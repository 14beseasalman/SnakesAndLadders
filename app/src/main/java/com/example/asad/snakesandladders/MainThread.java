package com.example.asad.snakesandladders;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;
    private long waitTime;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public int GetHeight() {

        return canvas.getHeight();

    }

    public int GetWidth() {
        return canvas.getWidth();

    }

    public void setRunning(boolean run) {

        running = run;
    }

    @Override
    public void run() {


        long startTime = 0;
        long timeMillis;
        long total_Time = 0;
        int FrameCount = 0;
        long targetTime = 1000 / FPS;
        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {

                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {


            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {


                this.sleep(waitTime);
            } catch (Exception e) {
            }
        }

    }
}
