package com.sectorseven.repository.School;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sectorseven.model.school.SchoolAdministratorAuthority;

@Repository
public interface SchoolAdministratorAuthorityRepository extends JpaSpecificationExecutor<SchoolAdministratorAuthority>, JpaRepository<SchoolAdministratorAuthority, Serializable>{

}
