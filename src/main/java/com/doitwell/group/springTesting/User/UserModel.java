package com.doitwell.group.springTesting.User;

import com.doitwell.group.springTesting.User.Roles.RolesUsers;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity
@Table
public class UserModel {

    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
        
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",referencedColumnName = "id"
            )
    )
    private Collection<RolesUsers> roles;
    private String phone;
    private LocalDate dob;
    private String pob;
    private String city;
    private String addresse;
    private String po_box;


    public UserModel(){}

    public UserModel(String firstName, String lastName, String email, String password,List<RolesUsers> role,
                     String phone, LocalDate dob, String pob, String city,String addresse,String po_box){


        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.roles =role;
        this.phone = Objects.requireNonNull(phone);
        this.dob = Objects.requireNonNull(dob);
        this.pob = Objects.requireNonNull(pob);
        this.city = Objects.requireNonNull(city);
        this.addresse = Objects.requireNonNull(addresse);
        this.po_box = Objects.requireNonNull(po_box);

    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<RolesUsers> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RolesUsers> role) {
        if (role==null){
            this.roles = List.of(new RolesUsers("user"));
        }
        this.roles = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge(){
        return Period.between(dob,LocalDate.now()).getYears();
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPo_box() {
        return po_box;
    }

    public void setPo_box(String po_box) {
        this.po_box = po_box;
    }

}
