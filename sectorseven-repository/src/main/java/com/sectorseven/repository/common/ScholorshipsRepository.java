package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Scholorships;

@Repository
public interface ScholorshipsRepository extends JpaSpecificationExecutor<Scholorships>, JpaRepository<Scholorships, Serializable>{

	List<Scholorships> findAllByCareerSubCatAndCountry(CareerSubcategory careerSubCat, Country india);

	List<Scholorships> findAllByCountry(Country india);

}
