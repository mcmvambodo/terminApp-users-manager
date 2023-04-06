package com.doitwell.group.springTesting;

import com.doitwell.group.springTesting.User.PasswordEncoder.PasswordEncoder;
import com.doitwell.group.springTesting.User.Privilege.Privilege;
import com.doitwell.group.springTesting.User.Privilege.PrivilegesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import com.doitwell.group.springTesting.User.UserModel;
import com.doitwell.group.springTesting.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup =false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PrivilegesRepository privilegesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
/*
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege,writePrivilege);
        List<Privilege> userPrivileges = List.of(readPrivilege);
        createRoleIfNotFound("ROLE_ADMIN",adminPrivileges);
        createRoleIfNotFound("ROLE_USER",userPrivileges);

        UserModel user = new UserModel("Michel Chateau", "MESSENG MVAMBODO", "mezuiella@gmail.com", "#Ajoene!ya3",
                List.of(new RolesUsers("user")),"+237 699460092", LocalDate.parse("1996-09-29"),
                "Yaoundé","Passau","Fruelingstrasse 6a","94032");
        userRepository.save(user);*/

        alreadySetup = true;
    }

    @Transactional
    private RolesUsers createRoleIfNotFound(String role, Collection<Privilege> privileges) {
        RolesUsers findRole = rolesRepository.findByName(role);
        if (findRole==null){
            findRole = new RolesUsers(role);
            findRole.setPrivileges(privileges);
            rolesRepository.save(findRole);
        }
        return findRole;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String privilege) {
        Privilege findPrivilege = privilegesRepository.findByName(privilege);
        if (findPrivilege==null){
            findPrivilege = new Privilege(privilege);
            privilegesRepository.save(findPrivilege);
        }
        return findPrivilege;
    }
}
