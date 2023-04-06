package com.doitwell.group.springTesting.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

import com.doitwell.group.springTesting.User.Mapper.MapperUser;
import com.doitwell.group.springTesting.User.PasswordEncoder.PasswordEncoder;
import com.doitwell.group.springTesting.User.Privilege.Privilege;
import com.doitwell.group.springTesting.User.Privilege.PrivilegesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesRepository;
import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class UserDetailsService {
    
    private UserRepository repo;

    @Autowired
    private MapperUser mapper;
    @Autowired
    private PrivilegesRepository privilegesRepository;
    @Autowired
    private RolesRepository rolesRepository;

    String saltvalue = PasswordEncoder.getSaltValue(30);
    // Verify user
        /*
        Boolean status = verifyUserPassword(password,encryptedpassword,saltvalue);
        if(status==true)
            System.out.println("Password Matched!!");
        else
            System.out.println("Password Mismatched");*/

    public UserDetailsService(UserRepository repo){
        this.repo=repo;
    }

    public List<UserDTO> getAllUsers(){
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    public UserModel findByEmail(String email){
        if(!Objects.isNull(isEmailExist(email)))
            throw new IllegalStateException("username already taken");
            
        Optional<UserModel> checkUser = repo.findByEmail(email);
        return checkUser.get();
    }

    public UserDTO addUser(UserModel user){
        if (user.getFirstName()==null || user.getFirstName().length()==0) {
            throw new IllegalStateException("name must be filled");
        }
        if (user.getLastName()==null || user.getLastName().length()==0) {
            throw new IllegalStateException("last name must be filled");
        }
        if (user.getPassword()==null || user.getPassword().length()==0) {
            throw new IllegalStateException("username must be filled");
        }
        //if  (user.getRoles().size()<1) throw new IllegalStateException("user must have at least one role");
        if  (Objects.isNull(user.getPhone()) | user.getPhone().length()==0) throw new IllegalStateException("user must have a phone number");
        if  (Objects.isNull(user.getDob()) | Period.between(user.getDob(), LocalDate.now()).getYears()<0) throw new IllegalStateException("user must have a valid date of birth");
        if  (Objects.isNull(user.getPob()) | user.getPob().length()==0) throw new IllegalStateException("user must have a place of birth");
        if  (Objects.isNull(user.getCity()) | user.getCity().length()==0) throw new IllegalStateException("user must have a city of residence");
        if  (Objects.isNull(user.getAddresse()) | user.getAddresse().length()==0) throw new IllegalStateException("user must have an address");
        if  (Objects.isNull(user.getPo_box()) | user.getPo_box().length()==0) throw new IllegalStateException("user must have a postal box");

        validateEmail(user.getEmail());
        //encrypt password
        validatePassword(user.getPassword());
        user.setPassword( PasswordEncoder.generateSecurePassword(user.getPassword(), saltvalue));

        //set user role
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        RolesUsers user_role = createRoleIfNotFound("USER",Arrays.asList(readPrivilege,writePrivilege));
        user.setRoles(List.of(user_role));

        UserDTO userDTO = new UserDTO();
        userDTO.setFirst_name(user.getFirstName());
        userDTO.setLast_name(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles().stream().map(RolesUsers::getName).collect(toList()));

        repo.save(user);

        return userDTO;
    }

    public String updateUser(Long id,String firstName, String lastName, String password, String email, List<RolesUsers> roles,
                             String phone, String dob, String pob, String city, String address, String po_box){
        UserModel userToUpdate = repo.findById(id)
                                .orElseThrow(()->{
                                    throw new IllegalStateException("user does not exist");
                                });
        //if username exist
        if(isEmailExist(email)!= null){
            throw new IllegalStateException("username already taken");
        }

        if(firstName!=null && firstName.length()>0 && !Objects.equals(firstName, userToUpdate.getFirstName())){
            userToUpdate.setFirstName(firstName);
        }
        if(lastName!=null && lastName.length()>0 && !Objects.equals(lastName, userToUpdate.getLastName())){
            userToUpdate.setLastName(lastName);
        }
        if(password!=null && password.length()>0 && !Objects.equals(password, userToUpdate.getPassword())){
            validatePassword(password);
            userToUpdate.setPassword(password);
        }
        if(email!=null && email.length()>0 && !Objects.equals(email, userToUpdate.getEmail())){
            validateEmail(email);
            userToUpdate.setEmail(email);
        }


        repo.save(userToUpdate);
        return "user updated successfully";
    }

    public String deleteUser(Long id){
        UserModel user = repo.findById(id)
            .orElseThrow(()->{
                throw new IllegalStateException("user with id "+id+" does not exist");
            });

        repo.delete(user);
        return "user deleted successfully";
    }

    public Boolean isEmailExist(String email){
        Optional<UserModel> checkUser = repo.findByEmail(email);
        return checkUser.isPresent();
    }

    public void validateEmail(String email){
        String regexPatternEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!patterMatcher(email, regexPatternEmail)) {
            throw new IllegalStateException("enter a valid email");
        }
    }

    public void validateUsername(String username){
        String regexPatternUsername = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (username.matches(regexPatternUsername)) {
            throw new IllegalStateException("enter a valid username");
        }
    }

    public void validatePassword(String password){
        /**
            Must have at least one numeric character
            Must have at least one lowercase character
            Must have at least one uppercase character
            Must have at least one special symbol among @#$%
            Password length should be between 8 and 20
         */
        String regexPatternPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        if (!patterMatcher(password, regexPatternPassword)) {
            throw new IllegalStateException("enter a valid password.\n"+
                                            "A password must have at least 1 Numeric character"+
                                            ",at least 1 lowercase character "+
                                            ",at least 1 uppercase character "+
                                            ",at least 1 special symbol among @#$%"+
                                            "Password length should be between 8 and 20"
                                            );
        }
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

    public static Boolean patterMatcher(String value, String regex){
        return Pattern.compile(regex)
                .matcher(value)
                .matches();
    }
}
