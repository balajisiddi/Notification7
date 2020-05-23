package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Responsibilities;

@Repository
public interface RolesAndResponseRepository extends JpaSpecificationExecutor<Responsibilities>, JpaRepository<Responsibilities, Serializable> {

	List<Responsibilities> findByCareerSubCat(CareerSubcategory careerSubCat);

	
}
