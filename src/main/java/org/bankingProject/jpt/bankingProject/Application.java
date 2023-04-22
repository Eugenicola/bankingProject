package org.bankingProject.jpt.bankingProject;

import org.bankingProject.jpt.bankingProject.models.Users.AccountHolder;
import org.bankingProject.jpt.bankingProject.models.accounts.Account;
import org.bankingProject.jpt.bankingProject.models.accounts.Checking;
import org.bankingProject.jpt.bankingProject.models.accounts.Status;
import org.bankingProject.jpt.bankingProject.securityConfig.models.Role;
import org.bankingProject.jpt.bankingProject.securityConfig.models.User;
import org.bankingProject.jpt.bankingProject.securityConfig.services.impl.UserService;
import org.bankingProject.jpt.bankingProject.services.accounts.impl.CheckingServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

		@Bean
		PasswordEncoder passwordEncoder() {
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}

		@Bean
		CommandLineRunner run(UserService userService, CheckingServiceImpl checkingService) {
			return args -> {
				userService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));
				userService.saveRole(new Role(null, "ROLE_ADMIN"));
				userService.saveRole(new Role(null, "ROLE_THIRD_PARTY"));

				userService.saveUser(new User(null, "John Doe", "john", "1234", new HashSet<>(), new HashSet<>(), new ArrayList<>()));
				userService.saveUser(new User(null, "James Smith", "james", "1234",new HashSet<>(),  new HashSet<>(), new ArrayList<>()));
				userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new HashSet<>(), new HashSet<>(), new ArrayList<>()));
				userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new HashSet<>(), new HashSet<>(), new ArrayList<>()));

				userService.addRoleToUser("john", "ROLE_ACCOUNT_HOLDER");
				userService.addRoleToUser("james", "ROLE_ADMIN");
				userService.addRoleToUser("jane", "ROLE_THIRD_PARTY");

			};
		}

}
