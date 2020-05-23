package com.sectorseven.service.admin.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.common.SevenSigmaDetailsRepository;
import com.sectorseven.repository.common.SevenSigmaRepository;
import com.sectorseven.service.admin.SevenSigmaService;

@Transactional
@Service
public class SigmaServiceImpl  implements SevenSigmaService{
	
	@Autowired
	private SevenSigmaRepository sevenSigmaRepository;
	
	@Autowired
	private SevenSigmaDetailsRepository sevenSigmaDetailsRepository;

	@Override
	public SevenSigma saveSigmas(SevenSigma sevenSigma) {
		return sevenSigmaRepository.save(sevenSigma);	
		
	}

	@Override
	public List<SevenSigma> getAllSigmas() {
		return sevenSigmaRepository.findAll();
	}

	@Override
	public SevenSigma findBySigmaId(long id) {
		return sevenSigmaRepository.findOne(id);
	}

	@Override
	public void updateSigma(SevenSigma sevenSigma) {
		sevenSigmaRepository.save(sevenSigma);		
	}

	@Override
	public List<SevenSigma> findBysigmaName(String sigmaName) {
		return sevenSigmaRepository.findBysigmaName(sigmaName);
	}

	@Override
	public SevenSigmaDetails saveSigmaDetails(SevenSigmaDetails sevenSigmaDetails,Long sigmaId) {
			if(sevenSigmaDetails.getType()!=null){
				sevenSigmaDetails.setVideoType(VideoType.Private);
				if(sevenSigmaDetails.getType().equals("audio/mpeg" )){
					sevenSigmaDetails.setType("audio/mp3");
				}
				
			}
			SevenSigma sevenSigma =findBySigmaId(sigmaId);
			sevenSigmaDetails.setSevenSigma(sevenSigma);
			return sevenSigmaDetailsRepository.save(sevenSigmaDetails);		
	}

	@Override
	public List<SevenSigmaDetails> getAllSigmaDetails() {
		return sevenSigmaDetailsRepository.findAll();
	}

	@Override
	public void updateSigmaDetails(SevenSigmaDetails sevenSigmaDetails) {
		sevenSigmaDetailsRepository.save(sevenSigmaDetails);	
	}

	@Override
	public List<SevenSigma> findBySigmaStatus(Status active) {
		return sevenSigmaRepository.findByStatus(active);
	}

	@Override
	public SevenSigma findBysubSigmaId(long sigmaId) {
		return sevenSigmaRepository.findOne(sigmaId);
	}

	@Override
	public List<SevenSigmaDetails> findAllBySigmaId(long sigmaId) {
		SevenSigma sigma=sevenSigmaRepository.findOne(sigmaId);
		return sevenSigmaDetailsRepository.findBySevenSigma(sigma);
	}

	@Override
	public SevenSigmaDetails findBySigmaDetailsId(long docId) {
		return sevenSigmaDetailsRepository.findOne(docId);
	}

	@Override
	public void saveDocument(SevenSigmaDetails document) {
		sevenSigmaDetailsRepository.save(document);		
	}

	@Override
	public SevenSigma findSigmaById(long sigmaId) throws Exception{
		SevenSigma sigma= sevenSigmaRepository.findOne(sigmaId);
		if(sigma==null){
			throw new UserDefinedException("Sigma Not Found");
		}
		return sigma;
	}

	@Override
	public SevenSigmaDetails findSigmaDetailById(long sigmaDetailId) {
		return sevenSigmaDetailsRepository.findOne(sigmaDetailId);
	}

	@Override
	public SevenSigma findSigmaByIdAndImgPathAndImgName(long sigmaId, String imgPath, String imgName) {
		return  sevenSigmaRepository.findSigmaByIdAndSigmaIconPathAndSigmaIconName(sigmaId,imgPath,imgName);
	}
	

}
