package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.business.Review;
import net.fittable.domain.business.Studio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTargetStudio(Studio targetStudio, Pageable pageable);

}
