package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.business.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
