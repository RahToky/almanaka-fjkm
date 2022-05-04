package mg.fjkm.Almanaka.display.models;

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
    @Setter(AccessLevel.NONE)
    private String htmlType = "text";

}
