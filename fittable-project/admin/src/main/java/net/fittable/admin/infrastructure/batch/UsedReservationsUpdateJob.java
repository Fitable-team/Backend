package net.fittable.admin.infrastructure.batch;

import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.domain.business.reservation.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class UsedReservationsUpdateJob {

    private final SessionRepository repository;

    public UsedReservationsUpdateJob(SessionRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "* 0 * * *")
    public void setReservationsAsUsed() {
        repository.saveAll(
                repository.findAll().stream()
                .peek(Session::setAllReservationAsUsed)
                .collect(Collectors.toSet())
        );
    }
}
