package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;

@Repository
public interface SevenSigmaDetailsRepository extends JpaSpecificationExecutor<SevenSigmaDetails>, JpaRepository<SevenSigmaDetails, Serializable> {

	List<SevenSigmaDetails> findBySevenSigma(SevenSigma sevenSigma);
	
	@Query(value="select * from seven_sigma_details  where seven_sigma=?1 AND type=?2 LIMIT ?3 OFFSET ?4 ", nativeQuery = true)
	List<SevenSigmaDetails> findBySevenSigmaAndType(Long sevenSigma,String type,Integer limit,Integer offset);



}
