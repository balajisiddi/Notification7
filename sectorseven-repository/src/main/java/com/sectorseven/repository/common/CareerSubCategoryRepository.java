package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;

@Repository
public interface CareerSubCategoryRepository extends JpaSpecificationExecutor<CareerSubcategory>, JpaRepository<CareerSubcategory, Serializable> {

	List<CareerSubcategory> findBySubCategoryName(String subCatName);

	List<CareerSubcategory> findByStatus(Status active);

	List<CareerSubcategory> findByTrending(Trending active);
	
	List<CareerSubcategory> findByCareerCategory(CareerCategory catid);

	@Query(value="select career_subcategory from career_subcategory  where career_category in :catIds",nativeQuery=true)
	List<CareerSubcategory> findByCategoryIds(@Param("catIds") List<Long> catIds);
	
	
	@Query(value = "select * from career_subcategory as cs where cs.sub_category_name=?1", nativeQuery=true)
	CareerSubcategory findByCareerSubCategoryName(String subCatName);

}
