package mg.fjkm.Almanaka.controllers;

import mg.fjkm.Almanaka.display.helpers.DisplayHelper;
import mg.fjkm.Almanaka.display.models.CsvForm;
import mg.fjkm.Almanaka.display.models.Menu;
import mg.fjkm.Almanaka.display.models.MenuItem;
import mg.fjkm.Almanaka.models.Csv;
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
public class HomeController {

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
        MENU = DisplayHelper.toMenu(CSV_LIST);
        addMenu(model);
        return "index";
    }

    @GetMapping("{csvHref}")
    public String toListPage(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent() || CSV_LIST == null)
                return "redirect:/";
            Csv csv = DisplayHelper.extractCsv(CSV_LIST, menuItem.get());
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
            Csv csv = DisplayHelper.extractCsv(CSV_LIST, menuItem.get());
            addMenu(model);
            model.addAttribute("csvHref", csvHref);
            model.addAttribute("csvHeaders", csv.getHeaders());
            model.addAttribute("csvTitle", csv.getTitle());
            model.addAttribute("csvForm", DisplayHelper.createCsvForm(csv));
        } catch (Exception e) {
            System.err.println("ERROR toListPage ::" + e.getMessage());
            e.printStackTrace();
        }
        return "form_csv";
    }

    @PostMapping("{csvHref}")
    public String addNewLineConfirm(@PathVariable(name = "csvHref") String csvHref, @ModelAttribute CsvForm csvForm, Model model) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent() || CSV_LIST == null)
                return "redirect:/";
            Csv csv = DisplayHelper.extractCsv(CSV_LIST, menuItem.get());
            System.out.println("csvForm: " + csvForm);
            return "redirect:/" + csvHref;
        } catch (Exception e) {
            System.err.println("ERROR toListPage ::" + e.getMessage());
            e.printStackTrace();
        }
        return "form_csv";
    }

}