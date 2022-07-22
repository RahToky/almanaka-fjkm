package mg.fjkm.Almanaka.services;

import mg.fjkm.Almanaka.exception.SaveFailedException;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.entity.Csv;

import java.util.List;

public interface CsvService {

    List<Csv> getAllCsv();

    void saveCsvLine(CsvForm csvForm) throws SaveFailedException;

}
