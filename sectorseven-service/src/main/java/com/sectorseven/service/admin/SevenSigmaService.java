package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;

public interface SevenSigmaService {
	
	
	SevenSigma saveSigmas(SevenSigma sevenSigma);
	
	List<SevenSigma> getAllSigmas();
	
	SevenSigma findBySigmaId(long id);
	
	void updateSigma(SevenSigma sevenSigma);
	
	List<SevenSigma> findBysigmaName(String sigmaName);
	
	SevenSigmaDetails saveSigmaDetails(SevenSigmaDetails sevenSigmaDetails,Long sigmaId);
	
	List<SevenSigmaDetails> getAllSigmaDetails();
	
	void updateSigmaDetails(SevenSigmaDetails sevenSigmaDetails);

	List<SevenSigma> findBySigmaStatus(Status active);

	SevenSigma findBysubSigmaId(long subSigmaId);

	List<SevenSigmaDetails> findAllBySigmaId(long sigmaId);

	SevenSigmaDetails findBySigmaDetailsId(long docId);

	void saveDocument(SevenSigmaDetails document);

	SevenSigma findSigmaById(long sigmaId) throws Exception;

	SevenSigmaDetails findSigmaDetailById(long sigmaDetailId) throws Exception;

	SevenSigma findSigmaByIdAndImgPathAndImgName(long sigmaId, String imgPath, String imgName) ;

}
