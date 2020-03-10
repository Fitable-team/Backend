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
    public CommandLineRunner storeTestData(StudioManagementService managementService, CSVDatabaseInitializer initializer) {
        return args -> {
            Studio studio = new Studio();

            studio.setName("하스야요가아카데미");
            studio.setCoordinate(new Coordinate(37.370475, 127.107046));
            studio.setRepresentativeContact("031-715-3558");
            studio.setLocation(Location.builder().superDistrict("경기도 성남시 분당구").lowerDistrict("정자동").name("정자동").coordinate(new Coordinate(37.370475, 127.107046)).build());
            studio.setDetailedAddress("정자동 15-3 폴라리스1건물. 507호");
            studio.setStudioIntroduction("하스야요가아카데는 하타");

            SocialAddress socialAddress = new SocialAddress();
            socialAddress.setBlogAddress("https://blog.naver.com/hasya21");
            socialAddress.setHomepage("http://www.hasya.co.kr");

            studio.setSocialAddress(socialAddress);

            managementService.addNewStudio(studio,
                    ContactInformation.builder().contactInformationValue("031-715-3558").informationType(ContactInformationType.PHONE).build());

            initializer.setStudioDatabase();
        };
    }

}
