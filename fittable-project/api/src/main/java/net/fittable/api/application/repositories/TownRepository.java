package net.fittable.api.application.repositories;

import net.fittable.domain.premises.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Long> {

    Optional<Town> findByName(String name);
}