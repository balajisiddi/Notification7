package com.sectorseven.repository.common;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.TimeSpent;

	@Repository
	public interface TimeSpentRepository extends JpaSpecificationExecutor<TimeSpent>, JpaRepository<TimeSpent, Serializable>{



}
