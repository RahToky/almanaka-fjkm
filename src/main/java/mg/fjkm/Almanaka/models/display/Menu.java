package mg.fjkm.Almanaka.models.display;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahatoky
 */
@Data
public class Menu {

    private List<MenuItem> menuItems = new ArrayList<>();

}
