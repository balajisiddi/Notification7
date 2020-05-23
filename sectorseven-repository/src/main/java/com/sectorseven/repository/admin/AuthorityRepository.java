package com.sectorseven.repository.admin;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.admin.Authority;

/**
 * @author Ramesh Naidu
 *
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Serializable>, JpaSpecificationExecutor<Authority> {

    Authority findAdministratorsAuthorityByAuthorityCode(String authorityCode);

	//Authority findByAuthorityCode(AdminRole roleSchoolAdmin);

}
