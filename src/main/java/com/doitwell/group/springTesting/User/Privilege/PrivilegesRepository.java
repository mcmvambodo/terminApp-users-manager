package com.doitwell.group.springTesting.User.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegesRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
