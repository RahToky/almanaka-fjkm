package mg.fjkm.Almanaka.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@ToString
public class Csv {

    private String filename;
    private String title;
    private String[] headers;
    private ArrayList<String[]> lines = new ArrayList<>();

}
