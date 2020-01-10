package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    List<Studio> findByOwner(StudioOwnerMember member);

}
