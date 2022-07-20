package mg.fjkm.Almanaka.cache;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public abstract class Const {

    public static final String FIELD_SEPARATOR = ":";

    public static HashMap<String, String> FIELD_HTML_TYPE = new HashMap<>();
    public static HashMap<String, Class> FIELD_CLASS = new HashMap<>();

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

}
