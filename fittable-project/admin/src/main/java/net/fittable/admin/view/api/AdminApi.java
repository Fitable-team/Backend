package net.fittable.admin.view.api;

import net.fittable.admin.application.ReservationManagementService;
import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.application.components.specifications.FileUploadService;
import net.fittable.admin.infrastructure.components.security.dto.FormLoginToken;
import net.fittable.admin.view.dto.BaseApiResult;
import net.fittable.admin.view.dto.TimetableManageDto;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController("/api/admin")
public class AdminApi {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private StudioManagementService studioManagementService;

    @Autowired
    private ReservationManagementService reservationManagementService;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/studio-list")
    @PreAuthorize("#hasRole('ADMIN') OR hasRole('STUDIO_MEMBER')")
    public List<Studio> getStudioList(@AuthenticationPrincipal FormLoginToken memberToken) {
        Member loginMember = memberToken.getPrincipal();

        return studioManagementService.getAllOwnedStudios(loginMember);
    }

    @PostMapping("/new-studio")
    @PreAuthorize("#hasRole('ADMIN') OR hasRole('STUDIO_MEMBER')")
    public BaseApiResult registerNewStudio() {
        return null;
    }

    @PostMapping("/studio-image")
    @PreAuthorize("#hasRole('ADMIN')")
    public String postImage(MultipartFile image, @AuthenticationPrincipal FormLoginToken memberToken) {
        return fileUploadService.uploadMultipartFile(memberToken.getPrincipal(), image);
    }

    @PostMapping("/edit-timetable")
    @PreAuthorize("#hasRole('ADMIN') OR hasRole('STUDIO_MEMBER')")
    public BaseApiResult editTimeTable(TimetableManageDto dto) {
        studioManagementService.editTimetable(dto);

        return BaseApiResult.ok();
    }
}
