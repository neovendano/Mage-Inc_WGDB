package com.mageinc.matchservice.api;
import com.mageinc.matchservice.repo.model.Match;
import com.mageinc.matchservice.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    @GetMapping
    public ResponseEntity<List<Match>> index(){
        final List<Match> matches = matchService.fetchAll();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> show(@PathVariable long id){
        try {
            final Match match = matchService.fetchById(id);
            return ResponseEntity.ok(match);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<String>> showUsers(@PathVariable long id){
        try {
            final List<String> users= matchService.fetchUsernamesById(id);
            return ResponseEntity.ok(users);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Match newMatch){
        final long id = matchService.create(newMatch);
        String location = String.format("/matches/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody Match newMatch){
        try{
            matchService.update(id, newMatch);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
