package org.bankingProject.jpt.bankingProject.controller.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.utils.Address;
import org.bankingProject.jpt.bankingProject.utils.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

import static org.bankingProject.jpt.bankingProject.models.accounts.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountControllerTest {
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        AccountHolder accountHolder1 = new AccountHolder(new Date(16/02/1987), (new Address("Gine i partagas 33", "Barcelona","Barcelona", "08003")),
                (new Address("Gine i partagas 33","Barcelona","Barcelona", "08003")));
        Checking checking1 = new Checking("Cat", new BigDecimal(250), new BigDecimal(12), ACTIVE );
        checkingRepository.save(checking1);
        accountHolderRepository.save(accountHolder1);
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/api/deleteChecking/1"))
                .andExpect(status().isNoContent());

        Assertions.assertNull(checkingRepository.findById(1).orElse(null));
    }

    @Test
    void getAccountList() throws Exception {
        Checking checking2 = new Checking("Cat", new BigDecimal(250), new BigDecimal(12), ACTIVE );
        String body = objectMapper.writeValueAsString(checking2);
        MvcResult mvcResult = mockMvc.perform(get("/accountList")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().);
    }
    @Test
    void deleteAccount_Valid_Id_ReturnsNoContent() throws Exception {
        // Create an account to delete
        Account account = new Account(new Money(new BigDecimal(100)), accountHolder1);
        accountRepository.save(account);

        // Perform the delete request
        mockMvc.perform(delete("/api/deleteAccount/{id}", account.getId()))
                .andExpect(status().isNoContent())
                .andExpect(content().string(" "));
        // Check that the account was deleted
        assertFalse(accountRepository.existsById(account.getId()));
    }
   @Test
    void getMyAccountsByOwner() throws Exception {
       MvcResult mvcResult = mockMvc.perform(get("/myaccounts").with(httpBasic("Erin Carr", "ironhack")))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
               .andReturn();
       //System.out.printin(mvcResult.getResponse().getContentAsString());
       assertFalse(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));
   }
    void getMyAccountsByOwner_withAuth_perform() throws Exception {
           MvcResult mvcResult = mockMvc.perform(get("/myaccounts").with(httpBasic("Erin Carr", "ironhack")))
                   .andExpect(status().is0k())
                   .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                   .andReturn();
           //System.out.printin(mvcResult.getResponse().getContentAsString());
           assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));
       }
        void getMyAccountsByOwner_withBadAuth_perform() throws Exception {
        mockMvc.perform(get (" /myaccounts") .with(httpBasic("Erin carr"', "ironhackS"))).andExpect (statusU. isUnauthorized/
       }

}