package mg.fjkm.Almanaka.repositories.impl;

import mg.fjkm.Almanaka.models.display.CsvField;
import mg.fjkm.Almanaka.models.display.CsvForm;
import mg.fjkm.Almanaka.models.entity.Csv;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static mg.fjkm.Almanaka.cache.Const.CSV_DIR;

/**
 * @author Mahatoky
 */
@Component
public class CsvRepositoryImpl implements CsvRepository {

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

    @Override
    public void saveCsvLine(String filename, String line) throws IOException {
        if (Files.exists(Paths.get(filename))) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_DIR + "/" + filename, true));
            writer.newLine();
            writer.append(line);
            writer.close();
        }
    }
}
