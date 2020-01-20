package net.fittable.admin.application;

import net.fittable.admin.application.components.specifications.SMSNotifyService;
import net.fittable.admin.infrastructure.repositories.ReservationRepository;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
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

    public List<Studio> getAllOwnedStudios(StudioOwnerMember member) {
        return studioRepository.findByOwner(member);
    }

    @Transactional
    public List<Reservation> getAllReservations(Member member) {
        if(member.getAuthority() == MemberAuthority.MEMBER) {
            throw new IllegalArgumentException("일반회원 권한으로는 접근할 수 없습니다.");
        }

        if(member.getAuthority() == MemberAuthority.ADMIN) {
            return reservationRepository.findAll();
        }

        List<Session> targetSessions = sessionRepository.findByTargetStudioContaining(studioRepository.findByOwner((StudioOwnerMember) member));
        List<Reservation> matchingReservations = new ArrayList<>();

        for(Session s: targetSessions) {
            matchingReservations.addAll(s.getReservations());
        }
        return matchingReservations;
    }

    @Transactional
    public void acceptReservations(List<Long> reservationIds) {
        List<Reservation> reservations = reservationRepository.findAllById(reservationIds);
        List<ContactInformation> clientContacts =
                reservations.stream()
                    .map(Reservation::getReservedClient)
                    .map(ClientMember::getPhoneNumber)
                    .map(n -> new ContactInformation(ContactInformationType.CELLPHONE, n))
                    .collect(Collectors.toList());

        for(Reservation r: reservations) {
            r.setAccepted(true);
        }

        notifyService.sendToGroup(clientContacts);
        this.reservationRepository.saveAll(reservations);
    }

}
