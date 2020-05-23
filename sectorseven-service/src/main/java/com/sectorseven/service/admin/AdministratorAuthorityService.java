package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.admin.AdministratorAuthority;

public interface AdministratorAuthorityService {

	 List<AdministratorAuthority> findAllCollegeAuthorities();

	    void saveAdministratorAuthority(AdministratorAuthority organizationAuthority);

	    AdministratorAuthority updateAdministratorAuthority(AdministratorAuthority collegeAuthority);

	    AdministratorAuthority findAdministratorAuthorityUserById(long id);
	    
	    AdministratorAuthority findAdministratorAuthorityByUserName(String userName);

	   /* Set<AdministratorAuthority> findByAdministrator(Administrator user);

	    void deleteByAdministrator(Administrator user);
*/
}
