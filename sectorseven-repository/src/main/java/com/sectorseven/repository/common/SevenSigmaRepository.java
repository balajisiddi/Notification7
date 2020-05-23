package com.sectorseven.repository.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.SevenSigma;

@Repository
public interface SevenSigmaRepository extends JpaSpecificationExecutor<SevenSigma>, JpaRepository<SevenSigma, Serializable> {

	List<SevenSigma> findBysigmaName(String sigmaName);

	List<SevenSigma> findByStatus(Status active);

	SevenSigma findSigmaByIdAndSigmaIconPathAndSigmaIconName(long sigmaId, String imgPath, String imgName);


}
