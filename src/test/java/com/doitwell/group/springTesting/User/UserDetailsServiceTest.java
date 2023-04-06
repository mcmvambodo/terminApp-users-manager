package com.doitwell.group.springTesting.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.doitwell.group.springTesting.User.PasswordEncoder.PasswordEncoder;
import com.doitwell.group.springTesting.User.Privilege.Privilege;
import com.doitwell.group.springTesting.User.Privilege.PrivilegesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserDetailsServiceTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserDetailsService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PrivilegesRepository privilegesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    
    @Test
    public void getUsersFromService(){
        //UserService service = new UserService(repo);
        List<UserDTO> users = service.getAllUsers();
        assertEquals(getUsersDirectly().get(0).getFirstName(), users.get(0).getFirst_name());
    }

    @Test
    public List<UserModel> getUsersDirectly(){
        return repo.findAll();
    }

    @Test
    public void addUser(){
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        RolesUsers roleAdmin = createRoleIfNotFound("ADMIN",List.of(readPrivilege,writePrivilege));
        RolesUsers roleUser = createRoleIfNotFound("USER", List.of(readPrivilege));

        UserModel user = new UserModel("MC", "Mvambodo", "mcmvambodo@gmail.com", "@Sengohr!do13",
                List.of(roleAdmin),"+237 699460092", LocalDate.parse("1996-09-29"),
                "Yaoundé","Passau","Fruelingstrasse 6a","94032");

        //UserService service = new UserService(repo);
        UserDTO userDTO = service.addUser(user);
       // assertNotNull(userDTO);
        //assertEquals("1", res);
    }
    @Test
    public void editUsers(){
        //UserModel user = new UserModel("MC", "Mvambodo", "mcmvambodo@gmail.com", "ajoeneya", "Ajoeneya3",List.of(new RolesUsers("user")));
        //repo.save(user);
        //assertEquals(1, users);
    }

    @Test
    public void validateEmail(){
        service.validatePassword("#Password1");
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
