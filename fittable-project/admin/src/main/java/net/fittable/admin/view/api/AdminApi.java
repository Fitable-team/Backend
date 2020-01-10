package net.fittable.admin.view.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminApi {

    @GetMapping
    public String hello() {
        return "hello";
    }
}
