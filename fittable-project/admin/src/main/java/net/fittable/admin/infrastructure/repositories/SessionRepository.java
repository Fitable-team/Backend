package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.business.Studio;
import net.fittable.domain.business.reservation.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByTargetStudioContaining(List<Studio> studio);

    List<Session> findByStartTimeAfter(LocalDateTime after);

    List<Session> findByStartTimeAfterAndEndTimeBefore(LocalDateTime after, LocalDateTime before);
}
