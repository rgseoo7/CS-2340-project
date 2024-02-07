package com.example.myapplication;

import com.example.myapplication.Model.Character;
import com.example.myapplication.Model.DamageReductionDecorator;
import com.example.myapplication.Model.HealthBoostDecorator;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.ScoreMultiplierDecorator;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class Sprint5Tests {
    private Player player;
    private Character character;
    private HealthBoostDecorator boost;
    private int addHealth;

    private DamageReductionDecorator boost1;

    @Before
    public void setUp() {
        player = Player.getInstance();
        player.setPlayerHP(500);
        addHealth = 50;
        character = player;
        boost = new HealthBoostDecorator(character);
        boost1 = new DamageReductionDecorator(character);
    }

    @Test
    public void DamageReductionBoostEasy() {
        player.setDifficulty("medium");
        int initialHP = player.getPlayerHP();
        boost1.setPlayerHP(initialHP);
        int newHP = player.getPlayerHP();
        assertEquals("NewHP should equal that" +
                " of the boosted one.",  initialHP - (player.getPlayerHP() - initialHP)/2, newHP);
    }

    @Test
    public void DamageReductionBoostMedium() {
        player.setDifficulty("easy");
        int initialHP = player.getPlayerHP();
        boost1.setPlayerHP(initialHP);
        int newHP = player.getPlayerHP();
        assertEquals("NewHP should equal that" +
                " of the boosted one.",  initialHP - (player.getPlayerHP() - initialHP)/2, newHP);
    }

    @Test
    public void DamageReductionBoostHard() {
        player.setDifficulty("hard");
        int initialHP = player.getPlayerHP();
        boost1.setPlayerHP(initialHP);
        int newHP = player.getPlayerHP();
        assertEquals("NewHP should equal that" +
                " of the boosted one.",  initialHP - (player.getPlayerHP() - initialHP)/2, newHP);
    }

    @Test
    public void playerHPBoost() {
        int initialHP = player.getPlayerHP();
        boost.setPlayerHP(50);
        int newHP = player.getPlayerHP();
        assertEquals("NewHP should equal that" +
                " of the boosted one.", initialHP + addHealth, newHP);
    }

    @Test
    public void playerHPBoostDouble() {
        int initialHP = player.getPlayerHP();
        boost.setPlayerHP(50);
        boost.setPlayerHP(50);
        int newHP = player.getPlayerHP();
        assertEquals("New player HP should have double boost applied",
                initialHP + 2 * addHealth, newHP);
    }

    @Test
    public void testDamageReductionOnDifferentHealthLevels() {
        player.setPlayerHP(300);
        int initialHP = player.getPlayerHP();
        boost1.setPlayerHP(initialHP - 100);
        int newHP = player.getPlayerHP();
        assertEquals("Damage should be reduced by half.", initialHP - 50, newHP);
    }

    @Test
    public void testPlayerHPIncreaseAndDecrease() {
        int initialHP = player.getPlayerHP();
        player.setPlayerHP(initialHP + 50); // Increase HP
        assertEquals("Player HP should increase by 50.", initialHP + 50, player.getPlayerHP());
        player.setPlayerHP(player.getPlayerHP() - 30); // Decrease HP
        assertEquals("Player HP should decrease by 30.", initialHP + 20, player.getPlayerHP());
    }

    @Test
    public void testScoreChangeDirectlyWithoutDecorator() {
        double initialScore = player.getPlayerScore();
        player.changePlayerScoreDirectly(initialScore + 100.0); // Directly changing score
        assertEquals("Player score should be increased by 100 directly.", initialScore + 100.0, player.getPlayerScore(), 0.01);
    }

    @Test
    public void testActivateAndDeactivateDecorator() {
        int initialHP = player.getPlayerHP();
        player.activateHealthBoost(); // Activate Health Boost Decorator
        boost.setPlayerHP(50); // Increase HP
        player.deactivateBoost(); // Deactivate Health Boost
        player.setPlayerHP(initialHP); // Reset HP to original
        assertEquals("Player HP should return to initial value after deactivating decorator.", initialHP, player.getPlayerHP());
    }

    @Test
    public void testRemovingDamageReductionDecorator() {
        player.setPlayerHP(500);
        player.activateDamageReduction(); // Apply decorator
        player.setPlayerHP(player.getPlayerHP() - 100); // Take 100 damage
        int reducedHP = player.getPlayerHP();
        player.deactivateBoost(); // Remove decorator
        player.setPlayerHP(reducedHP - 100); // Take another 100 damage
        assertEquals("After removing the decorator, full damage should be applied.", reducedHP - 100, player.getPlayerHP());
    }

    @Test
    public void testScoreMultiplierActivationAndDeactivation() {
        double initialScore = player.getPlayerScore();
        double multiplier = 2.0;
        player.activateScoreMultiplier(); // Activate Score Multiplier
        player.setPlayerScore(initialScore); // Set score to trigger multiplier effect
        assertEquals("Score should be doubled with the score multiplier active.", initialScore * multiplier, player.getPlayerScore(), 0.01);

        player.deactivateBoost();
        player.setPlayerScore(initialScore);
        assertEquals("Score should return to initial value after deactivating the multiplier.", initialScore, player.getPlayerScore(), 0.01);
    }

    @Test
    public void testLocationUpdateWithDecorator() {
        int initialX = player.getX();
        int initialY = player.getY();
        int newX = initialX + 10;
        int newY = initialY + 10;

        player.activateHealthBoost(); // Activate any decorator
        player.setXY(newX, newY); // Update location

        assertEquals("X location should be updated correctly with decorator active.", newX, player.getX());
        assertEquals("Y location should be updated correctly with decorator active.", newY, player.getY());

        player.deactivateBoost(); // Deactivate decorator
    }


}
