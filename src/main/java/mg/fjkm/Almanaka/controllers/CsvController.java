package mg.fjkm.Almanaka.controllers;

import mg.fjkm.Almanaka.cache.CsvCache;
import mg.fjkm.Almanaka.exception.SaveFailedException;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.display.Menu;
import mg.fjkm.Almanaka.models.display.MenuItem;
import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mahatoky
 */
@Controller
@RequestMapping("/almanaka")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @Autowired
    private CsvCache csvCache;

    @Autowired
    public Menu menu;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("menu", menu);
        return "index";
    }

    @GetMapping("{csvHref}")
    public String toListPage(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            System.out.println(">>> toListPage.href = " + csvHref);
            MenuItem menuItem = menu.getMenuItemByHref(csvHref);
            Csv csv = csvCache.getCsvByFilename(menuItem.getFilename());

            model.addAttribute("menu", menu);
            model.addAttribute("csvHref", csvHref);
            model.addAttribute("csv", csv);
            return "list";
        } catch (Exception e) {
            System.err.println("ERROR toListPage ::" + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("{csvHref}/add")
    public String showCsvLineForm(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            System.out.println(">>> showCsvLineForm.href == " + csvHref);
            MenuItem menuItem = menu.getMenuItemByHref(csvHref);
            Csv csv = csvCache.getCsvByFilename(menuItem.getFilename());

            model.addAttribute("menu", menu);
            model.addAttribute("csvHref", csvHref);
            model.addAttribute("csvHeaders", csv.getHeaders());
            model.addAttribute("csvTitle", csv.getTitle());
            model.addAttribute("csvForm", new CsvForm(csv));
            return "form_csv";
        } catch (Exception e) {
            System.err.println("ERROR addNewLine ::" + e.getMessage());
            e.printStackTrace();
            return "redirect:/";
        }
    }


    @PostMapping("{csvHref}")
    public String addNewLineConfirm(@PathVariable(name = "csvHref") String csvHref, @ModelAttribute CsvForm csvForm) {
        try {
            csvService.saveCsvLine(csvForm);
            csvCache.set(csvService.getAllCsv());
        } catch (SaveFailedException e) {
            System.err.println("ERROR addNewLineConfirm ::" + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/almanaka/" + csvHref;
    }

}
