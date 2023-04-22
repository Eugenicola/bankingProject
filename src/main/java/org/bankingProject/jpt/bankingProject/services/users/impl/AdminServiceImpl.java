package org.bankingProject.jpt.bankingProject.services.users.impl;

import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.repositories.users.AdminRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.RoleRepository;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminServiceInterface {

   @Autowired
   private AdminRepository adminRepository;

   @Autowired
   private RoleRepository roleRepository;

    public Admin addAdminUser(Admin user){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.getRoles().add(role);
        return adminRepository.save(user);
    }



}
