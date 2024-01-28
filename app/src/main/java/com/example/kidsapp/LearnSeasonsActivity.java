package com.example.kidsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LearnSeasonsActivity extends AppCompatActivity {

    private ImageView seasonImage;
    private TextView seasonDescription;
    private Button nextButton;
    private Button restartButton;
    private Button startGameButton;

    private String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
    private int[] seasonImages = {R.drawable.spring_image, R.drawable.summer_image, R.drawable.autumn_image, R.drawable.winter_image};
    private int currentSeasonIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_seasons);

        seasonImage = findViewById(R.id.season_image);
        seasonDescription = findViewById(R.id.season_description);
        nextButton = findViewById(R.id.next_button);
        restartButton = findViewById(R.id.restart_button);
        startGameButton = findViewById(R.id.start_game_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextSeason();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSeason();
            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        showSeason(currentSeasonIndex);
    }

    private void showSeason(int index) {
        seasonImage.setImageResource(seasonImages[index]);
        seasonDescription.setText(getSeasonDescription(index));
    }

    private void showNextSeason() {
        currentSeasonIndex = (currentSeasonIndex + 1) % seasons.length;
        showSeason(currentSeasonIndex);

        if (currentSeasonIndex == seasons.length - 1) {
            restartButton.setVisibility(View.INVISIBLE); // gözükmesini istiyorsan visible yap
            startGameButton.setVisibility(View.VISIBLE);
        } else {
            restartButton.setVisibility(View.INVISIBLE);
            startGameButton.setVisibility(View.INVISIBLE);
        }
    }


    private void resetSeason() {
        currentSeasonIndex = 0;
        showSeason(currentSeasonIndex);
    }

    private void startGame() {
        Intent intent = new Intent(LearnSeasonsActivity.this, Game2Activity.class);
        startActivity(intent);
    }

    private String getSeasonDescription(int index) {
        switch (index) {
            case 0:
                return "Spring is the season of growth and renewal, when flowers bloom and trees become green.";
            case 1:
                return "Summer is the warmest season of the year, characterized by longer days and outdoor activities.";
            case 2:
                return "Autumn, also known as fall, is the season of harvest and the changing colors of leaves.";
            case 3:
                return "Winter is the coldest season of the year, with snowfall and holiday celebrations.";
            default:
                return "";
        }
    }
}
