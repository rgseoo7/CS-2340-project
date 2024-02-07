package com.example.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.EnemyFactory;
//import com.example.myapplication.Model.ErraticEnemy;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.CollisionDetector;
import com.example.myapplication.ViewModel.DownMoveStrategy;
import com.example.myapplication.ViewModel.LeftMovement;
import com.example.myapplication.ViewModel.PlayerMovementObserver;
import com.example.myapplication.ViewModel.RightMovement;
import com.example.myapplication.ViewModel.SpriteMover;
import com.example.myapplication.ViewModel.UpMoveStrategy;

public class Room3Activity extends AppCompatActivity implements PlayerMovementObserver {
    private double playerScore;
    private SpriteMover spriteMover;
    private CollisionDetector collisionDetector;

    private Handler handler = new Handler();
    private String difficulty;
    private String playerName;
    private int playerHP;
    private int spritePosition;

    private Enemy erraticEnemy;

    private Enemy slowEnemy;
    //private boolean isEndScreenDisplayed = false;

    private MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        Player player = Player.getInstance();

        // Fetch the initial data passed in the Intent
        //playerName = getIntent().getStringExtra("playerName");
        //playerHP = getIntent().getIntExtra("playerHP", 100);
        //spritePosition = getIntent().getIntExtra("spritePosition", -1);
        //difficulty = getIntent().getStringExtra("difficulty");
        //playerScore = getIntent().getDoubleExtra("playerScore", 0);

        // Initialize Views
        TextView tvPlayerName = findViewById(R.id.tv_player_name);
        TextView tvPlayerHP = findViewById(R.id.tv_player_hp);
        TextView tvDifficulty = findViewById(R.id.tv_difficulty);
        TextView tvScore = findViewById(R.id.tv_score);

        playerHP = Player.getInstance().getPlayerHP();
        playerName = Player.getInstance().getPlayerName();
        difficulty = Player.getInstance().getDifficulty();
        playerScore = Player.getInstance().getPlayerScore();
        spritePosition = Player.getInstance().getSpritePosition();


        final ImageView ivPlayerSprite = findViewById(R.id.iv_player_sprite);

        // Set initial data to Views
        tvPlayerName.setText("Player: " + playerName);
        tvPlayerHP.setText("HP: " + playerHP);
        tvDifficulty.setText("Difficulty: " + difficulty);
        tvScore.setText("Score: " + playerScore);

        ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1);
        erraticEnemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.ERRATIC, enemySprite);
        player.addObserver(erraticEnemy);
        ImageView enemySprite1 = findViewById(R.id.iv_enemy_sprite2);
        slowEnemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.SLOW, enemySprite1);
        player.addObserver(slowEnemy);

        // Set sprite based on selection
        switch (spritePosition) {
        case 0:
            ivPlayerSprite.setImageResource(R.drawable.sprite1);
            break;
        case 1:
            ivPlayerSprite.setImageResource(R.drawable.sprite2);
            break;
        case 2:
            ivPlayerSprite.setImageResource(R.drawable.sprite3);
            break;
        default:
            break;
        }

        // Initialize CollisionDetector and SpriteMover
        collisionDetector = new CollisionDetector();
        spriteMover = new SpriteMover(collisionDetector);
        spriteMover.setMovementObserver(this);

        findViewById(R.id.btn_attack).setOnClickListener(v -> {
            // play attack sound
            sound = MediaPlayer.create(this, R.raw.attack);
            if (sound != null) {
                sound.start();
            }
            Log.d("attack button clicked", "attack button clicked");
            Log.d("Room3Activity", "Player X: " + player.getX() + ", Y: " + player.getY());
            Log.d("Room3Activity", "Erratic Enemy X: "
                    + erraticEnemy.getEnemyLocationX() + ", Y: "
                    + erraticEnemy.getEnemyLocationY());
            Log.d("Room3Activity", "Slow Enemy X: "
                    + slowEnemy.getEnemyLocationX() + ", Y: " + slowEnemy.getEnemyLocationY());

            if (Math.abs(player.getX() - erraticEnemy.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - erraticEnemy.getEnemyLocationY()) < 150) {
                erraticEnemy.destroy();
                Log.d("erratic enemy", "erratic enemy");
                player.setPlayerScore(player.getPlayerScore() + 50);
            }
            if (Math.abs(player.getX() - slowEnemy.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - slowEnemy.getEnemyLocationY()) < 150) {
                slowEnemy.destroy();
                Log.d("slow enemy", "slow enemy");

                player.setPlayerScore(player.getPlayerScore() + 50);
            }
        });

        // Attach strategies to buttons
        findViewById(R.id.btn_left).setOnClickListener(v -> {
            spriteMover.setMovementStrategy(new LeftMovement());
            moveSprite(ivPlayerSprite);
        });

        findViewById(R.id.btn_right).setOnClickListener(v -> {
            spriteMover.setMovementStrategy(new RightMovement());
            moveSprite(ivPlayerSprite);
        });

        findViewById(R.id.btn_up).setOnClickListener(v -> {
            spriteMover.setMovementStrategy(new UpMoveStrategy());
            moveSprite(ivPlayerSprite);
        });

        findViewById(R.id.btn_down).setOnClickListener(v -> {
            spriteMover.setMovementStrategy(new DownMoveStrategy());
            moveSprite(ivPlayerSprite);
        });

        handler.post(decreaseRunnable);
    }

    private void moveSprite(ImageView ivPlayerSprite) {
        float currentX = ivPlayerSprite.getX();
        float currentY = ivPlayerSprite.getY();
        spriteMover.moveSprite(ivPlayerSprite, (int) currentX, (int) currentY);
    }

    private int oldHP = playerHP;

    @Override
    public void onPlayerMove(int newX, int newY) {
        TextView tvhp = findViewById(R.id.tv_player_hp);
        ImageView ivPlayerSprite = findViewById(R.id.iv_player_sprite);
        ivPlayerSprite.setX(newX);
        ivPlayerSprite.setY(newY);
        Player player = Player.getInstance();
        player.setXY(newX, newY);
        if (player.getPlayerHP() < oldHP) {
            sound = MediaPlayer.create(this, R.raw.hurt);
            if (sound != null) {
                sound.start();
            }
        }
        oldHP = player.getPlayerHP();
        tvhp.setText("HP: " + player.getPlayerHP());
        if (newX >= 466 && newX <= 616 && newY >= 417 && newY <= 417 + 5) {
            transitionToNextActivity();
        }
    }

    private void transitionToNextActivity() {
        Intent intent = new Intent(Room3Activity.this, EndScreen.class);
        startActivity(intent);
    }

    public void decreaseValue() {
        Player player = Player.getInstance();
        synchronized (player.getCommonLock()) {
            if (player.getPlayerHP() <= 0 && !player.isEndScreenDisplayed()) {
                player.setEndScreenDisplayed(true);
                Intent endScreenIntent = new Intent(this, EndScreenLoss.class);
                endScreenIntent.putExtra("playerScore", player.getPlayerScore());
                endScreenIntent.putExtra("playerName", player.getPlayerName());
                startActivity(endScreenIntent);
                finish();
            }
        }
        if (player.getPlayerHP() > 0) {
            player.setPlayerScore(player.getPlayerScore() - 0.1);
            TextView tvScore = findViewById(R.id.tv_score);
            tvScore.setText("Score: " + String.format("%.1f", player.getPlayerScore()));
        }
    }


    private Runnable decreaseRunnable = new Runnable() {
        @Override
        public void run() {
            if (playerHP > 0) {
                decreaseValue();

                if (slowEnemy != null) {
                    slowEnemy.move();
                    erraticEnemy.move();
                }
            }
            ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1);
            ImageView enemySprite2 = findViewById(R.id.iv_enemy_sprite2);
            erraticEnemy.setEnemyLocationX((int) enemySprite.getX());
            erraticEnemy.setEnemyLocationY((int) enemySprite.getY());
            slowEnemy.setEnemyLocationX((int) enemySprite2.getX());
            slowEnemy.setEnemyLocationY((int) enemySprite2.getY());
                handler.postDelayed(this, 500);
            }
    };
}