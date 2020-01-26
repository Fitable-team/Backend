package net.fittable.admin.infrastructure.batch;

import net.fittable.admin.infrastructure.repositories.ReservationRepository;
import net.fittable.domain.business.Review;
import net.fittable.domain.business.reservation.Reservation;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class ExpiredEntityBatchDeleteJob {

    private final ReservationRepository reservationRepository;

    public ExpiredEntityBatchDeleteJob(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Scheduled
    @Transactional
    public void deleteExpiredEntities() {
        List<Reservation> canceledReservations = reservationRepository.findByCanceled(true);

        List<Review> deletableReviews = reservationRepository.findAll()
                .stream()
                .map(Reservation::getUserReview)
                .filter(Review::isInBatchEvictionTarget)
                .collect(Collectors.toList());


    }
}
