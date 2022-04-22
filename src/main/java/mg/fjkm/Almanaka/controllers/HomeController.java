package mg.fjkm.Almanaka.controllers;

import mg.fjkm.Almanaka.display.helpers.MenuHelper;
import mg.fjkm.Almanaka.display.models.Menu;
import mg.fjkm.Almanaka.display.models.MenuItem;
import mg.fjkm.Almanaka.models.Csv;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        MENU = MenuHelper.toMenu(CSV_LIST);
        addMenu(model);
        return "index";
    }

    @GetMapping("{csvHref}")
    public String toListPage(@PathVariable(name = "csvHref") String csvHref, Model model) {
        try {
            Optional<MenuItem> menuItem = MENU.getMenuItems().stream().filter(item -> item.getHref().equals(csvHref)).findFirst();
            if (!menuItem.isPresent())
                return "index";
            Csv csv = MenuHelper.extractCsv(CSV_LIST, menuItem.get());
            addMenu(model);
            model.addAttribute("csv", csv);
        } catch (Exception e) {
            System.err.println("ERROR toListPage ::" + e.getMessage());
            e.printStackTrace();
        }
        return "list";
    }

}
