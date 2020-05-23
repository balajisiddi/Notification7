package com.sectorseven.repository.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.DemandLabels;

@Repository
public interface DemandLabelsRepository extends JpaSpecificationExecutor<DemandLabels>, JpaRepository<DemandLabels, Serializable> {

	@Query(value = "select * from demand_labels as dl where dl.label=?1", nativeQuery=true)
	DemandLabels findDemandLableByLableName(String label);
	
	
}
