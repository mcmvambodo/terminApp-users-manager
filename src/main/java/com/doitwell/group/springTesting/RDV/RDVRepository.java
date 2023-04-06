package com.doitwell.group.springTesting.RDV;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RDVRepository extends JpaRepository<RDV,Long> {

    List<Optional<RDV>> findRDVByMotif(String motif);
}
