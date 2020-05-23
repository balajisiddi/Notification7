package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.admin.Authority;

/**
 * @author Ramesh naidu
 *
 */
public interface AuthorityService {

    List<Authority> findAllAuthority();

    void saveAuthority(Authority authority);

    Authority updateAuthority(Authority authority);

    Authority findAuthorityUserById(long id);

    Authority findAdministratorsAuthorityByAuthorityCode(String authorityCode) throws Exception;

}
