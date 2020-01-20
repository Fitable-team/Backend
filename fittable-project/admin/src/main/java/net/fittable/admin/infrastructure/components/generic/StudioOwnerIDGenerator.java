package net.fittable.admin.infrastructure.components.generic;

import net.fittable.domain.authentication.StudioOwnerMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudioOwnerIDGenerator {
    private static final int RANDOM_STUDIO_ID_DIGIT = 6;
    private static final int RANDOM_PASSWORD_DIGIT = 8;

    private final PasswordEncoder encoder;

    @Autowired
    public StudioOwnerIDGenerator(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public StudioOwnerMember setLoginId(StudioOwnerMember member) {
        String randomLoginID = UUID.randomUUID().toString().substring(0, RANDOM_STUDIO_ID_DIGIT);

        member.setLoginId(randomLoginID);
        return member;
    }

    public String setPassword(StudioOwnerMember member) {
        String randomPassword = UUID.randomUUID().toString().substring(0, RANDOM_PASSWORD_DIGIT);
        member.setEncryptedPassword(encoder.encode(randomPassword));

        return randomPassword;
    }
}
