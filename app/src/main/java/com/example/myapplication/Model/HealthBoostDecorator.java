package com.example.myapplication.Model;

public class HealthBoostDecorator extends CharacterDecorator {

    public HealthBoostDecorator(Character decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public void setPlayerHP(int additionalHealth) {
        // Temporarily increases perceived health
        ((Player) decoratedCharacter).changePlayerHP(super.getPlayerHP() + additionalHealth);
    }
}
