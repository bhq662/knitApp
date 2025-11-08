package hh.harjoitustyo.knitapp.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface YarnRepository extends CrudRepository<Yarn, Long> {
    Optional<Yarn> findByYarnName(String yarnName);
}
