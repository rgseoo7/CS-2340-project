package com.example.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.R;
import com.example.myapplication.Model.Leaderboard;
import com.example.myapplication.Model.ScoreEntry;
import java.util.List;
import android.widget.TextView;
public class EndScreenLoss extends Activity {
    @Override
    //this should produce the most recent score and not repeat it twice.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen_loss);
        Double playerScore = getIntent().getDoubleExtra("playerScore", 0);
        String playerName = getIntent().getStringExtra("playerName");

        // losing sound effect
        MediaPlayer lossSound = MediaPlayer.create(this, R.raw.lose);

        // Play the sound effect
        if (lossSound != null) {
            lossSound.start();
        }

        ScoreEntry newEntry = new ScoreEntry(playerName, playerScore);
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addScore(newEntry);

        Button btnRestart2 = findViewById(R.id.btn_start_over_2);
        btnRestart2.setOnClickListener(v -> {
            Intent intent = new Intent(EndScreenLoss.this, InitialConfigActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerLeaderboard2);
        List<ScoreEntry> topScores = leaderboard.getTopScores(5);
        ScoreAdapter scoreAdapter = new ScoreAdapter(topScores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scoreAdapter);

        Button btnReset2 = findViewById(R.id.btn_reset2);
        btnReset2.setOnClickListener(v -> {
            leaderboard.clearScores();
            recyclerView.setVisibility(View.INVISIBLE);
        });

        TextView tvMostRecent = findViewById(R.id.mostRecentScoreText2);
        tvMostRecent.setText("Most Recent Score: "
                + getIntent().getStringExtra("playerName") + ", "
                + getIntent().getDoubleExtra("playerScore", 500));
    }
}
