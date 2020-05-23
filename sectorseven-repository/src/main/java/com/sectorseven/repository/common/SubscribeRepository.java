package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.SubscribedCareers;

@Repository
public interface SubscribeRepository extends JpaSpecificationExecutor<SubscribedCareers>, JpaRepository<SubscribedCareers, Serializable> {

	List<SubscribedCareers> findSubscribedByAuthorityAndUserAndCareerSubcat(Authority authority, Long userId,
			CareerSubcategory subCat);

	SubscribedCareers findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(Authority authority,
			Long userId, CareerSubcategory subCat, Subscribe subscribed);

	List<SubscribedCareers> findSubscribedByAuthorityAndUser(Authority authority, Long userId);

	List<SubscribedCareers> findSubscribedByAuthorityAndUserAndSubscribe(Authority authority, Long userId,
			Subscribe subscribed);


	
}
