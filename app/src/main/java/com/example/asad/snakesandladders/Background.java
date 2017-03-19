package com.example.asad.snakesandladders;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Background {

    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res) {

        image = res;
        dx = GamePanel.MOVESPEED;


    }

    public void update() {


    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 400f, 0f, null);


    }
}
