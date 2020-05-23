package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Hub;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.CoCreationHub;

@Repository
public interface CoCreationHubRepository extends JpaSpecificationExecutor<CoCreationHub>, JpaRepository<CoCreationHub, Serializable>{

	List<CoCreationHub> findByhubName(String hubName);

	List<CoCreationHub> findHubByStatus(Status active);

	List<CoCreationHub> findAllByHub(Hub yes);

	@Query(value = "select * from co_creation_hub as ae where ae.hub_name=?1 and ae.status=?2", nativeQuery= true)
	CoCreationHub findByHubNameAndStatus(String hubZoneName, Status active);


}
