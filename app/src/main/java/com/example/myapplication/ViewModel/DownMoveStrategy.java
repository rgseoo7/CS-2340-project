package com.example.myapplication.ViewModel;

public class DownMoveStrategy implements MovementStrategy {
    @Override
    public int[] move(int x, int y) {
        return new int[]{x, y + 50};
    }
}
