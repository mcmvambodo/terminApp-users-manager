package com.doitwell.group.springTesting.User.Roles;

import com.doitwell.group.springTesting.User.Privilege.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Collection;

@Service
public class RolesService {

    @Autowired
    private RolesRepository repo;

    @Transactional
    public RolesUsers getOrCreateRole(String role, Collection<Privilege> privileges){
        RolesUsers fRole = repo.findByName(role);
        if (fRole==null){
            fRole = new RolesUsers(role);
            fRole.setPrivileges(privileges);
            repo.save(fRole);
        }
        return fRole;
    }
}
