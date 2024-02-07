package com.example.myapplication.ViewModel;

public class UpMoveStrategy implements MovementStrategy {
    @Override
    public int[] move(int x, int y) {
        return new int[]{x, y - 50};
    }
}
