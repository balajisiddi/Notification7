package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.school.UserInterests;

@Repository
public interface StudentInterestRepository extends JpaSpecificationExecutor<UserInterests>, JpaRepository<UserInterests, Serializable>{

	List<UserInterests> findByUserAndAuthority(Long userId, Authority authoriti);

	List<UserInterests> findAllByUserAndAuthority(Long userId, Authority authority);

	/*@Transactional
	@Modifying
	@Query(value="delete from user_interests where user=?1 AND authority=?2 ",nativeQuery=true)
	void deleteAllByUserAndAuthority(Long userId, Authority authority);
*/
	@Transactional
	@Modifying
    @Query(value="Delete from user_interests where user = :user AND authority=:authority",nativeQuery=true)
    void deleteAllByUserAndAuthority(@Param("user") Long user,@Param("authority") Long authority);

}
