package com.example.myapplication.ViewModel;

import android.widget.ImageView;
import android.os.SystemClock;

public class SteadyMovementStrategy implements EnemyMovementStrategy {

    private static final long DURATION = 500;
    private long lastDirectionToggleTime;
    private boolean movingRight = true;
    private int speed = 50;

    public SteadyMovementStrategy() {
        lastDirectionToggleTime = SystemClock.elapsedRealtime() - DURATION;
    }

    @Override
    public void move(ImageView sprite) {
        long currentTime = SystemClock.elapsedRealtime();

        if (currentTime - lastDirectionToggleTime > DURATION) {
            movingRight = !movingRight;
            lastDirectionToggleTime = currentTime;
        }

        float currentY = sprite.getY();

        if (movingRight) {
            sprite.setY(currentY + speed);
        } else {
            sprite.setY(currentY - speed);
        }

    }
}