package plalab.jpa.study02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Study02Application {

    public static void main(String[] args) {
        SpringApplication.run(Study02Application.class, args);
    }
}
