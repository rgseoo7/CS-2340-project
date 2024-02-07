package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.SteadyMovementStrategy;

public class SteadyEnemy extends Enemy {
    public SteadyEnemy(ImageView sprite) {
        super(sprite);
        this.movementStrategy = new SteadyMovementStrategy();
        sprite.setImageResource(R.drawable.turtle);
    }

    @Override
    public void move() {
        movementStrategy.move(sprite);
        ensureWithinBounds();
    }
}
