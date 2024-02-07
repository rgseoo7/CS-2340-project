package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.ErraticMovementStrategy;

public class ErraticEnemy extends Enemy {
    public ErraticEnemy(ImageView sprite) {
        super(sprite);
        this.movementStrategy = new ErraticMovementStrategy();
        sprite.setImageResource(R.drawable.frog);
    }

    @Override
    public void move() {
        movementStrategy.move(sprite);
        ensureWithinBounds();
    }
}
