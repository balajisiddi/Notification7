package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;

@Repository
public interface CoursesRepositoty extends JpaSpecificationExecutor<InstitutionCourses>, JpaRepository<InstitutionCourses, Serializable> {

	List<InstitutionCourses> findByInstitution(Institutions institution);

	@Query(value = "select * from institution_courses as ic where ic.institution=?1 and ic.subcat_id=?2", nativeQuery= true)
	List<InstitutionCourses> findByInstitutionAndSubcatId(Institutions institution, CareerSubcategory careerSubcategory);
	
	
}
