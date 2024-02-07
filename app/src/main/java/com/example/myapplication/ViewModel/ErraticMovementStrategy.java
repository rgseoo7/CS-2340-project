package com.example.myapplication.ViewModel;
//import java.util.Random;

import android.os.SystemClock;
import android.widget.ImageView;

public class ErraticMovementStrategy implements EnemyMovementStrategy {
    private static final long DURATION = 250; //Duration of sprite movemnt
    private long lastDirectionToggleTime;
    private boolean movingRight = true;

    private boolean movingLeft = true;

    private int speed = 25; // Sprite speed


    public ErraticMovementStrategy() {
        lastDirectionToggleTime = SystemClock.elapsedRealtime() - DURATION;
    }


    @Override
    public void move(ImageView sprite) {
        long currentTime = SystemClock.elapsedRealtime();


        if (currentTime - lastDirectionToggleTime > DURATION) {
            movingRight = !movingRight;
            movingLeft = !movingLeft;
            lastDirectionToggleTime = currentTime;
        }

        float currentX = sprite.getX();
        float currentY = sprite.getY();

        if (movingRight) {
            sprite.setX(currentX + speed);
        } else {
            sprite.setX(currentX - speed);
        }

        if (movingLeft) {
            sprite.setY(currentY + speed);
        } else {
            sprite.setY(currentY - speed);
        }

    }
}
