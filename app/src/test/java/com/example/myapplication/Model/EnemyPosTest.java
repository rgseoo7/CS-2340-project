package com.example.myapplication.Model;
/*
import android.widget.ImageView;
import com.example.myapplication.ViewModel.CollisionDetector;

import org.junit.Test;
import static org.junit.Assert.*;
public class EnemyPosTest {


    @Test
    //create a new enemy to start the test
    public void testMoveBeyondLeftBorder() {
        ImageView sprite = new ImageView(null);
        FastEnemy fastEnemy = new FastEnemy(sprite);
        CollisionDetector collisionDetector = new CollisionDetector();

        // Set enemy initially beyond the left border
        int initialX = 150;
        int initialY = 750;
        fastEnemy.setEnemyLocationX(initialX);
        fastEnemy.setEnemyLocationY(initialY);

        // Move the enemy
        fastEnemy.move();

        // Verify that the enemy location is corrected to be at the left border

        assertTrue(collisionDetector.isCollision(fastEnemy.getEnemyLocationX(), fastEnemy.getEnemyLocationY()));
    }

    @Test
    //create a new enemy
    public void testMoveBeyondRightBorder() {
        ImageView sprite = new ImageView(null);
        FastEnemy fastEnemy = new FastEnemy(sprite);
        CollisionDetector collisionDetector = new CollisionDetector();

        // Set enemy initially beyond the right border
        int initialX = 850;
        int initialY = 750;
        fastEnemy.setEnemyLocationX(initialX);
        fastEnemy.setEnemyLocationY(initialY);

        // Move the enemy
        fastEnemy.move();

        // Verify that the enemy location is corrected to be at the right border

        assertTrue(collisionDetector.isCollision(fastEnemy.getEnemyLocationX(), fastEnemy.getEnemyLocationY()));
    }

    @Test
    public void testMoveBeyondTopBorder() {
        ImageView sprite = new ImageView(null);
        FastEnemy fastEnemy = new FastEnemy(sprite);
        CollisionDetector collisionDetector = new CollisionDetector();

        // Set enemy initially beyond the right border
        int initialX = 850;
        int initialY = 1350;
        fastEnemy.setEnemyLocationX(initialX);
        fastEnemy.setEnemyLocationY(initialY);

        // Move the enemy
        fastEnemy.move();

        // Verify that the enemy location is corrected to be at the right border

        assertTrue(collisionDetector.isCollision(fastEnemy.getEnemyLocationX(), fastEnemy.getEnemyLocationY()));
    }

    @Test
    public void testMoveWithinBounds() {
        ImageView sprite = new ImageView(null);
        FastEnemy fastEnemy = new FastEnemy(sprite);
        CollisionDetector collisionDetector = new CollisionDetector();

        // Initial enemy location within bounds
        int initialX = 450;
        int initialY = 850;
        fastEnemy.setEnemyLocationX(initialX);
        fastEnemy.setEnemyLocationY(initialY);

        // Move the enemy within bounds
        fastEnemy.move();

        // Verify that the enemy location is still within bounds
        assertTrue(collisionDetector.isCollision(fastEnemy.getEnemyLocationX(), fastEnemy.getEnemyLocationY()));
    }
}


 */