package net.fittable.domain.business;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
public class SocialAddress {

    @NotEmpty
    private String kakaoId;

    @NotEmpty
    private String instagramAddress;

    @NotEmpty
    private String homepage;

    @NotEmpty
    private String facebookAddress;

    public String concatAddresses() {
        StringJoiner addresses = new StringJoiner(";");

        addresses.add(this.kakaoId);
        addresses.add(this.instagramAddress);
        addresses.add(this.homepage);
        addresses.add(this.facebookAddress);

        return addresses.toString();
    }

    public static SocialAddress fromConcatenatedString(String concat) {
        String[] splittedString = concat.split(";");

        return new SocialAddress(splittedString[0], splittedString[1], splittedString[2], splittedString[3]);
    }


}
