package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.authentication.StudioOwnerMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioOwnerMemberRepository extends JpaRepository<StudioOwnerMember, Long> {
}
