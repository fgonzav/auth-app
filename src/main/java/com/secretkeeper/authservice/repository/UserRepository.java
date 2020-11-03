package com.secretkeeper.authservice.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LdapRepository<UserEntity> {

    UserEntity findByUsername(String username);
}
