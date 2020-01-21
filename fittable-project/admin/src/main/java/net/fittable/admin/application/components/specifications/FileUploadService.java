package net.fittable.admin.application.components.specifications;

import net.fittable.domain.authentication.Member;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadMultipartFile(Member member, MultipartFile file);

}
