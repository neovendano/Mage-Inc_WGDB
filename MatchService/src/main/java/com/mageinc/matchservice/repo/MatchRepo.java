package com.mageinc.matchservice.repo;

import com.mageinc.matchservice.repo.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepo extends JpaRepository<Match, Long> {
}
