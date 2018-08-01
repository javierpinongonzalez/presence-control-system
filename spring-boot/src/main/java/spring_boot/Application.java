package spring_boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "business_logic",
        "infrastructure_database",
        "presentation_rest_api"
})
@EnableJpaRepositories("infrastructure_database")
@EntityScan("infrastructure_database")
public class Application {

    //FIXME delete if not logging
    private static final Log logger = LogFactory.getLog(Application.class);

    //TODO write @Override's

    //FIXME es necesario?
    //@PostConstruct
    //void started() {
    //    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    //}


    /**
     * Main method.
     *
     * @param args Nothing really...
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}