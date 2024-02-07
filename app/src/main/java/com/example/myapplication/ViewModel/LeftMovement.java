package com.example.myapplication.ViewModel;

public class LeftMovement implements MovementStrategy {
    @Override
    public int[] move(int x, int y) {
        return new int[]{x - 50, y};
    }
}
