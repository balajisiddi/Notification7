package com.sectorseven.repository.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.Parents;

@Repository
public interface ParentsRepository extends JpaSpecificationExecutor<Parents>, JpaRepository<Parents, Serializable> {

	List<Parents> findByFatherMobileOrFatherEmail(Long fatherMobile, String fatherEmail);

	List<Parents> findByFatherEmailOrFatherMobileAndIdNotIn(String fatherEmail, Long fatherMobile, Long id);

	Parents findByUserName(String username);

	
	
}
