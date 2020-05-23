package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.ServerBroke;

	@Repository
	public interface ServerBrokeRepository extends JpaSpecificationExecutor<ServerBroke>, JpaRepository<ServerBroke, Serializable> {
		
		@Query(value="SELECT * FROM server_broke  ORDER BY date_created DESC LIMIT 1",nativeQuery=true)
		ServerBroke findServerBrokeFlag();
	
	}
