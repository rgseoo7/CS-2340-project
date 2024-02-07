package com.example.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.Model.Leaderboard;
import com.example.myapplication.Model.ScoreEntry;
import java.util.List;
import android.widget.TextView;


public class EndScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        // winning sound effect
        MediaPlayer winSound = MediaPlayer.create(this, R.raw.win);

        // Play the sound effect
        if (winSound != null) {
            winSound.start();
        }

        //New code
        Player player = Player.getInstance();
        Double playerScore = (double) ((int) player.getPlayerScore());
        String playerName = player.getPlayerName();

        
        ScoreEntry newEntry = new ScoreEntry(playerName, playerScore);
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addScore(newEntry);

        Button btnRestart = findViewById(R.id.btn_start_over);
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(EndScreen.this, InitialConfigActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerLeaderboard);
        List<ScoreEntry> topScores = leaderboard.getTopScores(5);
        ScoreAdapter scoreAdapter = new ScoreAdapter(topScores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scoreAdapter);

        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(v -> {
            leaderboard.clearScores();
            recyclerView.setVisibility(View.INVISIBLE);
        });

        TextView tvMostRecent = findViewById(R.id.mostRecentScoreText);
        tvMostRecent.setText("Most Recent Score: "
                + Player.getInstance().getPlayerName() + ", "
                + (int) Player.getInstance().getPlayerScore());
    }
}
