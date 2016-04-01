package time.coffee;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableJpaRepositories("time.coffee.repository")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public Docket coffeeTimeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(apiPaths())
				.build()
				;
	}

	private Predicate<String> apiPaths() {
		return or(
				regex("/members/.*"),
				regex("/menus/.*"),
				regex("/orders/.*"),
				regex("/shops/.*"),
				regex("/surveys/.*")
		);
	}


}
