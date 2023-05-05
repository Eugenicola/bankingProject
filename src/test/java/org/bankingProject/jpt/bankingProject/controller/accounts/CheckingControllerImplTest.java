package org.bankingProject.jpt.bankingProject.controller.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.repositories.accounts.AccountRepository;
import org.bankingProject.jpt.bankingProject.repositories.accounts.CheckingRepository;
import org.bankingProject.jpt.bankingProject.repositories.users.AccountHolderRepository;
import org.bankingProject.jpt.bankingProject.securityConfig.repositories.UserRepository;
import org.bankingProject.jpt.bankingProject.utils.Address;
import org.bankingProject.jpt.bankingProject.utils.Money;
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

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.bankingProject.jpt.bankingProject.models.accounts.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class CheckingControllerImplTest {
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
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("John Doe");
        accountHolder.setUsername("johndoe");
        accountHolder.setPassword("password");
        accountHolder.setBirthDate(new Date());
        Address primaryAddress = new Address("123 Main St", "Anytown", "CA", "12345");
        Address mailingAddress = new Address("456 Oak St", "Anytown", "CA", "12345");
        accountHolder.setPrimaryAddress(primaryAddress);
        accountHolder.setMailingAddress(mailingAddress);
        accountHolderRepository.save(accountHolder);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addNewCheckingAccountTest() throws Exception {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("John Doe");
        accountHolder.setUsername("johndoe");
        accountHolder.setPassword("1234");
        accountHolder.setBirthDate(new Date());
        Address primaryAddress = new Address("123 Main St", "Anytown", "CA", "12345");
        Address mailingAddress = new Address("456 Oak St", "Anytown", "CA", "12345");
        accountHolder.setPrimaryAddress(primaryAddress);
        accountHolder.setMailingAddress(mailingAddress);
        accountHolderRepository.save(accountHolder);
        Checking account = new Checking();
        account.setBalance(new Money(new BigDecimal(1000)));
        account.setPrimaryOwner(accountHolder);
        account.setSecretKey("Euge");
        account.setCreationDate(new Date(2023/03/05));
        account.setStatus(ACTIVE);
        String body = objectMapper.writeValueAsString(account);
        MvcResult mvcResult = mockMvc.perform(post("/api/addCheckingAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Euge"));
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteAccountTest() throws Exception {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("John Doe");
        accountHolder.setUsername("johndoe");
        accountHolder.setPassword("1234");
        accountHolder.setBirthDate(new Date());
        Address primaryAddress = new Address("123 Main St", "Anytown", "CA", "12345");
        Address mailingAddress = new Address("456 Oak St", "Anytown", "CA", "12345");
        accountHolder.setPrimaryAddress(primaryAddress);
        accountHolder.setMailingAddress(mailingAddress);
        accountHolderRepository.save(accountHolder);
        Checking account = new Checking();
        account.setBalance(new Money(new BigDecimal(1000)));
        account.setPrimaryOwner(accountHolder);
        account.setSecretKey("Euge");
        account.setCreationDate(new Date(2023/03/05));
        account.setStatus(ACTIVE);
        accountRepository.save(account);
        String body = objectMapper.writeValueAsString(account.getId());
        String body = objectMapper.writeValueAsString(account);
        MvcResult mvcResult = mockMvc.perform(post("/api/addCheckingAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Euge"));

        MvcResult mvcResult1 = mockMvc.perform(delete("/api/deleteAccount/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)


        assertThat(accountRepository.findById(account.getId())).isEmpty();
    }

}

