package com.example.myapplication.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.Model.ScoreEntry;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<ScoreEntry> scoreEntries;

    public ScoreAdapter(List<ScoreEntry> scoreEntries) {
        this.scoreEntries = scoreEntries;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreEntry entry = scoreEntries.get(position);
        holder.playerName.setText(entry.getPlayerName());
        holder.playerScore.setText(String.valueOf(entry.getScore()));
    }

    @Override
    public int getItemCount() {
        return scoreEntries.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView playerScore;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            playerScore = itemView.findViewById(R.id.playerScore);
        }
    }
}
