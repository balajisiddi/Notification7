package com.sectorseven.repository.admin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandSupply;

@Repository
public interface DemandSupplyRepository extends JpaRepository<DemandSupply, Serializable>, JpaSpecificationExecutor<DemandSupply>{

	//List<DemandSupply> findAllByCountry(String country);

	//@Query(value="select salary from demand_supply where country=?1 ",nativeQuery=true)
	List<DemandSupply> findAllByLabel(DemandLabels country);

	List<DemandSupply> findAllByCareerSubCat(CareerSubcategory subcategory);

	@Query(value="select salary from demand_supply where subcat_id=?1 AND label=?2 ",nativeQuery=true)
	List<Double> findAllByCareerSubCatAndLabel(Long subcategory, Long long1);
	
	@Query(value="select manpower from demand_supply where subcat_id=?1 AND label=?2 ",nativeQuery=true)
	List<Double> findAllManpowerByCareerSubCatAndCountry(Long subcategory, Long long1);


	@Query(value="select color from demand_supply where year=?1",nativeQuery=true)
	Long getDemandSupplyBycareerByYear(Long year);

	@Query(value="select manpower from demand_supply where subcat_id=?1 AND year=?2",nativeQuery=true)
	List<Double> findAllByCareerSubCatAndYear(Long id, Long id2);

	@Query(value="select salary from demand_supply where subcat_id=?1 AND year=?2",nativeQuery=true)
	List<Double> findAllBySalaryCareerSubCatAndYear(Long id, Long id2);


}
