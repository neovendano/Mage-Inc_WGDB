package com.mageinc.devservice.api;
import com.mageinc.devservice.repo.model.Game;
import com.mageinc.devservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> index(){
        final List<Game> games = gameService.fetchAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> show(@PathVariable long id){
        try {
            final Game game = gameService.fetchById(id);
            return ResponseEntity.ok(game);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Game newGame){
        final long id = gameService.create(newGame);
        String location = String.format("/games/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody Game newGame){
        try{
            gameService.update(id, newGame);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
