package com.sectorseven.repository.admin;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Serializable>, JpaSpecificationExecutor<Administrator>{

	
	Administrator findByUsername(String username);

	Administrator findByEmail(String email);

	    @Modifying
	    @Query(value = "update administrator set password=?2 where id=?1 ", nativeQuery = true)
	    int updatePassword(Long collegeUserId, String password);

	    Administrator findByEmailAndUsername(String email, String username);

	
}
