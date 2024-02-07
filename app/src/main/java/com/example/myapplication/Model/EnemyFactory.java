package com.example.myapplication.Model;

import android.widget.ImageView;

// EnemyFactory.java
public class EnemyFactory {
    // The EnemyType enum defines a set of constants to represent various enemy behaviors.
    public enum EnemyType {
        FAST, //represents enemy that moves fast
        SLOW, //enemy that moves slowly
        ERRATIC, //enemy that moves erratic
        STEADY //enemy with consistent movement
    }

    public static Enemy createEnemy(EnemyType type, ImageView sprite) {
        switch (type) {
        case FAST: return new FastEnemy(sprite);
        case SLOW: return new SlowEnemy(sprite);
        case ERRATIC: return new ErraticEnemy(sprite);
        case STEADY: return new SteadyEnemy(sprite);
        default: return null;
        }
    }
}

