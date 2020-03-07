package net.fittable.admin.application;

import net.fittable.admin.application.components.specifications.SMSNotifyService;
import net.fittable.admin.infrastructure.repositories.ReservationRepository;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.view.dto.TimetableManageDto;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.ContactInformation;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.search.SearchableStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudioManagementService {

    @Autowired
    private MemberManagementService memberManagementService;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private SMSNotifyService notifyService;

    @Transactional
    public StudioDto getSingleStudio(String id) {
        Long studioId = Long.parseLong(id);

        Studio studio = studioRepository.findById(studioId).orElseThrow(() -> new NoSuchElementException("해당하는 아이디의 스튜디오가 없음."));
        return StudioDto.fromStudio(studio);
    }

    @Transactional
    public void addNewStudio(Studio studio, ContactInformation contactInformation) {
        studio.setOwner(memberManagementService.generateNewMember(contactInformation));

        studioRepository.save(studio);
    }

    @Transactional(readOnly = true)
    public SearchableStudio generateIndexableStudio(Long id) {
        return SearchableStudio.fromStudio(studioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 아이디의 스튜디오가 없음.")));
    }

    @Transactional(readOnly = true)
    public List<SearchableStudio> generateFullIndexList() {
        return studioRepository.findAll().stream().map(SearchableStudio::fromStudio).collect(Collectors.toList());
    }

    @Transactional
    public void editTimetable(TimetableManageDto dto) {
        if(dto.isCreatingNewTimeSession()) {
            Session createdSession = dto.toSession();
            createdSession.setTargetStudio(studioRepository
                    .findById(Long.parseLong(dto.getStudioId()))
                    .orElseThrow(() -> new NoSuchElementException("ID에 맞는 스튜디오가 없습니다."))
            );

            sessionRepository.save(createdSession);
            return;
        }
        Session existingSession = sessionRepository
                .findById(Long.parseLong(dto.getClassId())).orElseThrow(() -> new NoSuchElementException("ID에 맞는 클래스가 없습니다."));

    }

    public List<Studio> getAllOwnedStudios(Member member) {
        if(member.getAuthority() == MemberAuthority.ADMIN) {
            return studioRepository.findAll();
        }

        return studioRepository.findByOwner((StudioOwnerMember)member);
    }
}
