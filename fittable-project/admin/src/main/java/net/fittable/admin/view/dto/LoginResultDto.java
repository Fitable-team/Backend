package net.fittable.admin.view.dto;

import lombok.Data;

@Data
public class LoginResultDto {

    private String result;
    private int statusCode;
    private String token;
}
