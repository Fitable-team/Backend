package net.fittable.admin.view.dto;

import lombok.Data;
import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;

@Data
public class LoginRequestDto {

    private String id;
    private String password;

    public FormLoginToken toToken() {
        return new FormLoginToken(this.id, this.password);
    }
}
