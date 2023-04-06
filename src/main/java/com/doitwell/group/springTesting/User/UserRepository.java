package com.doitwell.group.springTesting.User;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    Optional <UserModel> findByEmail(String email);
}
