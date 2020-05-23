package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.CareerCategory;

@Repository
public interface CareerCategoryRepository extends JpaSpecificationExecutor<CareerCategory>, JpaRepository<CareerCategory, Serializable>{

	List<CareerCategory> findByCategoryName(String catName);

	List<CareerCategory> findByStatus(Status active);
	
	@Query(value = "select * from career_category as cc where cc.category_name=?1", nativeQuery=true)
	CareerCategory findByCategouryName(String categoryName);

}
