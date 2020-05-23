package com.sectorseven.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.AdministratorAuthority;

@Repository
public interface AdministratorAuthorityRepository extends JpaRepository<AdministratorAuthority, Long>, JpaSpecificationExecutor<AdministratorAuthority>{

	
	AdministratorAuthority findByUserName(String userName);


	
	//Set<AdministratorAuthority> findByAdministrator(Administrator user);

  /*  @Modifying
    @Transactional
    @Query("Delete from AdministratorAuthority ca where ca.administrator = :user")
    void deleteByAdministrator(@Param("user") Administrator user);

	Set<AdministratorAuthority> findByAdministrator(Administrator user);
*/	
}
