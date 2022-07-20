package mg.fjkm.Almanaka.models.display;

import lombok.Data;
import lombok.ToString;

/**
 * @author Mahatoky
 */
@Data
@ToString
public class MenuItem {

    private String label;
    private String href;
    private String filename;

}
