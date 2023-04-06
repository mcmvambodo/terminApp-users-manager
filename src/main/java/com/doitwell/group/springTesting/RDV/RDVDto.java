package com.doitwell.group.springTesting.RDV;

import com.doitwell.group.springTesting.User.UserModel;

public class RDVDto {
    private Long id;
    private UserModel user;

    public RDVDto() {
    }

    public RDVDto(Long id, UserModel user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
