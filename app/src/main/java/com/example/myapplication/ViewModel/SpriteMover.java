package com.example.myapplication.ViewModel;

import android.util.Log;
import android.widget.ImageView;

public class SpriteMover {
    private MovementStrategy movementStrategy;
    private static final String TAG = "SpriteMover"; // Tag for logging
    private CollisionDetector collisionDetector;
    private PlayerMovementObserver movementObserver;

    public SpriteMover(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void setMovementObserver(PlayerMovementObserver movementObserver) {
        this.movementObserver = movementObserver;
    }

    public void moveSprite(ImageView sprite, int x, int y) {
        if (movementStrategy != null && movementObserver != null) {
            int[] newCoordinates = movementStrategy.move(x, y);
            Log.d(TAG, "Attempting to move sprite to X: " + x + ", Y: " + y);
            if (!collisionDetector.isCollision(newCoordinates[0], newCoordinates[1])) {
                movementObserver.onPlayerMove(newCoordinates[0], newCoordinates[1]);
            }
        }
    }
}
