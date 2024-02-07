package com.example.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
//import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.EnemyFactory;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.CollisionDetector;
import com.example.myapplication.ViewModel.DownMoveStrategy;
import com.example.myapplication.ViewModel.LeftMovement;
import com.example.myapplication.ViewModel.PlayerMovementObserver;
import com.example.myapplication.ViewModel.RightMovement;
import com.example.myapplication.ViewModel.SpriteMover;
import com.example.myapplication.ViewModel.UpMoveStrategy;


public class GameScreenActivity extends AppCompatActivity implements PlayerMovementObserver {

    private double playerScore = 500;
    private SpriteMover spriteMover;
    private CollisionDetector collisionDetector; //testing github review
    private Handler handler = new Handler();
    private String difficulty;
    private String playerName;
    private int playerHP;
    private int spritePosition;
    private Enemy fastEnemy;
    private Enemy slowEnemy;

    private ImageView ivPlayerSprite;
    private boolean isEndScreenDisplayed = false;

    private ImageView healthBoost;
    private ImageView key;

    private MediaPlayer sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        // Fetch the initial data passed in the Intent
        Player player = Player.getInstance();
        playerName = getIntent().getStringExtra("playerName");
        playerHP = getIntent().getIntExtra("playerHP", 100);
        spritePosition = getIntent().getIntExtra("spritePosition", -1);
        difficulty = getIntent().getStringExtra("difficulty");


        player.setPlayerName(playerName);
        player.setPlayerHP(playerHP);
        player.setPlayerScore(playerScore);
        player.setDifficulty(difficulty);
        player.setSpritePosition(spritePosition);

        // Initialize Views
        TextView tvPlayerName = findViewById(R.id.tv_player_name);
        TextView tvPlayerHP = findViewById(R.id.tv_player_hp);
        TextView tvDifficulty = findViewById(R.id.tv_difficulty);
        TextView tvScore = findViewById(R.id.tv_score);
        ivPlayerSprite = findViewById(R.id.iv_player_sprite);


        // Set initial data to Views
        tvPlayerName.setText("Player: " + playerName);
        tvPlayerHP.setText("HP: " + playerHP);
        tvDifficulty.setText("Difficulty: " + difficulty);
        tvScore.setText("Score: " + playerScore);

        // Set sprite based on selection
        switch (spritePosition) { case 0: ivPlayerSprite.setImageResource(R.drawable.sprite1);
            break;
        case 1: ivPlayerSprite.setImageResource(R.drawable.sprite2);
            break;
        case 2: ivPlayerSprite.setImageResource(R.drawable.sprite3);
            break;
        default:
            break;
        }

        healthBoost = findViewById(R.id.health_boost_power_up);
        healthBoost.setImageResource(R.drawable.healthpowerup);


        //Enemy code
        ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1); 
        fastEnemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.FAST, enemySprite);
        player.addObserver(fastEnemy);
        ImageView enemySprite2 = findViewById(R.id.iv_enemy_sprite2);
        slowEnemy = EnemyFactory.createEnemy(EnemyFactory.EnemyType.SLOW, enemySprite2);
        player.addObserver(slowEnemy);


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

            if (Math.abs(player.getX() - fastEnemy.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - fastEnemy.getEnemyLocationY()) < 150) {
                fastEnemy.destroy();
                player.setPlayerScore(player.getPlayerScore() + 50);
            }
            if (Math.abs(player.getX() - slowEnemy.getEnemyLocationX()) < 150
                    && Math.abs(player.getY() - slowEnemy.getEnemyLocationY()) < 150) {
                slowEnemy.destroy();
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

        if (isCollisionWithHealthBoost(newX, newY, healthBoost)) {
            player.activateHealthBoost();
            player.setPlayerHP(50);
            tvhp.setText("HP: " + player.getPlayerHP());
            //applyHealthBoost(player, 50); // Apply a health boost of 50
            healthBoost.setVisibility(View.GONE); // Hide the health boost
            healthBoost.setX(0);
            healthBoost.setY(0);
            player.deactivateBoost();
        }

        tvhp.setText("HP: " + player.getPlayerHP());
        if (newX >= 466 && newX <= 616 && newY >= 417 && newY <= 500 && player.gethasKey()) {

            transitionToNextActivity();
        }
        key = findViewById(R.id.key);
        key.setImageResource(R.drawable.key);

        if (Math.abs(player.getX() - key.getX()) < 150
                && Math.abs(player.getY() - key.getY()) < 150) {
            player.sethasKey();
            key.setVisibility(View.GONE);
        }

    }

    private boolean isCollisionWithHealthBoost(int playerX, int playerY, ImageView healthBoost) {
        // Implement more robust collision detection logic here
        int boostX = (int) healthBoost.getX();
        int boostY = (int) healthBoost.getY();
        return Math.abs(playerX - boostX) < 20 && Math.abs(playerY - boostY) < 20;
    }


    private void applyHealthBoost(Player player, int healthBoostAmount) {
        int newHealth = player.getPlayerHP() + healthBoostAmount;
        player.setPlayerHP(newHealth);
        // Update any other relevant UI or game state here if necessary
    }


    private void transitionToNextActivity() {
        Intent intent = new Intent(GameScreenActivity.this, Room1Activity.class);
        startActivity(intent);
    }

    public void decreaseValue() {
        Player player = Player.getInstance();


        if (player.getPlayerHP() <= 0 && !isEndScreenDisplayed) {
            // Transition to the end screen if the score reaches zero or below
            isEndScreenDisplayed = true;
            Intent endScreenIntent = new Intent(GameScreenActivity.this, EndScreenLoss.class);
            endScreenIntent.putExtra("playerScore", player.getPlayerScore());
            endScreenIntent.putExtra("playerName", player.getPlayerName());
            startActivity(endScreenIntent);
            finish();
        } else {
            player.setPlayerScore(player.getPlayerScore() - 0.1);
            TextView tvScore = findViewById(R.id.tv_score);
            tvScore.setText("Score: " + String.format("%.1f", player.getPlayerScore()));
        }
    }


    private Runnable decreaseRunnable = new Runnable() {
        @Override
        public void run() {
            decreaseValue();
            ImageView enemySprite = findViewById(R.id.iv_enemy_sprite1);
            ImageView enemySprite2 = findViewById(R.id.iv_enemy_sprite2);
            fastEnemy.setEnemyLocationX((int) enemySprite.getX());
            fastEnemy.setEnemyLocationY((int) enemySprite.getY());
            slowEnemy.setEnemyLocationX((int) enemySprite2.getX());
            slowEnemy.setEnemyLocationY((int) enemySprite2.getY());


            // Move the fast enemy, if this is on the UI thread already
            if (fastEnemy != null) {
                fastEnemy.move();
                slowEnemy.move();
            }

            handler.postDelayed(this, 100); // re-post the Runnable for the next execution
        }
    };


    public double getPlayerScore() {
        return playerScore;
    }
    public int getPlayerHP() {
        return playerHP;
    }
    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public ImageView setUpHealthBoost() {
        return healthBoost;
    }

    public View getHealthBoost() {
        return healthBoost;
    }
}