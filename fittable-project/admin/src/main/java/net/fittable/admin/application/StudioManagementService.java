package net.fittable.admin.application;

import com.sun.corba.se.pept.transport.ContactInfo;
import net.fittable.admin.application.components.specifications.SMSNotifyService;
import net.fittable.admin.infrastructure.repositories.ReservationRepository;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.view.dto.TimetableManageDto;
import net.fittable.domain.authentication.ClientMember;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.ContactInformation;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.enums.ContactInformationType;
import net.fittable.domain.business.reservation.Reservation;
import net.fittable.domain.business.reservation.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public void addNewStudio(Studio studio, ContactInformation contactInformation) {
        studio.setOwner(memberManagementService.generateNewMember(contactInformation));

        studioRepository.save(studio);
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

    public List<Studio> getAllOwnedStudios(StudioOwnerMember member) {
        return studioRepository.findByOwner(member);
    }
}
