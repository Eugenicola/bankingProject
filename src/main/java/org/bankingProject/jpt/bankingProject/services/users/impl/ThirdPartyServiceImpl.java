package org.bankingProject.jpt.bankingProject.services.users.impl;


import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;
import org.bankingProject.jpt.bankingProject.repositories.users.ThirdPartyRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyServiceInterface {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ThirdParty addThirdParty(ThirdParty user){
        Role role = roleRepository.findByName("ROLE_THIRD_PARTY");
        user.getRoles().add(role);
        return thirdPartyRepository.save(user);
    }
}
