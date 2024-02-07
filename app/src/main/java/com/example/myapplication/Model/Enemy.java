package com.example.myapplication.Model;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.widget.ImageView;

import com.example.myapplication.ViewModel.EnemyMovementStrategy;
import com.example.myapplication.ViewModel.PlayerObserver;

public abstract class Enemy implements PlayerObserver {
    protected ImageView sprite;
    protected EnemyMovementStrategy movementStrategy;
    private boolean isDead;
    private int playerLocationX;
    private int playerLocationY;

    private int enemyLocationX;

    private int enemyLocationY;

    public int getPlayerLocationX() {
        return playerLocationX;
    }

    public int getPlayerLocationY() {
        return playerLocationY;
    }

    public void setPlayerLocationX(int x) {
        this.playerLocationX = x;
    }

    public void setPlayerLocationY(int y) {
        this.playerLocationY = y;
    }

    public void setEnemyLocationX(int x) {
        this.enemyLocationX = x;
    }

    public int getEnemyLocationX() {
        return enemyLocationX;
    }
    public int getEnemyLocationY() {
        return enemyLocationY;
    }

    public void setEnemyLocationY(int y) {
        this.enemyLocationY = y;
    }

    public boolean checkPlayerLocationX(int x) {
        return this.playerLocationX == x;
    }

    public boolean checkPlayerLocationY(int y) {
        return this.playerLocationY == y;
    }


    public Enemy(ImageView sprite) {
        this.sprite = sprite;
        Player.getInstance().addObserver(this); // Register as an observer
    }

    public ImageView getSprite() {
        return sprite;
    }

    public abstract void move();

    protected void ensureWithinBounds() {
        float x = sprite.getX();
        float y = sprite.getY();
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        if (x < 0) {
            sprite.setX(0);
        }
        if (y < 0) {
            sprite.setY(0);
        }
        if (x + sprite.getWidth() > screenWidth) {
            sprite.setX(screenWidth - sprite.getWidth());
        }
        if (y + sprite.getHeight() > screenHeight) {
            sprite.setY(screenHeight - sprite.getHeight());
        }
    }

    @Override
    public void updatePlayerLocation(int x, int y) {
        if (!isDead) {
            this.playerLocationX = x;
            this.playerLocationY = y;
            if (Math.abs(playerLocationX - enemyLocationX) <= 50
                    && Math.abs(playerLocationY - enemyLocationY) <= 50) {
                Player.getInstance().setPlayerHealth(Player.getInstance().getPlayerHP());
            }
        }
    }


    public void destroy() {
        if (!isDead) {
            isDead = true;
            Player.getInstance().removeObserver(this);
            ObjectAnimator animation = ObjectAnimator.ofFloat(sprite, "alpha", 1.0f, 0.0f);
            animation.setDuration(1000); //miliseconds so that is 1 second
            animation.start();
        }

    }
}
