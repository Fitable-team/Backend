package net.fittable.admin.infrastructure.repositories;

import net.fittable.domain.authentication.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {

    Optional<AdminMember> findByLoginId(String loginId);
}
