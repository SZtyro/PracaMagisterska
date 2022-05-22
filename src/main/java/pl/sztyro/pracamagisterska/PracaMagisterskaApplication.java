package pl.sztyro.pracamagisterska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan("pl.sztyro.pracamagisterska.model")

public class PracaMagisterskaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracaMagisterskaApplication.class, args);
    }

}
