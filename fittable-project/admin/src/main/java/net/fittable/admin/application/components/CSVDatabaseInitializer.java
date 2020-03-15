package net.fittable.admin.application.components;

import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.admin.infrastructure.repositories.search.StudioSearchRepository;
import net.fittable.domain.business.SocialAddress;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.StudioFilter;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Coordinate;
import net.fittable.domain.premises.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CSVDatabaseInitializer {
    private static final String DATA_DELIMITER = "\t";

    private final StudioManagementService studioManagementService;
    private final String studioFileDir;
    private final String timetableFileDir;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private StudioSearchRepository studioSearchRepository;

    public CSVDatabaseInitializer(StudioManagementService studioManagementService,
                                  @Value("${initial.filedir.studio}") String studioFileDir,
                                  @Value("${initial.filedir.timetable}") String timetableFileDir) {
        this.studioManagementService = studioManagementService;
        this.studioFileDir = studioFileDir;
        this.timetableFileDir = timetableFileDir;
    }

    public void setStudioDatabase() {
        List<String[]> lines = readLineFromTsv(this.studioFileDir);

        for(String[] line: lines) {
            Studio studio = null;
            List<String> fields = Arrays.asList(line);
            System.out.println(fields);

            if(fields.contains("스튜디오ID")) {
                continue;
            }
            List<StudioFilter> filters = Arrays.asList(fields.get(10).split(","))
                    .stream()
                    .map(StudioFilter::generateEntity).collect(Collectors.toList());

            SocialAddress socialAddress = new SocialAddress();

            socialAddress.setHomepage(fields.get(11));

            if(fields.size() > 12) {
                socialAddress.setBlogAddress(fields.get(12));
            }

            Location location = Location.builder()
                    .name(fields.get(13))
                    .superDistrict(fields.get(4))
                    .lowerDistrict(fields.get(5))
                    .coordinate(Coordinate.builder().latitude(Double.parseDouble(fields.get(6))).longitude(Double.parseDouble(fields.get(7))).build())
                    .build();

            studio = Studio.builder()
                    .studioAttributes(filters)
                    .id(Long.parseLong(fields.get(0)))
                    .name(fields.get(1))
                    .location(location)
                    .topAddress(fields.get(4))
                    .detailedAddress(fields.get(5))
                    .coordinate(Coordinate.builder().latitude(Double.parseDouble(fields.get(6))).longitude(Double.parseDouble(fields.get(7))).build())
                    .representativeContact(fields.get(8))
                    .studioIntroduction(fields.get(9))
                    .socialAddress(socialAddress)
                    .build();

            studioRepository.save(studio);
        }
    }

    public void setTimetableDatabase() {
        List<String[]> lines = readLineFromTsv(this.timetableFileDir);
    }

    private List<String[]> readLineFromTsv(String resourceName) {
        List<String[]> parsedRows = new ArrayList<>();

        try(InputStreamReader reader = new InputStreamReader(CSVDatabaseInitializer.class.getResourceAsStream(resourceName), "x-windows-949")) {
            BufferedReader lineReader = new BufferedReader(reader);
            String line = lineReader.readLine();

            while(!StringUtils.isEmpty(line)) {
                line = line.replace("\"", "");

                parsedRows.add(line.split(DATA_DELIMITER));
                line = lineReader.readLine();
            }

        } catch (IOException e) {
            log.error("error while parsing CSV", e);
            return Collections.emptyList();
        }
        return parsedRows;
    }

    private List<Session> parseTimetableInformation() {
        return null;
    }


}
