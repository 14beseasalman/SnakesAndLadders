package com.example.asad.snakesandladders;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Handler;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GamePanel gameView;
    Button human, computer;

    Boolean DT1 = false, DT2 = false, SL1 = false, SL2 = false;
    Map<Integer, Integer> Ladders = new HashMap<>();
    Map<Integer, Integer> Snakes = new HashMap<>();
    Toast toast;
    int GAME_LEVEL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLevel(getIntent());

        RelativeLayout game = (RelativeLayout) findViewById(R.id.activity_main);
        gameView = new GamePanel(this);

        addSnakesAndLadderPositions();

        game.addView(gameView);
        human = (Button) findViewById(R.id.btn_player_turn);
        human.setOnClickListener(this);
        computer = (Button) findViewById(R.id.btn_computer_turn);
        computer.setOnClickListener(this);

        computer.setClickable(false);
    }

    @Override
    public void onClick(View view) {

        Random r = new Random();
        int i1 = r.nextInt(6) + 1;

        if (view.getId() == R.id.btn_computer_turn) {
            i1 = difficultRoll(Player1().initalposition);
            human.setClickable(true);
            computer.setClickable(false);
            if (!(Player1().initalposition + i1 > 100)) {
                Player1().initalposition = Player1().initalposition + i1;
                if (Ladders.containsKey(Player1().initalposition)) {
                    Player1().initalposition = Ladders.get(Player1().initalposition);
                    DT1 = true;
                    SL1 = false;
                } else if (Snakes.containsKey(Player1().initalposition)) {
                    Player1().initalposition = Snakes.get(Player1().initalposition);

                    SL1 = true;
                    DT1 = false;
                }
                if (SL1 && i1 == 6)
                    SL1 = false;

                if (!SL1) {
                    ConvertPositionToXYCoords(Player1().initalposition, Player.PLAYER_1);
                    showRollToast(Player.PLAYER_1,i1);
                }
                if (DT1) {

                    DT1 = false;
                    human.setClickable(true);
                    computer.setClickable(false);
                }
            }
        }
        if (view.getId() == R.id.btn_player_turn) {

            human.setClickable(false);
            computer.setClickable(true);
            if (!(Player2().initalposition + i1 > 100)) {
                Player2().initalposition = Player2().initalposition + i1;
                if (Ladders.containsKey(Player2().initalposition)) {
                    Player2().initalposition = Ladders.get(Player2().initalposition);
                    DT2 = true;
                    SL2 = false;
                } else if (Snakes.containsKey(Player1().initalposition)) {
                    Player2().initalposition = Snakes.get(Player2().initalposition);
                    SL1 = true;
                    DT2 = false;
                }
                if (SL2 && i1 == 6)
                    SL2 = false;

                if (!SL2) {
                    ConvertPositionToXYCoords(Player2().initalposition, Player.PLAYER_2);
                    showRollToast(Player.PLAYER_2,i1);
                }
                if (DT2) {
                    DT2 = false;
                    human.setClickable(true);
                    computer.setClickable(false);
                }
            }
        }
        if(Player1().initalposition == 100)
            winner(Player.PLAYER_1);
        if(Player2().initalposition == 100)
            winner(Player.PLAYER_2);
    }

    Player Player1() {
        return gameView.player1;
    }

    Player Player2() {
        return gameView.player2;
    }

    private void ConvertPositionToXYCoords(int initalposition, String turn) {
        int row = initalposition / 10;

        int column;
        if (initalposition > 10)
            column = initalposition % 10;
        else
            column = initalposition;

        if (row == 0 || row % 2 == 0) {
            if (column != 0)
                column = 400 + ((column - 1) * 96);
            else
                column = 400 + (column * 96);
        } else {
            column = (960 + 400) - ((column) * 96);
        }
        if (initalposition % 10 == 0)
            row = 880 - ((row - 1) * 96);
        else
            row = 880 - ((row) * 96);

        gameView.ChangeXOfPlayer(column, turn);
        gameView.ChangeYofPlayer(row, turn);

    }
    private void winner(String player){
        if(player.equalsIgnoreCase(Player.PLAYER_1))
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Computer Won!")
                    .setConfirmText("Restart Game?")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = getIntent();
                            intent.putExtra("GAME_LEVEL", GAME_LEVEL);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .show();
        if(player.equalsIgnoreCase(Player.PLAYER_2))
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Player Won!")
                    .setConfirmText("Restart Game?")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = getIntent();
                            intent.putExtra("GAME_LEVEL", GAME_LEVEL);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .show();
    }
    private void addSnakesAndLadderPositions() {
        //Ladders
        Ladders.put(2, 38);
        Ladders.put(7, 14);
        Ladders.put(8, 31);
        Ladders.put(15, 26);
        Ladders.put(28, 84);
        Ladders.put(36, 44);
        Ladders.put(51, 67);
        Ladders.put(71, 91);
        Ladders.put(78, 98);
        Ladders.put(87, 94);
        Ladders.put(21, 42);

        //Snakes
        Snakes.put(16, 6);
        Snakes.put(46, 25);
        Snakes.put(49, 11);
        Snakes.put(62, 19);
        Snakes.put(64, 60);
        Snakes.put(74, 53);
        Snakes.put(89, 68);
        Snakes.put(92, 88);
        Snakes.put(95, 75);
        Snakes.put(99, 80);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setLevel(intent);
    }
    private void setLevel(Intent i){

        try{
            GAME_LEVEL = i.getExtras().getInt("GAME_LEVEL");}
        catch(Exception e) {
            GAME_LEVEL = 2;
        }
        toast = Toast.makeText(this, "Level "+GAME_LEVEL, Toast.LENGTH_SHORT);
        toast.show();
    }
    private int difficultRoll(int currentPos){
        //greedy approach to maximize chances of winning
        //according to difficulty level
        Random rand = new Random();

        int roll=rand.nextInt(6) + 1;

        ArrayList<Integer> list = new ArrayList<>();

        for(int i =0; i<GAME_LEVEL; i++)
            list.add(rand.nextInt(6) + 1);
        //try to go for ladders
        for(int num:list)
            if(Ladders.containsKey(num+currentPos))
                roll = num;

        //avoid snakes
        while(Snakes.containsKey(currentPos+roll)){
            roll = rand.nextInt(6) + 1;
        }

        return roll;
    }

    void showRollToast(String player, int roll){
        if(toast!=null)
            toast.cancel();
        String msg; int gravity;
        if(player.equalsIgnoreCase(Player.PLAYER_1)) {
            msg = "Computer Rolled ";
            gravity = Gravity.TOP | Gravity.RIGHT;
        }
        else{
            msg = "Player Rolled ";
            gravity = Gravity.TOP | Gravity.LEFT;
        }
        toast = Toast.makeText(getApplicationContext(), msg+roll, Toast.LENGTH_SHORT);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}
