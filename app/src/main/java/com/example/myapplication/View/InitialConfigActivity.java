package com.example.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class InitialConfigActivity extends AppCompatActivity {

    private int playerHP;
    private RadioGroup difficultyRadioGroup;
    private static EditText playerNameInput;
    private static Spinner characterSpriteSelector;
    private static ImageView previewSprite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_initial_config);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        EditText playerNameInput = findViewById(R.id.player_name_input);
        RadioGroup difficultyRadioGroup = findViewById(R.id.difficulty_radio_group);
        Spinner characterSpriteSelector = findViewById(R.id.character_sprite_selector);
        Button btnContinue = findViewById(R.id.btn_continue);
        TextView previewName = findViewById(R.id.preview_name);
        ImageView previewSprite = findViewById(R.id.preview_sprite);

        // gets sprite choices string and sets spinner text white
        String[] spriteOptions = getResources().getStringArray(R.array.sprite_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, spriteOptions);
        characterSpriteSelector.setAdapter(adapter);

        // Handling difficulty selection
        difficultyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedButton = findViewById(checkedId);
            switch (selectedButton.getText().toString()) {
            case "Easy":
                playerHP = 200;
                break;
            case "Medium":
                playerHP = 150;
                break;
            case "Hard":
                playerHP = 100;
                break;
            default:
                //nothing
            }
        });

        // Handling sprite selection
        characterSpriteSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("switch", "switch activated");
                ImageView sprite1 = (ImageView) findViewById(R.id.preview_sprite);
                ImageView sprite2 = (ImageView) findViewById(R.id.preview_sprite2);
                ImageView sprite3 = (ImageView) findViewById(R.id.preview_sprite3);
                switch (position) {
                    case 0:
                        sprite1.setVisibility(View.VISIBLE);
                        sprite2.setVisibility(View.INVISIBLE);
                        sprite3.setVisibility(View.INVISIBLE);
                        //previewSprite.setImageResource(R.drawable.sprite1);
                        break;
                    case 1:
                        sprite1.setVisibility(View.INVISIBLE);
                        sprite2.setVisibility(View.VISIBLE);
                        sprite3.setVisibility(View.INVISIBLE);
                        //previewSprite.setImageResource(R.drawable.sprite2);
                        Log.d("Case 1", "sprite2");
                        break;
                    case 2:
                        sprite1.setVisibility(View.INVISIBLE);
                        sprite2.setVisibility(View.INVISIBLE);
                        sprite3.setVisibility(View.VISIBLE);
                        //previewSprite.setImageResource(R.drawable.sprite3);
                        break;
                    default:
                        //skip
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing here
            }
        });

        // Handling the Continue button click event
        btnContinue.setOnClickListener(v -> {
            String playerName = playerNameInput.getText().toString().trim();
            if (playerName.isEmpty()) {
                Toast.makeText(InitialConfigActivity.this,
                        "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(InitialConfigActivity.this, GameScreenActivity.class);

            intent.putExtra("playerName", playerName);
            intent.putExtra("playerHP", playerHP);

            int selectedSpritePosition = characterSpriteSelector.getSelectedItemPosition();
            intent.putExtra("spritePosition", selectedSpritePosition);

            int selectedDifficultyId = difficultyRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedButton = findViewById(selectedDifficultyId);
            intent.putExtra("difficulty", selectedButton.getText().toString());

            startActivity(intent);
            previewName.setText("Preview Name: " + playerName);
        });

        playerNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Pass
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                previewName.setText("Preview Name: " + s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Pass
            }
        });
        
    }

    public static EditText getPlayerNameInput() {
        return playerNameInput;
    }
    public static void getPlayerNameInput(String whitespaceName) {
    }
    public static boolean isPlayerNameValid() {
        return false;
    }


}
