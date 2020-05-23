package com.sectorseven.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.repository.admin.AdministratorAuthorityRepository;
import com.sectorseven.service.admin.AdministratorAuthorityService;

/**
 * @author RameshNaidu
 *
 */
@Service
public class AdministratorAuthorityServiceImpl implements AdministratorAuthorityService{

	 @Autowired
	    private AdministratorAuthorityRepository administratorAuthorityRepository;

	    @Override
	    public List<AdministratorAuthority> findAllCollegeAuthorities() {
	        return administratorAuthorityRepository.findAll();
	    }

	    @Override
	    public AdministratorAuthority findAdministratorAuthorityByUserName(String userName) {
	        return administratorAuthorityRepository.findByUserName(userName);
	    }
	    
	    @Override
	    @Transactional
	    public void saveAdministratorAuthority(AdministratorAuthority administratorAuthority) {
	    	administratorAuthorityRepository.save(administratorAuthority);
	    }

	    @Override
	    @Transactional
	    public AdministratorAuthority updateAdministratorAuthority(AdministratorAuthority administratorAuthority) {
	        return administratorAuthorityRepository.save(administratorAuthority);
	    }

	    @Override
	    public AdministratorAuthority findAdministratorAuthorityUserById(long id) {
	        return administratorAuthorityRepository.findOne(id);
	    }

	 /*   @Override
	    public Set<AdministratorAuthority> findByAdministrator(Administrator user) {
	        return administratorAuthorityRepository.findByAdministrator(user);
	    }

	    @Override
	    @Transactional
	    public void deleteByAdministrator(Administrator user) {
	    	administratorAuthorityRepository.deleteByAdministrator(user);
	    }*/


}
