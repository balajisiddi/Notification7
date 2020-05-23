package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.school.UserInterests;

@Repository
public interface UserInterestsRepository extends JpaSpecificationExecutor<UserInterests>, JpaRepository<UserInterests, Serializable> {

	List<UserInterests> findInterestsByUserAndAuthority(Long userId, Authority authority);


	
}
