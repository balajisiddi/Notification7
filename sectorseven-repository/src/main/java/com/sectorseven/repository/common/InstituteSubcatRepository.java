package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.InstituteSubcat;
@Repository
public interface InstituteSubcatRepository extends JpaSpecificationExecutor<InstituteSubcat>, JpaRepository<InstituteSubcat, Serializable> {
	
	@Query(value = "select * from institute_subcat as i where i.subcat_id=?1 AND i.country=?2 GROUP BY institution_id", nativeQuery=true)
	List<InstituteSubcat> findByCareerSubCatIdAndCountry(CareerSubcategory careerSubCat, int country);
	
	
}