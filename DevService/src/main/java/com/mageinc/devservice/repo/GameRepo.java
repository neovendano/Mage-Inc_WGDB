package com.mageinc.devservice.repo;
import com.mageinc.devservice.repo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
}
