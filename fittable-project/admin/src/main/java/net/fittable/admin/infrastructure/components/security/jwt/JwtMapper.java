package net.fittable.admin.infrastructure.components.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;
import net.fittable.domain.authentication.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@Slf4j
public class JwtMapper {

    private final String jwtSecret;

    public JwtMapper(@Value("${authentication.jwt.secret}")String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Optional<String> mapToJwt(FormLoginToken token) {
        String generatedToken = null;

        if(!token.isAuthenticated()) {
            throw new IllegalArgumentException("인증되지 않은 토큰입니다.");
        }
        Member authenticatedMember = token.getPrincipal();

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
            generatedToken = JWT.create()
                    .withIssuer("fitable")
                    .withClaim("memberId", authenticatedMember.getLoginId())
                    .withClaim("memberAuthority", authenticatedMember.getAuthority().name())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.error("error occurred while signing JWT token:", e);
            return Optional.empty();
        }
        return Optional.of(generatedToken);
    }
}
