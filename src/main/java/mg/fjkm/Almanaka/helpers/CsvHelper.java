package mg.fjkm.Almanaka.helpers;

import lombok.Data;
import mg.fjkm.Almanaka.cache.Const;
import mg.fjkm.Almanaka.models.display.CsvField;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.display.Menu;
import mg.fjkm.Almanaka.models.display.MenuItem;
import mg.fjkm.Almanaka.models.entity.Csv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mahatoky
 */
@Data
public abstract class CsvHelper {

    public static String getFieldName(String csvFieldName) {
        return csvFieldName.split(Const.FIELD_SEPARATOR)[0];
    }

    public static String buildFieldName(String name, Class clazz) {
        return name + Const.FIELD_SEPARATOR + clazz.getSimpleName();
    }

    public static String getHtmlValue(Class clazz) {
        if (clazz == LocalDate.class || clazz == LocalDateTime.class)
            return "date";
        if (clazz == Number.class || clazz == Integer.class || clazz == Double.class)
            return "number";
        return "text";
    }
}

