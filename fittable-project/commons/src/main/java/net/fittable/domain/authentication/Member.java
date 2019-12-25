package net.fittable.domain.authentication;

import java.time.LocalDateTime;

/**
 * defines specifications to be implemented for fittable.net member domain classes.
 * classes may contain sensitive private infos, including password or birthday.
 * implement this interface to conform to member domain specifications.
 */

public interface Member {

    String getLoginId();

    LocalDateTime getBirthday();

    String getPhoneNumber();

    String getEmailAddress();

    String getAuthority();

    boolean isMatchingPassword(String encryptedPassword);


}
