package mg.fjkm.Almanaka.config;

import mg.fjkm.Almanaka.cache.CsvCache;
import mg.fjkm.Almanaka.models.display.Menu;
import mg.fjkm.Almanaka.repositories.CsvRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class CacheLoaderConfig {

    @Bean
    public CsvCache csvCache(CsvRepository csvRepository) {
        CsvCache csvCache = new CsvCache();
        csvCache.set(csvRepository.getAllCsv());
        return csvCache;
    }

    @Bean
    public Menu menu(CsvCache csvCache) {
        return new Menu(csvCache.get());
    }

}
