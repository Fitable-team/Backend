package net.fittable.admin.infrastructure.components.security;

import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;
import net.fittable.admin.infrastructure.repositories.AdminMemberRepository;
import net.fittable.admin.infrastructure.repositories.StudioOwnerMemberRepository;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.StudioOwnerMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class DatabaseAccountAuthProvider implements AuthenticationProvider {

    private final StudioOwnerMemberRepository studioOwnerMemberRepository;
    private final AdminMemberRepository adminMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseAccountAuthProvider(StudioOwnerMemberRepository studioOwnerMemberRepository, AdminMemberRepository adminMemberRepository) {
        this.studioOwnerMemberRepository = studioOwnerMemberRepository;
        this.adminMemberRepository = adminMemberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FormLoginToken token = (FormLoginToken) authentication;
        Optional<StudioOwnerMember> studioOwnerMember = studioOwnerMemberRepository.findByLoginId(token.getUserId());

        Member member = studioOwnerMember.isPresent() ?
                studioOwnerMember.get() :
                adminMemberRepository
                        .findByLoginId(token.getUserId())
                        .orElseThrow(() -> new NoSuchElementException("등록되지 않은 회원정보입니다."));

        String encryptedPassword = passwordEncoder.encode(token.getPassword());

        if(member.isMatchingPassword(encryptedPassword)) {
            return new FormLoginToken(token.getUserId(), token.getCredentials(), member);
        }

        throw new SecurityException("비밀번호가 일치하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FormLoginToken.class.isAssignableFrom(aClass);
    }
}
