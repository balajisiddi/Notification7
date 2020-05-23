package com.sectorseven.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sectorseven.model.admin.Authority;
import com.sectorseven.repository.admin.AuthorityRepository;
import com.sectorseven.service.admin.AuthorityService;

/**
 * @author RameshNaidu
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findAllAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    @Transactional
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);

    }

    @Override
    @Transactional
    public Authority updateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findAuthorityUserById(long id) {
        return (Authority) authorityRepository.findOne(id);
    }

    @Override
    public Authority findAdministratorsAuthorityByAuthorityCode(String authorityCode) {
        return authorityRepository.findAdministratorsAuthorityByAuthorityCode(authorityCode);
    }

}
