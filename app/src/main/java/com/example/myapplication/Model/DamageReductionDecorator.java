package com.example.myapplication.Model;

public class DamageReductionDecorator extends CharacterDecorator {
    public DamageReductionDecorator(Character decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public void setPlayerHealth(int playerHP) {
        Player player = Player.getInstance();
        switch (player.getDifficulty()) {
        case "Hard":
            ((Player) decoratedCharacter).changePlayerHP(playerHP - 25);
            break;
        case "Easy":
            ((Player) decoratedCharacter).changePlayerHP(playerHP - 10);
            break;
        case "Medium":
            ((Player) decoratedCharacter).changePlayerHP(playerHP - 15);
            break;
        default:
            break;
        }
    }
}
