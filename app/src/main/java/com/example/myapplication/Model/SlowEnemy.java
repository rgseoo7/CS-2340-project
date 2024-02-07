package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.SlowMovementStrategy;

public class SlowEnemy extends Enemy {
    public SlowEnemy(ImageView sprite) {
        super(sprite);
        this.movementStrategy = new SlowMovementStrategy();
        sprite.setImageResource(R.drawable.sloth);
    }

    @Override
    public void move() {
        movementStrategy.move(sprite);
        ensureWithinBounds();
    }
}
