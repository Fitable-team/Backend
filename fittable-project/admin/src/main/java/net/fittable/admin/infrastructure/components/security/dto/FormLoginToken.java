package net.fittable.admin.infrastructure.components.security.dto;

import lombok.Data;
import net.fittable.domain.authentication.enums.MemberAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;

@Data
public class FormLoginToken extends AbstractAuthenticationToken {

    private String userId;
    private String password;

    public FormLoginToken(String userId, String password) {
        super(null);
    }

    public FormLoginToken(String userId, String password, MemberAuthority authority) {
        super(Collections.singletonList(new SimpleGrantedAuthority(authority.name())));
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }
}
