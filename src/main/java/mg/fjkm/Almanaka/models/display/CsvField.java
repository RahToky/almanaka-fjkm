package mg.fjkm.Almanaka.models.display;

import lombok.*;

/**
 * @author Mahatoky
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CsvField {

    private String name;
    private Object value;
    private Class clazz;
    private String clazzStr;
    @Setter(AccessLevel.NONE)
    private String htmlType = "text";

}
