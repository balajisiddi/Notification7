package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.ActivityLog;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Feedback;

@Repository
public interface FeedbackRepository extends JpaSpecificationExecutor<Feedback>, JpaRepository<Feedback, Serializable>{
	
}
