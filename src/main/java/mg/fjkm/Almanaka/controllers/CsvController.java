package mg.fjkm.Almanaka.controllers;

import mg.fjkm.Almanaka.helpers.CsvHelper;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.display.Menu;
import mg.fjkm.Almanaka.models.display.MenuItem;
import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahatoky
 */
@Controller
@RequestMapping("/")
public class CsvController {

    @Autowired
    private CsvService csvService;

    public static List<Csv> CSV_LIST = new ArrayList<>();
    public static Menu MENU;

    private void addMenu(Model model) {
        if (MENU == null)
            System.err.println(">>> ERROR MENU IS NULL !!!!");
        model.addAttribute("menu", MENU);
    }

    @GetMapping("")
    public String index(Model model) {
        CSV_LIST = csvService.getAllCsv();
        MENU = CsvHelper.toMenu(CSV_LIST);
        addMenu(model);
        return "index";
    }

    @GetMapping("{csvHref}")
    public String toListPage(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent() || CSV_LIST == null)
                return "redirect:/";
            Csv csv = CsvHelper.extractCsv(CSV_LIST, menuItem.get());
            addMenu(model);
            model.addAttribute("csvHref", csvHref);
            model.addAttribute("csv", csv);
        } catch (Exception e) {
            System.err.println("ERROR toListPage ::" + e.getMessage());
            e.printStackTrace();
        }
        return "list";
    }

    @GetMapping("{csvHref}/add")
    public String addNewLine(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent() || CSV_LIST == null)
                return "redirect:/";
            Csv csv = CsvHelper.extractCsv(CSV_LIST, menuItem.get());
            addMenu(model);
            model.addAttribute("csvHref", csvHref);
            model.addAttribute("csvHeaders", csv.getHeaders());
            model.addAttribute("csvTitle", csv.getTitle());
            model.addAttribute("csvForm", CsvHelper.toCsvForm(csv));
        } catch (Exception e) {
            System.err.println("ERROR addNewLine ::" + e.getMessage());
            e.printStackTrace();
        }
        return "form_csv";
    }

    @PostMapping("{csvHref}")
    public String addNewLineConfirm(@PathVariable(name = "csvHref") String csvHref, @ModelAttribute CsvForm csvForm) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent() || CSV_LIST == null)
                return "redirect:/";
            Csv csv = CsvHelper.extractCsv(CSV_LIST, menuItem.get());
            System.out.println(csvForm);
            return "redirect:/" + csvHref;
        } catch (Exception e) {
            System.err.println("ERROR addNewLineConfirm ::" + e.getMessage());
            e.printStackTrace();
        }
        return "form_csv";
    }

}
