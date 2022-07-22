package mg.fjkm.Almanaka.cache;

import mg.fjkm.Almanaka.models.entity.Csv;

import java.util.ArrayList;
import java.util.List;

/**
 * Vas servir de cache pour diminuer le temps d'accès aux fichiers csv
 *
 * @author mahatoky
 */

public class CsvCache {

    private List<Csv> csvList = new ArrayList<>();

    public void set(List<Csv> csvList) {
        this.csvList = csvList;
    }

    public List<Csv> get() {
        return csvList;
    }

    public boolean isEmpty() {
        return csvList != null && csvList.isEmpty();
    }

    public boolean containObject() {
        return csvList != null;
    }

    public Csv getCsvByFilename(String filemane) {
        return (filemane == null || filemane.isEmpty() || csvList == null || csvList.isEmpty()) ? null : csvList.stream().filter(csv -> csv.getFilename().equals(filemane)).findFirst().get();
    }

}
