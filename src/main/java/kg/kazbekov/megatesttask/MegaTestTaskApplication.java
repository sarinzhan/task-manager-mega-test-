package kg.kazbekov.megatesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MegaTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MegaTestTaskApplication.class, args);
    }

}
