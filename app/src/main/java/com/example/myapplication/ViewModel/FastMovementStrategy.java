package com.example.myapplication.ViewModel;

import android.os.SystemClock;
import android.widget.ImageView;

public class FastMovementStrategy implements EnemyMovementStrategy {
    public static final long DURATION = 500;
    private long lastDirectionToggleTime;
    private boolean movingRight = true;
    private int speed = 100;

    public FastMovementStrategy() {
        lastDirectionToggleTime = SystemClock.elapsedRealtime() - DURATION;
    }

    @Override
    public void move(ImageView sprite) {
        long currentTime = SystemClock.elapsedRealtime();

        if (currentTime - lastDirectionToggleTime > DURATION) {
            movingRight = !movingRight;
            lastDirectionToggleTime = currentTime;
        }

        float currentX = sprite.getX();

        if (movingRight) {
            sprite.setX(currentX + speed);
        } else {
            sprite.setX(currentX - speed);
        }

    }
}
