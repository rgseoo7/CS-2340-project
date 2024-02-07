package com.example.myapplication.ViewModel;

public class CollisionDetector {
    private static final int WALL_LEFT = 130;
    private static final int WALL_TOP = 417; //Higher number puts the top lower
    private static final int WALL_RIGHT = 840; //Working
    private static final int WALL_BOTTOM = 1200; //Working

    public boolean isCollision(int x, int y) {
        return x < WALL_LEFT || x > WALL_RIGHT || y < WALL_TOP || y > WALL_BOTTOM;
    }

    public static int getWallLeft() {
        return  WALL_LEFT;
    }

    public static int getWallRight() {
        return  WALL_RIGHT;
    }

    public static int getWallTop() {
        return  WALL_TOP;
    }

    public static int getWallBottom() {
        return  WALL_BOTTOM;
    }
}
