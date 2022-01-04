package com.mageinc.matchservice.service;
import com.mageinc.matchservice.repo.MatchRepo;
import com.mageinc.matchservice.repo.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepo matchRepo;
    private static final String GET_USERNAME_URL = "http://localhost:8080/users/fetchName/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    public List<Match> fetchAll(){
        return matchRepo.findAll();
    }

    public Match fetchById(long id) throws IllegalArgumentException{
        final Optional<Match> maybeMatch = matchRepo.findById(id);

        if (maybeMatch.isEmpty()) throw new IllegalArgumentException("Match not found");
        else return maybeMatch.get();
    }

    public List<String> fetchUsernamesById (long id){
        final Optional<Match> maybeMatch = matchRepo.findById(id);

        if (maybeMatch.isEmpty()) throw new IllegalArgumentException("Match not found");
        else
        {
            String firstUserId = String.valueOf(maybeMatch.get().getFirstUserId());
            String secondUserId = String.valueOf(maybeMatch.get().getSecondUserId());
            String winner = String.valueOf(maybeMatch.get().getWinner());
            List<String> usernames = new ArrayList<>();
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> paramsFirst = new HashMap<String, String>();
            paramsFirst.put("id", firstUserId);
            Map<String, String> paramsSecond = new HashMap<String, String>();
            paramsSecond.put("id", secondUserId);
            Map<String, String> paramsWinner = new HashMap<String, String>();
            paramsWinner.put("id", winner);
            String resultFirst = restTemplate.getForObject(GET_USERNAME_URL, String.class, paramsFirst);
            String resultSecond = restTemplate.getForObject(GET_USERNAME_URL, String.class, paramsSecond);
            String resultWinner = restTemplate.getForObject(GET_USERNAME_URL, String.class, paramsWinner);

            usernames.add(resultFirst);
            usernames.add(resultSecond);
            usernames.add(resultWinner);
            return usernames;
        }
    }

    public long create(Match newMatch){
        final Match savedMatch = matchRepo.save(newMatch);
        return savedMatch.getId();
    }

    public void update(long id, Match newMatch){
        boolean isOn = newMatch.isOn();
        long gameId = newMatch.getGameId();
        long firstUserId = newMatch.getFirstUserId();
        long secondUserId = newMatch.getSecondUserId();
        long winner = newMatch.getWinner();
        Date matchTime = newMatch.getMatchTime();

        Optional<Match> maybeMatch = matchRepo.findById(id);
        if(maybeMatch.isEmpty()) throw new IllegalArgumentException("Match not found");
        final Match oldMatch = maybeMatch.get();

        oldMatch.setOn(isOn);
        oldMatch.setGameId(gameId);
        oldMatch.setFirstUserId(firstUserId);
        oldMatch.setSecondUserId(secondUserId);
        oldMatch.setWinner(winner);
        oldMatch.setMatchTime(matchTime);

        matchRepo.save(oldMatch);
    }

    public void delete(long id){
        matchRepo.deleteById(id);
    }
}
