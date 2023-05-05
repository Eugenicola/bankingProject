package org.bankingProject.jpt.bankingProject.controller.users.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.UserRepository;
import org.bankingProject.jpt.bankingProject.utils.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountHolderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAddNewAccountHolder() throws Exception {
            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setName("John Doe");
            accountHolder.setUsername("johndoe");
            accountHolder.setPassword("password");
            accountHolder.setBirthDate(new Date());
            Address primaryAddress = new Address("123 Main St", "Anytown", "CA", "12345");
            Address mailingAddress = new Address("456 Oak St", "Anytown", "CA", "12345");
            accountHolder.setPrimaryAddress(primaryAddress);
            accountHolder.setMailingAddress(mailingAddress);

            String body = objectMapper.writeValueAsString(accountHolder);
            MvcResult mvcResult = mockMvc.perform(post("/api/addAccountHolder")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
            .andExpect(status().isCreated()).andReturn();
            assertTrue(mvcResult.getResponse().getContentAsString().contains("John Doe"));
    }

    @Test
    void checkBalance() {
    }

    @Test
    void accHolderTransference() {
    }
}