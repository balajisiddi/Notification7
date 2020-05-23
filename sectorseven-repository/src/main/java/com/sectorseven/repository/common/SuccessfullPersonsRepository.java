package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.SuccessPersons;

@Repository
public interface SuccessfullPersonsRepository extends JpaSpecificationExecutor<SuccessPersons>, JpaRepository<SuccessPersons, Serializable>{

	List<SuccessPersons> findAllByCareerSubCat(CareerSubcategory careerSubCat);

}
