package com.sectorseven.repository.common;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.common.OtpToken;

@Repository
public interface OtpRepository extends JpaSpecificationExecutor<OtpToken>, JpaRepository<OtpToken, Serializable>{

	OtpToken findByUserAndAuthorityAndStatus(Long user,Authority authority, Status active);

	 
	 @Modifying
	 @Transactional
	 @Query(value = "update otp_token set status=?1 where user=?2 AND authority=?3", nativeQuery = true)
	 int updateOtpStatusByUserAndAuthority(int inactive,Long user,Long authority);






}
