package net.fittable.admin.view.api;

import net.fittable.admin.application.components.specifications.FileUploadService;
import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/admin")
public class AdminApi {

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping("/studio-image")
    @PreAuthorize("#{hasRole('ADMIN')}")
    public String postImage(MultipartFile image, @AuthenticationPrincipal FormLoginToken memberToken) {

        return fileUploadService.uploadMultipartFile(memberToken.getPrincipal(), image);
    }
}
