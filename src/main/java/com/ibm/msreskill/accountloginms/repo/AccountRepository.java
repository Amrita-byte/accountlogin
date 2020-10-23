package com.ibm.msreskill.accountloginms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.msreskill.accountloginms.model.AccountInfo;

public interface AccountRepository extends JpaRepository<AccountInfo, String>{

}
