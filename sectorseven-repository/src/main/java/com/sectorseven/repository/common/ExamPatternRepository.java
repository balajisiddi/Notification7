package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.ExamPattern;

@Repository
public interface ExamPatternRepository extends JpaSpecificationExecutor<ExamPattern>, JpaRepository<ExamPattern, Serializable> {

	List<ExamPattern> findByCareerSubCat(CareerSubcategory careerSubCat);

	
}
