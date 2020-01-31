package net.fittable.admin.application;

import lombok.extern.slf4j.Slf4j;
import net.fittable.admin.application.components.specifications.FileUploadService;
import net.fittable.domain.authentication.Member;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@Slf4j
public class LocalFileUploadService implements FileUploadService {
    private static final String LOCAL_FILE_DIR = "/Users/wheejuni/Documents/fitable-static";
    private static final String LOCAL_HOST_NAME = "http://local.api.fitable.net/static/";

    @Override
    public String uploadMultipartFile(Member member, MultipartFile file) {
        InputStream image = null;

        try(InputStream is = file.getInputStream()){
            image = is;
        } catch (IOException e) {
            log.error("error while reading uploaded file", e);
        }

        try {
            byte[] imagebytes = new byte[image.available()];
            image.read(imagebytes);

            File targetFile = new File(LOCAL_FILE_DIR + "/" + member.getLoginId() + "/" + file.getOriginalFilename());
            OutputStream writeFile = new FileOutputStream(targetFile);

            writeFile.write(imagebytes);
        } catch (IOException e) {
            log.error("error while writing image", e);
        }

        return LOCAL_HOST_NAME + "/" + member.getLoginId() + "/" + file.getOriginalFilename();
    }
}
