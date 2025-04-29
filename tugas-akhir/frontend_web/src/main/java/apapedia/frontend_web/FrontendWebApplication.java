package apapedia.frontend_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FrontendWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendWebApplication.class, args);
	}

}
