package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.domain.business.Studio;
import net.fittable.domain.premises.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudioSearchService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private TownRepository townRepository;

    public List<Town> getTownList() {
        return townRepository.findAll();
    }

    @Transactional
    public List<Studio> findByTownName(String townName) {
        Town targetTown = townRepository.findByName(townName).orElseThrow(() -> new NoSuchElementException("부정확한 동네 이름입니다."));

        return studioRepository.findByTown(targetTown);
    }
}
