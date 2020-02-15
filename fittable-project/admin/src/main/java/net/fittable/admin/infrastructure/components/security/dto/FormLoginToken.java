package net.fittable.admin.infrastructure.components.security.dto;

import lombok.Data;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.enums.MemberAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Data
public class FormLoginToken extends AbstractAuthenticationToken {

    private String userId;
    private String password;
    private Member principal;

    public FormLoginToken(String userId, String password) {
        super(null);

        this.userId = userId;
        this.password = password;
    }

    public FormLoginToken(String userId, String password, Member processedPrincipal) {
        super(Collections.singletonList(new SimpleGrantedAuthority(processedPrincipal.getAuthority().name())));

        this.userId = userId;
        this.password = password;
        this.principal = processedPrincipal;
        super.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return this.password;
    }

    @Override
    public Member getPrincipal() {
        return this.principal;
    }
}
