package mg.fjkm.Almanaka.models.display;

import lombok.Data;
import mg.fjkm.Almanaka.models.entity.Csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahatoky
 */
@Data
public class Menu {

    private final String BASE_URL = "almanaka";

    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(){}

    public Menu(List<Csv> csvList) {
        for (Csv csv : csvList) {
            menuItems.add(toMenuItem(csv));
        }
    }

    public MenuItem getMenuItemByHref(String href) {
        if (href == null || href.isEmpty() || menuItems == null || menuItems.isEmpty()) {
            return null;
        }
        Optional<MenuItem> menuItem = menuItems.stream().filter(item -> item.getHref().equals(href)).findFirst();
        if (!menuItem.isPresent()) {
            return null;
        }
        return menuItem.get();
    }

    public MenuItem toMenuItem(Csv csv) {
        MenuItem menuItem = new MenuItem();
        menuItem.setFilename(csv.getFilename());
        menuItem.setLabel(csv.getTitle());
        menuItem.setHref(csv.getTitle().replaceAll("[^a-zA-Z]", "-").toLowerCase());
        return menuItem;
    }

}
