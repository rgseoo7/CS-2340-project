package com.example.myapplication.Model;

import com.example.myapplication.ViewModel.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

public class Player implements Character {

    private Character originalCharacter;

    private static volatile Player instance;
    private final Object commonLock = new Object();
    private boolean isEndScreenDisplayed = false;
    private Character decoratedCharacter = this;
    private String playerName;
    private int playerHP;
    private int spritePosition;

    private double playerScore;
    private String difficulty;
    private int xLocation;
    private int yLocation;
    private boolean hasKey;

    private List<PlayerObserver> observers = new ArrayList<>();

    private Player() { }

    public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }
    public Object getCommonLock() {
        return commonLock;
    }

    public boolean isEndScreenDisplayed() {
        return isEndScreenDisplayed;
    }

    public void setEndScreenDisplayed(boolean displayed) {
        isEndScreenDisplayed = displayed;
    }

    // Getters and setters for playerName
    public String getPlayerName() {
        return playerName;
    }
    public int getSpritePosition() {
        return spritePosition;
    }
    public void setSpritePosition(int spritePosition) {
        this.spritePosition = spritePosition;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean gethasKey() {
        return hasKey;
    }
    public void sethasKey() {
        hasKey = true;
    }
    // Getters and setters for playerHP
    public int getPlayerHP() {
        return playerHP;
    }

    @Override
    public void setPlayerHP(int playerHP) {
        if (this == decoratedCharacter) {
            this.playerHP = playerHP; // Direct modification when not decorated
        } else {
            decoratedCharacter.setPlayerHP(playerHP); // Delegated when decorated
        }
    }

    public String getPlayerNameAndScore() {
        return this.playerName + this.playerScore;
    }



    // Getters and setters for playerScore
    public double getPlayerScore() {
        return playerScore;
    }

    @Override
    public void setPlayerScore(double playerScore) {
        if (this == decoratedCharacter) {
            this.playerScore = playerScore; // Direct modification when not decorated
        } else {
            decoratedCharacter.setPlayerScore(playerScore);
        }
    }


    public void deactivateBoost() {
        this.decoratedCharacter = originalCharacter;
    }

    // Getters and setters for Difficulty
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Getters and setters for xLocation
    public int getX() {
        return xLocation;
    }

    public void setXY(int xLocation, int yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        notifyObservers();
    }

    // Getters and setters for yLocation
    public int getY() {
        return yLocation;
    }

    // Observer pattern methods
    public void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    public void changePlayerHP(int newHP) {
        this.playerHP = newHP;
    }

    private void notifyObservers() {
        for (PlayerObserver observer : observers) {
            observer.updatePlayerLocation(xLocation, yLocation);
        }
    }

    public void activateScoreMultiplier() {
        this.originalCharacter = this.decoratedCharacter;
        this.decoratedCharacter = new ScoreMultiplierDecorator(this.decoratedCharacter);
    }

    public void activateDamageReduction() {
        this.originalCharacter = this.decoratedCharacter;
        this.decoratedCharacter = new DamageReductionDecorator(this.decoratedCharacter);
    }

    public void activateHealthBoost() {
        this.originalCharacter = this.decoratedCharacter;
        this.decoratedCharacter = new HealthBoostDecorator(this.decoratedCharacter);
    }

    public void setPlayerHealth(int playerHP) {
        if (this == decoratedCharacter) {
            Player player = Player.getInstance();
            switch (player.getDifficulty()) {
            case "Hard":
                player.setPlayerHP(playerHP - 50);
                break;
            case "Easy":
                player.setPlayerHP(playerHP - 10);
                break;
            case "Medium":
                player.setPlayerHP(playerHP - 25);
                break;
            default:
                break;
            }
        } else {
            decoratedCharacter.setPlayerHealth(playerHP);
        }
    }

    public void changePlayerScoreDirectly(double newScore) {
        this.playerScore = newScore;
    }

    public void scoreMultiplier(double multiplier) {
        this.playerScore = playerScore * multiplier;
    }

}
