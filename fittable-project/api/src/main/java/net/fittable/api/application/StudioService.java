package net.fittable.api.application;

import net.fittable.api.application.repositories.StudioRepository;
import net.fittable.api.application.repositories.TownRepository;
import net.fittable.domain.business.Studio;
import net.fittable.domain.premises.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private TownRepository townRepository;

    public Studio getById(Long id) {
        return studioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 ID의 스튜디오가 없음"));
    }

    @Transactional
    public List<Studio> findByLocaion(String town) {
        Location targetLocation = townRepository.findByName(town).orElseThrow(() -> new NoSuchElementException("해당 지명이 등록되지 않았음"));

        return studioRepository.findByTown(targetLocation);
    }
}
