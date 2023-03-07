package com.dsi.ebankback;

import com.dsi.ebankback.entities.*;
import com.dsi.ebankback.enums.AccountStatus;
import com.dsi.ebankback.enums.TypeOperation;
import com.dsi.ebankback.repositories.AccountOperationRepository;
import com.dsi.ebankback.repositories.BankAccountRepository;
import com.dsi.ebankback.repositories.CustomerRepository;
import com.dsi.ebankback.repositories.RuleRepository;
import com.dsi.ebankback.services.CustomerService;
import com.dsi.ebankback.services.RuleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankBackApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,

							AccountOperationRepository accountOperationRepository, CustomerService customerService,
							RuleService ruleService) {
		return args -> {
			Stream.of("Atif", "Zaid", "Chaimae").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customer.setPassword(passwordEncoder().encode("123456"));
				customerRepository.save(customer);
			});
			ruleService.addNewRule(new Rule((long)1, "USER"));
			ruleService.addNewRule(new Rule((long)2, "ADMIN"));
			ruleService.addNewRule(new Rule((long)3, "CUSTOMER_MANAGER"));
			ruleService.addNewRule(new Rule((long)4, "PRODUCT_MANAGER"));

			customerService.addRuleToUser((long)2, "USER");
			customerService.addRuleToUser((long)1, "ADMIN");
			customerService.addRuleToUser((long)1, "USER");
			customerService.addRuleToUser((long)3, "PRODUCT_MANAGER");
			customerRepository.findAll().forEach(cust -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setBalance(Math.random()* 90000);
				currentAccount.setCreateDate(new Date());
				currentAccount.setAccountStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				currentAccount.setRib(UUID.randomUUID().toString());
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setBalance(Math.random()* 90000);
				savingAccount.setCreateDate(new Date());
				savingAccount.setAccountStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(4.8);
				savingAccount.setRib(UUID.randomUUID().toString());
				bankAccountRepository.save(savingAccount);
			});
			bankAccountRepository.findAll().forEach(acou -> {
				for (int i = 0; i < 5; i++) {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setAmount(Math.random()*90000);
					accountOperation.setDateOperatio(new Date());
					accountOperation.setTypeOperation(Math.random() > 0.5 ? TypeOperation.DEBIT: TypeOperation.CREDIT);
					accountOperation.setBankAccount(acou);
					accountOperationRepository.save(accountOperation);
				}
			});
		};
	}

}
