package com.doitwell.group.springTesting.User.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesUsers,Long> {
    RolesUsers findByName(String name);
}
