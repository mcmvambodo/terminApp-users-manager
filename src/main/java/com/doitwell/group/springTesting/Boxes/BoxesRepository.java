package com.doitwell.group.springTesting.Boxes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoxesRepository extends JpaRepository<Boxes,Long> {
    Optional<Boxes> findByBoxeNumber(int number);
}
