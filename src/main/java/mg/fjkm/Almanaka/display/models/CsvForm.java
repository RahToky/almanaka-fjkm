package mg.fjkm.Almanaka.display.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CsvForm {

    private CsvField[] fields;

}
