package com.ibm.msreskill.accountloginms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.msreskill.accountloginms.model.JWTReqwUserAccountInfo;
import com.ibm.msreskill.accountloginms.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

}
