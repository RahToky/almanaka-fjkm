package mg.fjkm.Almanaka.display.helpers;

import lombok.Data;
import mg.fjkm.Almanaka.display.models.CsvField;
import mg.fjkm.Almanaka.display.models.CsvForm;
import mg.fjkm.Almanaka.display.models.Menu;
import mg.fjkm.Almanaka.display.models.MenuItem;
import mg.fjkm.Almanaka.models.Csv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mahatoky
 */
@Data
public abstract class DisplayHelper {

    private static final String FIELD_SEPARATOR = ":";

    private static HashMap<String, String> FIELD_HTML_TYPE = new HashMap<>();
    private static HashMap<String, Class> FIELD_CLASS = new HashMap<>();

    static {
        FIELD_HTML_TYPE.put("LocalDate", "date");
        FIELD_HTML_TYPE.put("LocalDateTime", "date");
        FIELD_HTML_TYPE.put("Double", "number");
        FIELD_HTML_TYPE.put("Integer", "number");
        FIELD_HTML_TYPE.put("TextArea", "textarea");

        FIELD_CLASS.put("LocalDate", LocalDate.class);
        FIELD_CLASS.put("LocalDateTime", LocalDateTime.class);
        FIELD_CLASS.put("Double", Double.class);
        FIELD_CLASS.put("Integer", Integer.class);
        FIELD_CLASS.put("TextArea", String.class);
        FIELD_CLASS.put("String", String.class);
    }

    public static Menu toMenu(List<Csv> csvList) {
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
        return menuItem;
    }

    public static Csv extractCsv(List<Csv> csvList, MenuItem menuItem) {
        return (menuItem == null || csvList == null || csvList.size() == 0) ? null : csvList.stream().filter(csv -> csv.getFilename().equals(menuItem.getFilename())).findFirst().get();
    }

    public static String getFieldName(String csvFieldName) {
        return csvFieldName.split(FIELD_SEPARATOR)[0];
    }

    public static String buildFieldName(String name, Class clazz) {
        return name + FIELD_SEPARATOR + clazz.getSimpleName();
    }

    public static CsvForm createCsvForm(Csv csv) {
        CsvField[] csvFields = new CsvField[csv.getHeaders().length];
        for (int i = 0; i < csvFields.length; i++) {
            String[] headerSplit = csv.getHeaders()[i].split(":");
            Class clazz = String.class;
            try {
                clazz = FIELD_CLASS.get(headerSplit[1]);
                if (clazz == null)
                    clazz = String.class;
            } catch (Exception e) {
            }
            String htmlType = "text";
            try {
                htmlType = FIELD_HTML_TYPE.get(headerSplit[1]);
                if (htmlType == null)
                    htmlType = "text";
            } catch (Exception e) {
            }
            csvFields[i] = new CsvField(headerSplit[0], null, clazz, clazz.getCanonicalName(), htmlType);
        }
        return new CsvForm(csvFields);
    }

    public static String getHtmlValue(Class clazz) {
        if (clazz == LocalDate.class || clazz == LocalDateTime.class)
            return "date";
        if (clazz == Number.class || clazz == Integer.class || clazz == Double.class)
            return "number";
        return "text";
    }
}

