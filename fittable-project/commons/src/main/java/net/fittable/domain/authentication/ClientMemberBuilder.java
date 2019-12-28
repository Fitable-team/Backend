package net.fittable.domain.authentication;

import java.time.LocalDateTime;

public final class ClientMemberBuilder {
    private String loginId;
    private String encryptedPassword;
    private LocalDateTime birthday;
    private String phoneNumber;
    private String emailAddress;

    private ClientMemberBuilder() {
    }

    public static ClientMemberBuilder aClientMember() {
        return new ClientMemberBuilder();
    }

    public ClientMemberBuilder loginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public ClientMemberBuilder encryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public ClientMemberBuilder birthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public ClientMemberBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientMemberBuilder emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public ClientMember build() {
        return new ClientMember(loginId, encryptedPassword, birthday, phoneNumber, emailAddress);
    }
}
