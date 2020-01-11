package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.ReservationRepository;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.Studio;
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
    private StudioRepository studioRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public void addNewStudio(Studio studio, StudioOwnerMember member) {
        studio.setOwner(member);

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
        reservationRepository.saveAll(reservationRepository.findAllById(reservationIds)
                .stream()
                .peek(Reservation::markAsAccepted)
                .collect(Collectors.toSet()));
    }

    @Transactional
    public void checkReservationAsUsed(List<Long> reservationIds) {

    }

    @Transactional
    public void checkAsAbsent(List<Reservation> reservation) {
        reservationRepository.saveAll(reservation.stream()
                .peek(r -> r.setWasAbsent(true)).collect(Collectors.toSet()));
    }
}
