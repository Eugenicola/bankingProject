package org.bankingProject.jpt.bankingProject.services.users.impl;


import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.ThirdPartyRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.services.impl.UserService;
import org.bankingProject.jpt.bankingProject.services.accounts.impl.AccountServiceImpl;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Collection;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyServiceInterface {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepository

    public ThirdParty addThirdParty(ThirdParty thirdParty){
        Role role = roleRepository.findByName("ROLE_THIRD_PARTY");
        thirdParty.getRoles().add(role);
        ThirdParty savedUser = thirdPartyRepository.save(thirdParty);
        userService.loadUserByUsername(savedUser.getUsername());
        return savedUser;
    }
}
