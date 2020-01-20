package net.fittable.admin.application;

import net.fittable.admin.infrastructure.components.generic.StudioOwnerIDGenerator;
import net.fittable.admin.infrastructure.repositories.AdminMemberRepository;
import net.fittable.admin.infrastructure.repositories.StudioOwnerMemberRepository;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberManagementService {

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private StudioOwnerMemberRepository studioOwnerMemberRepository;

    @Autowired
    private StudioOwnerIDGenerator idGenerator;

    @Transactional
    public StudioOwnerMember generateNewMember(ContactInformation contactInformation) {
        StudioOwnerMember member = new StudioOwnerMember();

        member.setOwnerContactInformation(contactInformation);

        this.idGenerator.setLoginId(member);
        String generatedPassword = this.idGenerator.setPassword(member);

        return studioOwnerMemberRepository.save(member);
    }
}
