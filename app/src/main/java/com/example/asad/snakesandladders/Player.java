package com.example.asad.snakesandladders;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private double dya = 1;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    public static final String PLAYER_1 = "PLAYER_1";
    public static final String PLAYER_2 = "PLAYER_2";
    int initalposition = 1;

    public Player(Bitmap res, int w, int h, int numFrames) {
        x = 410;
        spritesheet = res;
        y = GamePanel.HEIGHT - 75;
        dy = 0;
        score = 0;
        height = h;
        width = w;
        Bitmap[] image = new Bitmap[numFrames];
        for (int i = 0; i < image.length; i++) {

            image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {

            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if (up) {
//System.out.println("UP");
            dy = -1;

        } else {

            dy = 1;
        }

        if (dy > 14) dy = 14;
        if (dy < -14) dy = -14;

        // y+=dy;

        // System.out.println(x);
        dy = 0;


    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(animation.getImage(), x, y, null);

    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDYA() {
        dya = 0;
    }

    public void resetScore() {
        score = 0;
    }
}
