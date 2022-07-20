package mg.fjkm.Almanaka.config;

import mg.fjkm.Almanaka.cache.CsvCache;
import mg.fjkm.Almanaka.models.display.Menu;
import mg.fjkm.Almanaka.services.CsvService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class CacheLoader {

    @Bean
    public CsvCache csvCache(CsvService csvService) {
        System.out.println(">>> init config");
        CsvCache csvCache = new CsvCache();
        csvCache.store(csvService.getAllCsv());
        return csvCache;
    }

    @Bean
    public Menu menu(CsvCache csvCache) {
        return new Menu(csvCache.get());
    }

}
