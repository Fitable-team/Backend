package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.AdminMemberRepository;
import net.fittable.admin.infrastructure.repositories.StudioOwnerMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberManagementService {

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private StudioOwnerMemberRepository studioOwnerMemberRepository;


}
