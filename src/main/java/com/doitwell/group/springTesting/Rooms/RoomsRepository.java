package com.doitwell.group.springTesting.Rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    Optional<Rooms> findByName(String name);
}
