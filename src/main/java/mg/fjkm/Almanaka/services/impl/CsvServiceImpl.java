package mg.fjkm.Almanaka.services.impl;

import mg.fjkm.Almanaka.cache.CsvCache;
import mg.fjkm.Almanaka.exception.SaveFailedException;
import mg.fjkm.Almanaka.models.display.CsvField;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            if (csvForm == null)
                throw new SaveFailedException("CsvForm is null.");
            if (csvForm.getFilename() == null || csvForm.getFilename().isEmpty())
                throw new SaveFailedException("CsvForm.filename is null or empty");
            if (csvForm.getFields() == null || csvForm.getFields().length == 0)
                throw new SaveFailedException("CsvForm.fields is null or empty");

            StringBuilder stringBuilder = new StringBuilder();
            CsvField[] fields = csvForm.getFields();
            for (CsvField field : fields) {
                Object value = field.getValue();
                stringBuilder.append(value == null ? "" : value.toString());
                stringBuilder.append(";");
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            csvRepository.saveCsvLine(csvForm.getFilename(), stringBuilder.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new SaveFailedException(ioe.getMessage());
        }
    }
}
