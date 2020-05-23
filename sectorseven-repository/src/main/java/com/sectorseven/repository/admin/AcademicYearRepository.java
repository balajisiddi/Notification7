package com.sectorseven.repository.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AcademicYear;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Serializable>, JpaSpecificationExecutor<AcademicYear>{

	AcademicYear findByStatus(Status active);

	List<AcademicYear> findAllYearsByStatus(Status active);
		
	
	@Query(value = "select * from academic_year where name=?1", nativeQuery=true)
	AcademicYear findByAcadamicYearName(String acadamicYearName);
}
