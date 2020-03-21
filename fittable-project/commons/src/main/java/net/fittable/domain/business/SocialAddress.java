package net.fittable.domain.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.fittable.persistence.validators.annotation.HttpUrl;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialAddress {

    private String kakaoId;

    @HttpUrl
    private String blogAddress;

    @HttpUrl
    private String instagramAddress;

    @NotEmpty
    @HttpUrl
    private String homepage;

    @HttpUrl
    private String facebookAddress;

    public String concatAddresses() {
        StringJoiner addresses = new StringJoiner(";");

        addresses.add(this.kakaoId);
        addresses.add(this.blogAddress);
        addresses.add(this.instagramAddress);
        addresses.add(this.homepage);
        addresses.add(this.facebookAddress);

        return addresses.toString();
    }

    public static SocialAddress fromConcatenatedString(String concat) {
        String[] splittedString = concat.split(";");

        return new SocialAddress(splittedString[0], splittedString[1], splittedString[2], splittedString[3], splittedString[4]);
    }

    public String getKakaoId() {
        if(StringUtils.isEmpty(this.kakaoId) || this.kakaoId.equals("null")) {
            return null;
        }

        return kakaoId;
    }

    public String getBlogAddress() {
        if(StringUtils.isEmpty(this.blogAddress) || this.blogAddress.equals("null")) {
            return null;
        }

        return blogAddress;
    }

    public String getInstagramAddress() {
        if(StringUtils.isEmpty(this.instagramAddress) || this.instagramAddress.equals("null")) {
            return null;
        }

        return instagramAddress;
    }

    public String getHomepage() {
        if(StringUtils.isEmpty(this.homepage) || this.homepage.equals("null")) {
            return null;
        }

        return homepage;
    }

    public String getFacebookAddress() {
        if(StringUtils.isEmpty(this.facebookAddress) || this.facebookAddress.equals("null")) {
            return null;
        }

        return facebookAddress;
    }
}
