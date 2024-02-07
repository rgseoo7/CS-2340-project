package com.example.myapplication.ViewModel;

import android.os.SystemClock;
import android.widget.ImageView;

public class SlowMovementStrategy implements EnemyMovementStrategy {
    private static final long DURATION = 500; //Duration of sprite movemnt
    private long lastDirectionToggleTime;
    private boolean movingRight = true;
    private int speed = 25; // Sprite speed

    public SlowMovementStrategy() {
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
