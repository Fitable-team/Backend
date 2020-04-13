package net.fittable.admin;

import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.application.components.CSVDatabaseInitializer;
import net.fittable.domain.business.ContactInformation;
import net.fittable.domain.business.SocialAddress;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.enums.ContactInformationType;
import net.fittable.domain.premises.Coordinate;
import net.fittable.domain.premises.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Bean
    public CommandLineRunner storeTestData(CSVDatabaseInitializer initializer) {
        return args -> {
            initializer.setStudioDatabase();
            initializer.setTimetableDatabase();
            initializer.setReviews();
        };
    }

}
