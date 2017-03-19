package com.example.asad.snakesandladders;

import android.graphics.Rect;


public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int width;
    protected boolean doubleTurn;
    protected boolean SnakeLock;
    protected int height;

    public void setDoubleTurn(boolean t) {
        this.doubleTurn = t;
    }

    public boolean getDoubleTurn() {
        return this.doubleTurn;
    }

    public void setSnakeLock(boolean t) {
        this.SnakeLock = t;
    }

    public boolean getSnakeLock() {
        return this.SnakeLock;
    }

    public void setX(int x) {
        this.x = x;

    }

    public void setY(int y) {
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;

    }

    public int getWidth() {
        return width;

    }

    public Rect getRectangle() {

        return new Rect(x, y, x + width, y + height);
    }


}
