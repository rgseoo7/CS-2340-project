package com.example.myapplication.Model;

public abstract class CharacterDecorator implements Character {
    protected Character decoratedCharacter;

    public CharacterDecorator(Character decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }

    // Implement all methods from the Character interface
    // Delegating the call to the decoratedCharacter object
    @Override
    public String getPlayerName() {
        return decoratedCharacter.getPlayerName();
    }

    @Override
    public void setPlayerName(String playerName) {
        decoratedCharacter.setPlayerName(playerName);
    }

    @Override
    public int getPlayerHP() {
        return decoratedCharacter.getPlayerHP();
    }

    @Override
    public void setPlayerHP(int playerHP) {
        decoratedCharacter.setPlayerHP(playerHP);
    }

    @Override
    public double getPlayerScore() {
        return decoratedCharacter.getPlayerScore();
    }

    @Override
    public void setPlayerScore(double playerScore) {
        decoratedCharacter.setPlayerScore(playerScore);
    }

    @Override
    public String getDifficulty() {
        return decoratedCharacter.getDifficulty();
    }

    @Override
    public void setDifficulty(String difficulty) {
        decoratedCharacter.setDifficulty(difficulty);
    }

    @Override
    public int getX() {
        return decoratedCharacter.getX();
    }

    @Override
    public int getY() {
        return decoratedCharacter.getY();
    }

    @Override
    public void setXY(int xLocation, int yLocation) {
        decoratedCharacter.setXY(xLocation, yLocation);
    }

    @Override
    public void setPlayerHealth(int health) {
        decoratedCharacter.setPlayerHealth(health);
    }

}
