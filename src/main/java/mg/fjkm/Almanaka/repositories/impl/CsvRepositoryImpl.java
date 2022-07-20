package mg.fjkm.Almanaka.repositories.impl;

import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahatoky
 */
@Component
public class CsvRepositoryImpl implements CsvRepository {

    private static final String CSV_DIR = "./data/csv";

    private List<File> getAllCsvFile(String dir) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        ArrayList<File> csvNames = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                if ("csv".equals(filename.substring(filename.lastIndexOf(".") + 1))) {
                    csvNames.add(listOfFiles[i]);
                }
            }
        }
        return csvNames;
    }

    @Override
    public List<Csv> getAllCsv() {
        List<File> files = getAllCsvFile(CSV_DIR);
        List<Csv> results = new ArrayList<>();
        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                Csv csv = new Csv();
                csv.setFilename(file.getName());
                csv.setTitle(file.getName().substring(0, file.getName().lastIndexOf(".")));
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        csv.setHeaders(line.split(";"));
                        isFirstLine = false;
                    } else {
                        csv.getLines().add(line.split(";"));
                    }
                }
                results.add(csv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
