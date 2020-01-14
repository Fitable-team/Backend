package net.fittable.admin.infrastructure.components.security;

import net.fittable.admin.infrastructure.repositories.AdminMemberRepository;
import net.fittable.admin.infrastructure.repositories.StudioOwnerMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DatabaseAccountAuthProvider implements AuthenticationProvider {

    private final StudioOwnerMemberRepository studioOwnerMemberRepository;
    private final AdminMemberRepository adminMemberRepository;

    @Autowired
    public DatabaseAccountAuthProvider(StudioOwnerMemberRepository studioOwnerMemberRepository, AdminMemberRepository adminMemberRepository) {
        this.studioOwnerMemberRepository = studioOwnerMemberRepository;
        this.adminMemberRepository = adminMemberRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
