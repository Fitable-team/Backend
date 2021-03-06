package net.fittable.admin.application.components;

import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.infrastructure.repositories.ReviewRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.admin.infrastructure.repositories.search.StudioSearchRepository;
import net.fittable.admin.view.dto.client.request.ReviewPostDto;
import net.fittable.domain.business.*;
import net.fittable.domain.business.reservation.RegularSession;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Coordinate;
import net.fittable.domain.premises.Location;
import net.fittable.domain.search.SearchableStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalTime;
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
    private ReviewRepository reviewRepository;

    @Autowired
    private StudioSearchRepository studioSearchRepository;

    public CSVDatabaseInitializer(StudioManagementService studioManagementService,
                                  @Value("${initial.filedir.studio}") String studioFileDir,
                                  @Value("${initial.filedir.timetable}") String timetableFileDir) {
        this.studioManagementService = studioManagementService;
        this.studioFileDir = studioFileDir;
        this.timetableFileDir = timetableFileDir;
    }

    @Transactional
    public void setStudioDatabase() {
        List<String[]> lines = readLineFromTsv(this.studioFileDir);

        for(String[] line: lines) {
            Studio studio = null;
            List<String> fields = Arrays.asList(line);
            System.out.println(fields);

            if(fields.contains("스튜디오ID")) {
                continue;
            }
            StudioImageList imageList = new StudioImageList();

            imageList.setRepresentativeImage(fields.get(2));
            imageList.setImageDirectories(Arrays.asList(fields.get(3).split(";")));

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
                    .imageList(imageList)
                    .topAddress(fields.get(4))
                    .detailedAddress(fields.get(5))
                    .coordinate(Coordinate.builder().latitude(Double.parseDouble(fields.get(6))).longitude(Double.parseDouble(fields.get(7))).build())
                    .representativeContact(fields.get(8))
                    .studioIntroduction(fields.get(9))
                    .socialAddress(socialAddress)
                    .build();

            studioRepository.save(studio);
            studioSearchRepository.saveNewStudio(SearchableStudio.fromStudio(studio));
        }
    }

    @Transactional
    public void setTimetableDatabase() {
        List<String[]> lines = readLineFromTsv(this.timetableFileDir);

        for(String[] line: lines) {
            if(Arrays.asList(line).contains("스튜디오명")) {
                continue;
            }

            System.out.println(Arrays.asList(line));
            Lesson lesson = new Lesson();
            Studio targetStudio = studioRepository.findByName(line[1]).orElseThrow(() -> new NoSuchElementException("해당하는 명칭의 스튜디오 없음"));
            RegularSession regularSession = null;
            Session sessionInfo = new Session();

            lesson.setStudio(targetStudio);


            List<String> fields = Arrays.asList(line);
            if(fields.contains("스튜디오ID")) {
                continue;
            }

            lesson.setTitle(fields.get(4));
            lesson.setInstructorName(fields.get(5));

            if(!fields.get(8).equals("-")) {
                sessionInfo.setPrice(Integer.parseInt(fields.get(8)));
            }

            if(Boolean.valueOf(fields.get(2))) {
                regularSession = new RegularSession();

                for(String day: fields.get(3).split(";")) {
                    regularSession.addRegularDay(day);
                }
            }

            sessionInfo.setRegularSession(regularSession);
            sessionInfo.setLesson(lesson);

            sessionInfo.setStartTime(LocalTime.parse(fields.get(6)));
            sessionInfo.setEndTime(LocalTime.parse(fields.get(7)));

            Set<Session> sessionSet = new HashSet<>();
            sessionSet.add(sessionInfo);

            lesson.setSessions(sessionSet);
            targetStudio.addLesson(lesson);
            lesson.setStudio(targetStudio);

            studioRepository.save(targetStudio);
        }
    }

    @Transactional
    public void setReviews() {
        List<Studio> studios = studioRepository.findAll();

        for(Studio studio: studios) {
            List<ReviewPostDto> generatedReviews = newReviewRequests(studio.getId());
            generatedReviews.forEach(studioManagementService::addNewReviewForStudio);
        }
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

    private List<ReviewPostDto> newReviewRequests(Long studioId) {
        List<ReviewPostDto> dtos = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            ReviewPostDto dto = new ReviewPostDto();

            dto.setAuthorsName(generateAuthorName(i));
            dto.setContent("요가 맛집이네요!");
            dto.setRating(7.5D);
            dto.setStudioId(studioId);

            dtos.add(dto);
        }
        return dtos;
    }

    private String generateAuthorName(int index) {
        switch(index) {
            case 0:
                return "이말년";
            case 1:
                return "주호민";
            case 2:
                return "류혜영";
            default:
                return "";
        }
    }


}
