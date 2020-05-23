package com.sectorseven.repository.student;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Serializable>, JpaSpecificationExecutor<Student>{

	Student findByMobile(Long mbl);

	Student findByEmail(String emailID);

	Student findByUserName(String userName);

	List<Student> findByParents(Parents parents);
	
	@Query(value="SELECT * FROM student WHERE school_teacher=?1 ORDER BY date_created DESC LIMIT ?2 OFFSET ?3",nativeQuery = true)
	List<Student> findsBySchoolTeacher(Long schoolTeacher,Integer limit,Integer offset);


	List<Student> findBySchoolTeacher(SchoolTeacher schoolTeacher);


	
}
