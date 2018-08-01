package spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {
        "business_logic",
        "infrastructure_database",
        "presentation_rest_api"
})
@EnableJpaRepositories("infrastructure_database")
@EntityScan("infrastructure_database")
public class Application {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }


    /**
     * Main method.
     *
     * @param args Nothing really...
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}