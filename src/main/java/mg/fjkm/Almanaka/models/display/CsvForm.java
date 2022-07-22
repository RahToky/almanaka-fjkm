package mg.fjkm.Almanaka.models.display;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import mg.fjkm.Almanaka.cache.Const;
import mg.fjkm.Almanaka.models.entity.Csv;

import java.util.Locale;

@Data
@AllArgsConstructor
@ToString
public class    CsvForm {

    private String filename;
    private CsvField[] fields;

    public CsvForm(){}

    public CsvForm(Csv csv) {
        this.filename = csv.getFilename();
        fields = new CsvField[csv.getHeaders().length];
        for (int i = 0; i < fields.length; i++) {
            String[] headerSplit = csv.getHeaders()[i].split(":");
            Class clazz = String.class;
            try {
                clazz = Const.FIELD_CLASS.get(headerSplit[1].toLowerCase());
                if (clazz == null)
                    clazz = String.class;
            } catch (Exception e) {
            }
            String htmlType = "text";
            try {
                htmlType = Const.FIELD_HTML_TYPE.get(headerSplit[1].toLowerCase());
                if (htmlType == null)
                    htmlType = "text";
            } catch (Exception e) {
            }
            fields[i] = new CsvField(headerSplit[0], null, clazz, clazz.getCanonicalName(), htmlType);
        }
    }

}
