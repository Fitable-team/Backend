package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.premises.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TownRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByName(String name);
}
