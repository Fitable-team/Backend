package net.fittable.api.view;

import net.fittable.api.application.repositories.StudioRepository;
import net.fittable.domain.business.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private StudioRepository studioRepository;

    @GetMapping(path = "/{studioId}")
    public Studio studioIdApi(@PathVariable String studioId) {
        Long id = Long.parseLong(studioId);

        return studioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ID에 맞는 스튜디오가 없습니다."));
    }
}
