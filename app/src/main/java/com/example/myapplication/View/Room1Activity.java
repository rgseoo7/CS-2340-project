package com.example.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

public class Room1Activity extends AppCompatActivity implements PlayerMovementObserver {
    private double playerScore;
    private SpriteMover spriteMover;
    private CollisionDetector collisionDetector;

    private Handler handler = new Handler();
    private String difficulty;
    private String playerName;
    private int playerHP;
    private int spritePosition;
    private Enemy erraticEnemy;

    private Enemy erraticEnemy2;
    //private boolean isEndScreenDisplayed = false;

    private ImageView shieldPower;

    // attack or hit sound
    private MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        initializeViews();
    }

    protected void initializeViews() {

        Player player = Player.getInstance();

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

        //Enemy code
        ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1);
        erraticEnemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.ERRATIC, enemySprite);
        player.addObserver(erraticEnemy);
        ImageView enemySprite2 = findViewById(R.id.iv_enemy_sprite2);
        erraticEnemy2 = EnemyFactory.createEnemy(EnemyFactory.EnemyType.ERRATIC, enemySprite2);
        player.addObserver(erraticEnemy2);

        shieldPower = findViewById(R.id.shield);
        shieldPower.setImageResource(R.drawable.shieldpowerup);


        // Set sprite based on selection
        switch (spritePosition) { case 0: ivPlayerSprite.setImageResource(R.drawable.sprite1);
            break;
        case 1: ivPlayerSprite.setImageResource(R.drawable.sprite2);
            break;
        case 2: ivPlayerSprite.setImageResource(R.drawable.sprite3);
            break;
        default: break;
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

            if (Math.abs(player.getX() - erraticEnemy.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - erraticEnemy.getEnemyLocationY()) < 150) {
                erraticEnemy.destroy();
                player.setPlayerScore(player.getPlayerScore() + 50);
            }
            if (Math.abs(player.getX() - erraticEnemy2.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - erraticEnemy2.getEnemyLocationY()) < 150) {
                erraticEnemy2.destroy();
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
        if (isCollisionWithDamageReductionBoost(newX, newY, shieldPower)) {
            player.activateDamageReduction();
            shieldPower.setVisibility(View.INVISIBLE); // Hide the power-up
            shieldPower.setX(0);
            shieldPower.setY(0);
            player.deactivateBoost();
        }
        if (newX >= 466 && newX <= 616 && newY >= 417 && newY <= 417 + 5) {
            transitionToNextActivity();
        }
    }
    private boolean isCollisionWithDamageReductionBoost(int playerX,
                                                        int playerY, ImageView shieldBoost) {
        // Implement more robust collision detection logic here
        int boostX = (int) shieldBoost.getX();
        int boostY = (int) shieldBoost.getY();
        return Math.abs(playerX - boostX) < 50 && Math.abs(playerY - boostY) < 50;
    }


    public void transitionToNextActivity() {
        Intent intent = new Intent(Room1Activity.this, Room2Activity.class);
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
            }
            ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1);
            ImageView enemySprite2 = findViewById(R.id.iv_enemy_sprite2);
            erraticEnemy.setEnemyLocationX((int) enemySprite.getX());
            erraticEnemy.setEnemyLocationY((int) enemySprite.getY());
            erraticEnemy2.setEnemyLocationX((int) enemySprite2.getX());
            erraticEnemy2.setEnemyLocationY((int) enemySprite2.getY());

            // Move the fast enemy, if this is on the UI thread already
            if (erraticEnemy != null) {
                erraticEnemy.move();
                erraticEnemy2.move();
            }

            handler.postDelayed(this, 500);
        }
    };
    public double getPlayerScore() {
        return playerScore;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerScore(int score) {
        this.playerScore = score;
    }

}