package com.sectorseven.repository.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.DemandColors;

@Repository
public interface DemandColorsRepository extends JpaSpecificationExecutor<DemandColors>, JpaRepository<DemandColors, Serializable> {

	@Query(value="select name from demand_colors where id=?1",nativeQuery=true)
	String findColorById(Long id);

	@Query(value = "select * from demand_colors where name=?1", nativeQuery=true)
	DemandColors findDemandColorByColorName(String color);
	
}
