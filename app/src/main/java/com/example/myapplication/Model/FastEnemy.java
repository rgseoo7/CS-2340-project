package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.FastMovementStrategy;

public class FastEnemy extends Enemy {
    public FastEnemy(ImageView sprite) {
        super(sprite);
        this.movementStrategy = new FastMovementStrategy();
        sprite.setImageResource(R.drawable.cheetah);
    }

    @Override
    public void move() {
        movementStrategy.move(sprite);
        ensureWithinBounds();
    }

}
