package fall2018.csc207_project.MineSweeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Random;

import fall2018.csc207_project.GameCenter.GlobalCenter;
import fall2018.csc207_project.GameCenter.LocalGameCenter;
import fall2018.csc207_project.R;
import fall2018.csc207_project.SlidingTileGame.SlidingTileGame;
import fall2018.csc207_project.UI.StartingActivity;


public class GameComplexityActivity extends Activity {

    private GlobalCenter globalCenter;
    private LocalGameCenter localCenter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);
        globalCenter = (GlobalCenter) (getIntent().getSerializableExtra("GlobalCenter"));
        localCenter = globalCenter.getLocalGameCenter(globalCenter.getCurrentPlayer().
                getUsername());
        add10BombsButtonListener();
        add15BombsButtonListener();
        add20BombsButtonListener();
        addCrazyModeButtonListener();
        addLuckyModeButtonListener();

    }

//    private int numBombs(int bombNum){
//        return bombNum;
//    }

    private void add10BombsButtonListener(){
        Button l10Button = findViewById(R.id.grid10);
        l10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(10);
                finish();
            }
        });
    }

    private void add15BombsButtonListener(){
        Button l15Button = findViewById(R.id.grid15);
        l15Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(15);
                finish();
            }
        });
    }

    private void addCrazyModeButtonListener(){
        Button crazyButton = findViewById(R.id.crazy);
        crazyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =
                        new AlertDialog.Builder(GameComplexityActivity.this);
                builder1.setMessage("This mode is crazy! The number of bombs is quite " +
                        "tricky, and this mode is just for fun, " +
                        "not for mark, would you like to go?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes, please",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                switchToGame(98);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No, thanks",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    private void addLuckyModeButtonListener(){
        Button luckButton = findViewById(R.id.lucky);
        luckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 =
                        new AlertDialog.Builder(GameComplexityActivity.this);
                builder1.setMessage("Good luck for you! The number of bombs is random " +
                        ", and this mode is just for fun, " +
                        "not for mark, would you like to go?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes, please",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Random random = new Random();
                                int comp = random.nextInt(101);
                                if (comp > 100) {
                                    comp = 100;
                                } else if (comp < 0) {
                                    comp = 0;
                                }
                                switchToGame(comp);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No, thanks",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    private void add20BombsButtonListener(){
        Button l20Button = findViewById(R.id.grid20);
        l20Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(20);
                finish();
            }
        });
    }

//    private void switchToGame(int numBombs) {
//        MineSweeperGame game = new MineSweeperGame(numBombs);
//        Intent tmp = new Intent(this, GameActivity.class);
//        tmp.putExtra("GlobalCenter", globalCenter);
//        tmp.putExtra("minesweeperGame",game);
//        startActivity(tmp);
//        finish();
//    }

    private void switchToGame(int complexity) {
        LinkedList<Object> settings = new LinkedList<>();
        settings.add(complexity);
        MineSweeperGame game = (MineSweeperGame) localCenter.newGame("minesweeperGame",
                settings);
        Intent tmp = new Intent(this, GameActivity.class);
        tmp.putExtra("GlobalCenter", globalCenter);
        localCenter.setCurGame(game);
        startActivity(tmp);
        finish();
    }

    public void onBackPressed() {
        Intent tmp = new Intent(this, StartingActivity.class);
        tmp.putExtra("GlobalCenter", globalCenter);
        startActivity(tmp);
        finish();
    }
}
