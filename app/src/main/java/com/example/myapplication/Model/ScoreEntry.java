package com.example.myapplication.Model;

import java.util.Date;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private final String playerName;
    private final Double score;
    private final Date date;

    public ScoreEntry(String playerName, Double score) {
        this.playerName = playerName;
        this.score = score;
        this.date = new Date();
    }

    public String getPlayerName() {
        return playerName;
    }

    public Double getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(ScoreEntry o) {
        return Double.compare(this.score, o.score);
    }

    public void updateScore(int i) {

    }
}
