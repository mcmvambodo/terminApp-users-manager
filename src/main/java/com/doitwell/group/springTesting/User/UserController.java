package com.doitwell.group.springTesting.User;
import java.util.List;

import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private UserDetailsService service;

    @GetMapping
    public List<UserDTO> getUsers(){
        return service.getAllUsers();
    }

    @PostMapping
    public UserDTO addUser( @RequestBody UserModel user)
    {
        //UserModel user = new UserModel(firstname, lastname, email, username, password);
        return service.addUser(user);
    }
    @GetMapping("{email}")
    public UserModel getOneUser(@PathVariable("email") String email){
        return this.service.findByEmail(email);
    }



    @PutMapping("{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @RequestParam(name="firstname", required = false) String firstname,
                             @RequestParam(name = "lastname", required = false) String lastname,
                             @RequestParam(name = "password", required = false) String password,
                             @RequestParam(name = "email", required = false) String email,
                             @RequestParam(name="roles", required = false) List<RolesUsers> roles,
                             @RequestParam(name="phone", required = false) String phone,
                             @RequestParam(name="dob", required = false) String dob,
                             @RequestParam(name="pob", required = false) String pob,
                             @RequestParam(name="city", required = false) String city,
                             @RequestParam(name="addresse", required = false) String addresse,
                             @RequestParam(name="po_box", required = false) String po_box
                             ){
        return service.updateUser(id,firstname, lastname, password, email, roles, phone, dob, pob, city, addresse, po_box);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long id){
        return service.deleteUser(id);
    }


}
