package com.doitwell.group.springTesting.User.Mapper;

import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import com.doitwell.group.springTesting.User.UserDTO;
import com.doitwell.group.springTesting.User.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class MapperUser {
    public UserDTO toDto(UserModel user) {
        String first_name = user.getFirstName();
        String last_name = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        List<String> roles = user
                .getRoles()
                .stream()
                .map(RolesUsers::getName)
                .collect(toList());

        return new UserDTO(first_name,last_name, email,password, roles);
    }
/*
    public UserModel toUser(UserDTO userDTO) {
        return new UserModel(userDTO.getEmail(),userDTO.getRoles() userDTO.getPassword(), new ArrayList<>());
    }*/
}
