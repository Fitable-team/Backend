package net.fittable.admin.view.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseApiResult {

    private int resultCode;
    private String message;
    private String result;

    public static BaseApiResult ok() {
        return new BaseApiResult(200, "OK" ,"요청에 성공하였습니다.");
    }
}
