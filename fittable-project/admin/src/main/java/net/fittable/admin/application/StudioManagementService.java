package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioManagementService {

    @Autowired
    private StudioRepository studioRepository;

    public void addNewStudio(Studio studio, StudioOwnerMember member) {
        studio.setOwner(member);

        studioRepository.save(studio);
    }

    public List<Studio> getAllOwnedStudios(StudioOwnerMember member) {
        return studioRepository.findByOwner(member);
    }
}
