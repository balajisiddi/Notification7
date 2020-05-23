package com.sectorseven.repository.School;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;

@Repository
public interface SchoolTeacherRepository extends JpaRepository<SchoolTeacher, Serializable>, JpaSpecificationExecutor<SchoolTeacher> {

	List<SchoolTeacher> findByEmailOrMobile(String email, Long mobile);

	@Query(value="select * from school_teacher where id not in (?3) and (email = ?1 or mobile= ?2)", nativeQuery = true)
	List<SchoolTeacher> findByEmailOrMobileAndIdNotIn(String email, Long contactNo, Long id);

	SchoolTeacher findByEmail(String email);

	List<SchoolTeacher> findByStatus(Status active);

	SchoolTeacher findById(Long id);

	List<SchoolTeacher> findByStatusAndSchool(Status active, School school);

	@Query(value = "select * from school_teacher where first_name=?1", nativeQuery=true)
	SchoolTeacher findByFirstName(String schoolTeacherFirstName);
	
}
