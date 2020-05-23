package com.sectorseven.service.admin.impl;

import static java.lang.String.format;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.admin.Administrator;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.admin.AdministratorRepository;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AdministratorService;
import com.sectorseven.service.admin.AuthorityService;



@Transactional(propagation = Propagation.REQUIRED)
@Service
public  class AdministratorServiceImpl  implements AdministratorService  {

	
	private static final Logger LOG = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    @Autowired
    private AdministratorAuthorityService administratorAuthorityService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    protected  AdministratorRepository repository;
    
    
    

    @Override
    public void saveOrganizationAdministrator(Administrator organizationUsers, Set<AdminRole> roles)throws Exception {
        repository.save(organizationUsers);
        saveAuthorities(organizationUsers, roles);
    }

    @Override
    public int updatePassword(Long organizationUserId, String password) {
        return repository.updatePassword(organizationUserId, password);
    }


    private void saveAuthorities(Administrator administrator, Set<AdminRole> roles) throws Exception{
        for (AdminRole role : roles) {
        	AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(administrator);
            administratorAuthority.setAuthority(authority);
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }

    @SuppressWarnings("unused")
	private void updateAuthorities(Administrator administrator, Set<AdminRole> roles) throws Exception{
        Administrator user = repository.findOne(administrator.getId());
        for (AdminRole role : roles) {
            AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(user);
            administratorAuthority.setAuthority(authority);
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }

    /*@SuppressWarnings("unused")
	private void deleteAuthority(Administrator administrator) {
        administratorAuthorityService.deleteByAdministrator(administrator);
    }*/

    /*@Override
    public T updateOrganizationAdministrator(T organizationUsers) {
        return repository().save(organizationUsers);
    }*/

    @Override
    public Administrator findAdministrator(Long id) throws UserDefinedException {
        Administrator administrator = repository.findOne(id);
        if (null != administrator) {
            return administrator;
        } else {
            String message = format("Administrator (%d) not found", id);
            LOG.error(message);
           throw new UserDefinedException(message);
        }
    }

    @Override
    public Administrator findAdministratorByUsername(String username) {
        return repository.findByUsername(username);
    }
    

    @Override
    public List<Administrator> findAllAdministrators() {
        return repository.findAll();
    }

    @Override
    public Administrator getEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Administrator findbyEmailAndUsername(String email, String username) {
        return repository.findByEmailAndUsername(email, username);
    }

	


    
	
}
