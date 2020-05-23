package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Institutions;

@Repository
public interface InstitutionRepository extends JpaSpecificationExecutor<Institutions>, JpaRepository<Institutions, Serializable> {

	List<Institutions> findByCareerSubCat(CareerSubcategory careerSubCat);

	List<Institutions> findByCareerSubCatAndCountry(CareerSubcategory careerSubCat, Country country);

	
	@Query(value = "SELECT * FROM institutions AS i WHERE i.institute_url=?1 GROUP BY institute_url", nativeQuery=true)
	Institutions findByInstituteUrl(String instituteUrl);
	
}
