package org.bankingProject.jpt.bankingProject.services.users.impl;

import org.bankingProject.jpt.bankingProject.models.Users.Admin;
import org.bankingProject.jpt.bankingProject.repositories.users.AdminRepository;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminServiceInterface {

   @Autowired
    private AdminRepository adminRepository;

    public Admin addAdminUser(Admin user){
        return adminRepository.save(user);
    }



}
