package mg.fjkm.Almanaka.services.impl;

import mg.fjkm.Almanaka.cache.CsvCache;
import mg.fjkm.Almanaka.exception.SaveFailedException;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Mahatoky
 */
@Service
public class CsvServiceImpl implements CsvService {

    @Autowired
    private CsvRepository csvRepository;

    @Override
    public List<Csv> getAllCsv() {
        return csvRepository.getAllCsv();
    }

    @Override
    public void saveCsvLine(CsvForm csvForm) throws SaveFailedException {
        try {
            csvRepository.saveCsvLine(csvForm);
        }catch (IOException ioe){
            throw new SaveFailedException(ioe.getMessage());
        }
    }
}
