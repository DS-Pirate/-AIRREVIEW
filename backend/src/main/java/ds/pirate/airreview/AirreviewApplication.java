package ds.pirate.airreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirreviewApplication.class, args);
	}

}
