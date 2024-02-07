package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private static final Leaderboard LEADERBOARD_INSTANCE = new Leaderboard();
    private final List<ScoreEntry> scoreEntries;

    private Leaderboard() {
        this.scoreEntries = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        return LEADERBOARD_INSTANCE;
    }

    public void addScore(ScoreEntry scoreEntry) {
        this.scoreEntries.add(scoreEntry);
        Collections.sort(this.scoreEntries, Collections.reverseOrder());
    }

    public List<ScoreEntry> getTopScores(int limit) {
        return this.scoreEntries.subList(0, Math.min(limit, this.scoreEntries.size()));
    }

    public ScoreEntry getMostRecentScore() {
        return this.scoreEntries.size() > 0
                ? this.scoreEntries.get(this.scoreEntries.size() - 1) : null;
    }

    public void clearScores() {
        scoreEntries.clear();
    }
}
