package com.sectorseven.service.admin;

import java.util.List;
import java.util.Set;
import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.admin.Administrator;
import com.sectorseven.model.util.UserDefinedException;

public interface AdministratorService  {


    void saveOrganizationAdministrator(Administrator administrator, Set<AdminRole> roles)throws Exception;

    int updatePassword(Long administratorId, String password);

    List<Administrator> findAllAdministrators();

    Administrator getEmail(String email);

    Administrator findbyEmailAndUsername(String email, String username);

	Administrator findAdministrator(Long id) throws UserDefinedException;

	Administrator findAdministratorByUsername(String username);


}
