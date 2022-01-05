package com.mageinc.devservice.service;
import com.mageinc.devservice.repo.GameRepo;
import com.mageinc.devservice.repo.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepo gameRepo;
    public List<Game> fetchAll(){
        return gameRepo.findAll();
    }

    public Game fetchById(long id) throws IllegalArgumentException {
        Optional<Game> maybeGame = gameRepo.findById(id);
        if(maybeGame.isEmpty()) throw new IllegalArgumentException("Game not found");
        else return maybeGame.get();
    }

    public String fetchNameById(long id)throws IllegalArgumentException {
        Optional<Game> maybeGame = gameRepo.findById(id);
        String error = "Game not found";
        if(maybeGame.isEmpty()) return error;
        else return maybeGame.get().getGameName();
    }

    public long create(Game newGame){
        final Game savedGame = gameRepo.save(newGame);
        return savedGame.getId();
    }

    public void update(long id, Game newGame){
        String version = newGame.getVersion();
        String name = newGame.getGameName();
        String description =newGame.getTextDescription();
        int rate = newGame.getRate();


        Optional<Game> maybeGame = gameRepo.findById(id);
        if(maybeGame.isEmpty()) throw new IllegalArgumentException("Game not found");
        final Game oldGame = maybeGame.get();

        if (version != null && !version.isBlank()) oldGame.setVersion(version);
        if (name != null && !name.isBlank()) oldGame.setGameName(name);
        if (description != null && !description.isBlank()) oldGame.setTextDescription(description);

        oldGame.setRate(rate);

        gameRepo.save(oldGame);
    }

    public void delete(long id){
        gameRepo.deleteById(id);
    }

}
