package mg.fjkm.Almanaka.display.helpers;

import lombok.Data;
import mg.fjkm.Almanaka.display.models.Menu;
import mg.fjkm.Almanaka.display.models.MenuItem;
import mg.fjkm.Almanaka.models.Csv;

import java.util.List;

/**
 * @author Mahatoky
 */
@Data
public abstract class MenuHelper {

    private static final String FIELD_SEPARATOR = ":";

    public static Menu toMenu(List<Csv> csvList) {
        System.out.println("csvList.size=" + csvList.size());
        Menu menu = new Menu();
        for (Csv csv : csvList) {
            try {
                menu.getMenuItems().add(toMenuItem(csv));
            } catch (Exception e) {
                System.err.println("ERROR toMenu::" + e.getMessage() + ", csv=" + csv);
                e.printStackTrace();
            }
        }
        return menu;
    }

    public static MenuItem toMenuItem(Csv csv) {
        MenuItem menuItem = new MenuItem();
        menuItem.setFilename(csv.getFilename());
        menuItem.setLabel(csv.getTitle());
        menuItem.setHref(csv.getTitle().replaceAll("[^a-zA-Z]", "-").toLowerCase());
        System.out.println("menuItem=" + menuItem + ", csv=" + csv);
        return menuItem;
    }

    public static Csv extractCsv(List<Csv> csvList, MenuItem menuItem) {
        return (menuItem == null || csvList == null || csvList.size() == 0) ? null : csvList.stream().filter(csv -> csv.getFilename().equals(menuItem.getFilename())).findFirst().get();
    }

    public static String getFieldName(String csvFieldName) {
        return csvFieldName.split(FIELD_SEPARATOR)[0];
    }

    public static Class getFieldClass(String csvFieldName) {
        try {
            return Class.forName(csvFieldName.split(FIELD_SEPARATOR)[1]);
        } catch (Exception e) {
            return String.class;
        }
    }

    public static String buildFieldName(String name, Class clazz) {
        return name + FIELD_SEPARATOR + clazz.getSimpleName();
    }
}
