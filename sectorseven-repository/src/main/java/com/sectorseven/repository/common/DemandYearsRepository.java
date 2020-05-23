package com.sectorseven.repository.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.DemandYears;

@Repository
public interface DemandYearsRepository extends JpaSpecificationExecutor<DemandYears>, JpaRepository<DemandYears, Serializable> {

	@Query(value = "select * from demand_years as dy where dy.year=?1", nativeQuery=true)
	DemandYears findDemandYearByYear(String year);
	
}
