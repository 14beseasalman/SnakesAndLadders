package com.example.asad.snakesandladders;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 960;
    public static final int MOVESPEED = -5;


    private MainThread thread;
    private Background background;
    public Player player1;
    public Player player2;

    //    public void AddIndicators(String indicator,boolean value,String whichPlayer) {
//        if (whichPlayer.equals(Player.PLAYER_1))
//        {
//            switch(indicator)
//            {
//                case "DT":  player1.setDoubleTurn(true);  break;
//                case "SL":  player1.setSnakeLock(true);  break;
//            }
//
//        }
//        if(whichPlayer.equals(Player.PLAYER_2)){
//            switch(indicator)
//            {
//                case "DT": player2.setDoubleTurn(true);   break;
//                case "SL": player2.setSnakeLock(true);   break;
//            }
//
//        }
//
//
//    }
    public void ChangeXOfPlayer(int x, String whichPlayer) {
        if (whichPlayer.equals(Player.PLAYER_1))
            player1.x = x;
        if (whichPlayer.equals(Player.PLAYER_2))
            player2.x = x;

    }

    public void ChangeYofPlayer(int y, String whichPlayer) {
        if (whichPlayer.equals(Player.PLAYER_1))
            player1.y = y;
        if (whichPlayer.equals(Player.PLAYER_2))
            player2.y = y;
    }

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
        player1 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_red), 65, 25, 3);
        player2 = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_green), 65, 25, 3);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {

            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();


            }
            retry = false;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    public void update() {

        background.update();
        player1.update();
        player2.update();

    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            background.draw(canvas);
            player1.draw(canvas);
            player2.draw(canvas);
        }
    }


}
