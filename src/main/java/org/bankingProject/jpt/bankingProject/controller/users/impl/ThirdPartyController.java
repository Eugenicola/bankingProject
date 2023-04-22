package org.bankingProject.jpt.bankingProject.controller.users.impl;

import jakarta.validation.Valid;
import org.bankingProject.jpt.bankingProject.models.Users.ThirdParty;
import org.bankingProject.jpt.bankingProject.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyServiceInterface thirdPartyServiceinterface;

    @PostMapping("/addThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addNewThirdParty(@RequestBody @Valid ThirdParty user){
        return thirdPartyServiceinterface.addThirdParty(user);
    }
}
