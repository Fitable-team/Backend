package net.fittable.admin.infrastructure.components.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;
import net.fittable.admin.infrastructure.components.security.jwt.JwtMapper;
import net.fittable.admin.view.dto.LoginResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("authentication.login.landingpage")
    private String redirectionTarget;

    @Autowired
    private JwtMapper jwtMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        if(!(auth instanceof FormLoginToken)) {
            log.error("security was not configured correctly!");
            return;
        }
        LoginResultDto dto = new LoginResultDto();
        FormLoginToken processedToken = (FormLoginToken)auth;
        ObjectMapper objectMapper = new ObjectMapper();

        SecurityContextHolder.getContext().setAuthentication(processedToken);
        String tokenString = jwtMapper.mapToJwt(processedToken).orElseThrow(() -> new IllegalArgumentException("JWT 처리중 에러 발생."));

        dto.setResult("ok");
        dto.setStatusCode(HttpStatus.OK.value());
        dto.setToken(tokenString);

        res.setStatus(HttpStatus.OK.value());
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
