package net.fittable.admin.application.components;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Coordinate;
import net.fittable.domain.premises.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class CSVDatabaseInitializer {

    private final StudioManagementService studioManagementService;
    private final String fileDirectory;

    @Autowired
    private TownRepository townRepository;

    public CSVDatabaseInitializer(StudioManagementService studioManagementService, @Value("${initial.filedir}") String fileDirectory) {
        this.studioManagementService = studioManagementService;
        this.fileDirectory = fileDirectory;
    }

    public void setStudioDatabase() {
        List<String[]> parsedRows = null;

        try(FileReader reader = new FileReader(this.fileDirectory)) {
            CSVReader csvReader = new CSVReader(reader);
            parsedRows = csvReader.readAll();
        } catch (CsvException | IOException e) {
            log.error("error while parsing CSV", e);
            return;
        }

        for(String[] c: parsedRows) {
            Studio studio = new Studio();
            for(int i = 0; i < c.length; i++) {
                switch(i) {
                    case 0:
                        studio.setName(c[i]);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Town town = townRepository.findByName(c[i].split(" ")[2])
                                .orElse(Town.builder()
                                        .name(c[i + 1].split(" ")[0])
                                        .superDistrict(c[i])
                                        .lowerDistrict(c[i + 1].split(" ")[0])
                                        .build());

                        studio.setTown(town);
                    case 4:
                        studio.setDetailedAddress(c[i - 1] + c[i]);
                    case 5:
                        studio.setCoordinate(new Coordinate(Double.parseDouble(c[i]), Double.parseDouble(c[i + 1])));
                    case 6:
                        break;
                    case 7:
                        studio.setRepresentativeContact(c[i]);
                    case 8:
                        studio.setStudioIntroduction(c[i]);
                    case 9:



                }
            }
        }
        return;
    }

    private List<Session> parseTimetableInformation() {
        return null;
    }


}
