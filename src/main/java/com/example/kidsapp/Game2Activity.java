package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game2Activity extends AppCompatActivity {

    private ImageView seasonImage;
    private TextView seasonText;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;

    private String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
    private int[] seasonImages = {R.drawable.spring_image, R.drawable.summer_image, R.drawable.autumn_image, R.drawable.winter_image};
    private int currentSeasonIndex = 0;

    private List<String> options;
    private String correctOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        seasonImage = findViewById(R.id.season_image);
        seasonText = findViewById(R.id.season_text);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option3Button.getText().toString());
            }
        });

        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option4Button.getText().toString());
            }
        });

        updateSeason();
    }

    private void updateSeason() {
        currentSeasonIndex = (currentSeasonIndex + 1) % seasons.length;
        seasonImage.setImageResource(seasonImages[currentSeasonIndex]);
        seasonText.setText(seasons[currentSeasonIndex]);

        options = generateOptions();
        correctOption = seasons[currentSeasonIndex];

        Collections.shuffle(options);

        option1Button.setText(options.get(0));
        option2Button.setText(options.get(1));
        option3Button.setText(options.get(2));
        option4Button.setText(options.get(3));
    }

    private List<String> generateOptions() {
        List<String> options = new ArrayList<>();
        options.add(seasons[currentSeasonIndex]);

        while (options.size() < 4) {
            String randomSeason = seasons[(int) (Math.random() * seasons.length)];
            if (!options.contains(randomSeason)) {
                options.add(randomSeason);
            }
        }

        return options;
    }

    private void checkAnswer(String selectedOption) {
        if (selectedOption.equals(correctOption)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            updateSeason();
        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
