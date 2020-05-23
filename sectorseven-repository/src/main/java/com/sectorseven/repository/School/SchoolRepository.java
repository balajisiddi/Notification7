package com.sectorseven.repository.School;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Serializable>, JpaSpecificationExecutor<School>{

	List<School> findByStatus(Status active);

	School findByEmailAndStatus(String email, Status active);

	List<School> findByEmailOrContactNoAndStatus(String email, long contactNo, Status active);

	List<School> findByEmailOrContactNoOrSchoolCode(String email, Long contactNo, String schoolCode);

	@Query(value="select * from school where id not in (?4) and (email = ?1 or contact_no= ?2 or school_code = ?3 )", nativeQuery = true)
	List<School> findByEmailOrContactNoOrSchoolCodeAndIdNotIn(String email, Long contactNo, String schoolCode, Long id);

	School findById(Long id);

	@Query(value = "select * from school where school_name=?1", nativeQuery = true)
	School findBySchoolName(String schoolName);
	
}
