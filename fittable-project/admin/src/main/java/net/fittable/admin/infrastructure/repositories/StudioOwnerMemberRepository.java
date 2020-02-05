package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.authentication.StudioOwnerMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudioOwnerMemberRepository extends JpaRepository<StudioOwnerMember, Long> {

    Optional<StudioOwnerMember> findByLoginId(String loginId);
}
