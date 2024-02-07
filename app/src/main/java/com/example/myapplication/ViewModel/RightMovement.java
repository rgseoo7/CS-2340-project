package com.example.myapplication.ViewModel;

public class RightMovement implements MovementStrategy {
    @Override
    public int[] move(int x, int y) {
        return new int[]{x + 50, y};
    }
}
