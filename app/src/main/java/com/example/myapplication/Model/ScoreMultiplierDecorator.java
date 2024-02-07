package com.example.myapplication.Model;

public class ScoreMultiplierDecorator extends CharacterDecorator {

    public ScoreMultiplierDecorator(Character decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public void setPlayerScore(double playerScore) {
        ((Player) decoratedCharacter).changePlayerScoreDirectly(super.getPlayerScore() * 2);
    }
}
