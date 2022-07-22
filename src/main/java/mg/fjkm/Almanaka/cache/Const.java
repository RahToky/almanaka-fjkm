package mg.fjkm.Almanaka.cache;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public abstract class Const {

    public static final String CSV_DIR = "./data/csv";
    public static final String FIELD_SEPARATOR = ":";

    public static HashMap<String, String> FIELD_HTML_TYPE = new HashMap<>();
    public static HashMap<String, Class> FIELD_CLASS = new HashMap<>();

    static {
        FIELD_HTML_TYPE.put("localdate", "date");
        FIELD_HTML_TYPE.put("localdatetime", "date");
        FIELD_HTML_TYPE.put("double", "number");
        FIELD_HTML_TYPE.put("integer", "number");
        FIELD_HTML_TYPE.put("textarea", "textarea");

        FIELD_CLASS.put("localdate", LocalDate.class);
        FIELD_CLASS.put("localdatetime", LocalDateTime.class);
        FIELD_CLASS.put("double", Double.class);
        FIELD_CLASS.put("integer", Integer.class);
        FIELD_CLASS.put("textarea", String.class);
        FIELD_CLASS.put("string", String.class);
    }

}
