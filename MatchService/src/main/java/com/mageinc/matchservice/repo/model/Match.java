package com.mageinc.matchservice.repo.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean isOn;
    private long gameId;
    private long firstUserId;
    private long secondUserId;
    private long winner;
    private Date matchTime = new java.util.Date();

    public Match() {
    }

    public Match(long gameId, long firstUserId, long secondUserId, Date matchTime) {
        this.isOn = true;
        this.gameId = gameId;
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.matchTime = matchTime;
    }

    public long getId() {
        return id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(long firstUserId) {
        this.firstUserId = firstUserId;
    }

    public long getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(long secondUserId) {
        this.secondUserId = secondUserId;
    }

    public long getWinner() {
        return winner;
    }

    public void setWinner(long winner) {
        this.winner = winner;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }
}
