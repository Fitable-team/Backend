package net.fittable.admin.application;

import net.fittable.admin.infrastructure.components.generic.StudioOwnerIDGenerator;
import net.fittable.admin.infrastructure.repositories.AdminMemberRepository;
import net.fittable.admin.infrastructure.repositories.StudioOwnerMemberRepository;
import net.fittable.domain.authentication.Member;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

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

    @Transactional
    public StudioOwnerMember changeStudioMemberLoginId(String loginId, Member member) {
        if(member.getAuthority() != MemberAuthority.STUDIO_OWNER) {
            throw new IllegalArgumentException("스튜디오 회원에게만 제공되는 기능입니다.");
        }
        StudioOwnerMember targetMember = studioOwnerMemberRepository.findByLoginId(member.getLoginId())
                .orElseThrow(() -> new NoSuchElementException("해당 아이디로 검색된 스튜디오 회원이 없습니다."));

        targetMember.setLoginId(loginId);

        return studioOwnerMemberRepository.save(targetMember);
    }
}
