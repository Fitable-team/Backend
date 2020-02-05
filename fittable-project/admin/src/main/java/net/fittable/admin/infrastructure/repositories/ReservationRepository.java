package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.business.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCanceled(boolean canceled);
}
