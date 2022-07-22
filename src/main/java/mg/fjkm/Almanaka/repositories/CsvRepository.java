package mg.fjkm.Almanaka.repositories;

import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.entity.Csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Mahatoky
 */
public interface CsvRepository {

     List<Csv> getAllCsv();

     void saveCsvLine(CsvForm csvForm) throws IOException, NullPointerException;

}
