package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.jrp.pma.dao.UserAccountRepository;
import com.jrp.pma.entities.UserAccount;

public class UserAccountService {

	@Autowired
    UserAccountRepository userAccountRepository;
	
	
	public UserAccount save(UserAccount userAccount) {
		return userAccountRepository.save(userAccount);
	}



	
}