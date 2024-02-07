package com.example.myapplication.Model;

public interface Character {

    // Health-related methods
    // Methods for player name
    String getPlayerName();
    void setPlayerName(String playerName);

    // Methods for player health points (HP)
    int getPlayerHP();
    void setPlayerHP(int playerHP);

    void setPlayerHealth(int health);

    // Methods for player score
    double getPlayerScore();
    void setPlayerScore(double playerScore);

    // Methods for game difficulty
    String getDifficulty();
    void setDifficulty(String difficulty);

    // Methods for player location
    int getX();
    int getY();
    void setXY(int xLocation, int yLocation);

    // Add other methods as needed for your game's requirements
}
