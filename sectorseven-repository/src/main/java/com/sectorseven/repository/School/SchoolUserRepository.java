package com.sectorseven.repository.School;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.school.SchoolUsers;

@Repository
public interface SchoolUserRepository extends JpaSpecificationExecutor<SchoolUsers>, JpaRepository<SchoolUsers, Serializable>{

	SchoolUsers findByUserName(String username);

	List<SchoolUsers> findByEmailOrPhone(String email, Long contactNo);

	@Query(value="select * from school_users where id not in (?4) and (email = ?1 or phone= ?2 )", nativeQuery = true)
	List<SchoolUsers> findByEmailOrContactNoAndIdNotIn(String email, Long contactNo, Long id);

	SchoolUsers findSchoolUserByEmail(String email);

	
}
