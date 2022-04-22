package mg.fjkm.Almanaka.services.impl;

import mg.fjkm.Almanaka.models.Csv;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
