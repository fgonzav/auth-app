package com.secretkeeper.authservice.repository;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import javax.naming.Name;

@Getter
@Setter
@ToString
@Entry(base = "ou=Users", objectClasses = {"inetOrgPerson", "organizationalPerson","person","top"})
public final class UserEntity{

    @Id
    private Name id;

    @Attribute(name = "givenName")
    private String name;

    @Attribute(name = "sn")
    private String lastname;

    @Attribute(name = "cn")
    private String commonName;

    @DnAttribute("uid")
    @Attribute(name = "uid")
    private String username;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "userPassword")
    private String password;
}
