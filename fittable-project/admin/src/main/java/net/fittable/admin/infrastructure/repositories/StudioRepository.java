package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.Studio;
import net.fittable.domain.premises.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    List<Studio> findByOwner(StudioOwnerMember member);

    List<Studio> findByLocation(Location location);

}
