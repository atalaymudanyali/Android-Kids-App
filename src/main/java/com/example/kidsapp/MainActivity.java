package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Intent intent = null;
        String gameName = "";
        switch (view.getId()) {
            case R.id.image_game1:
                intent = new Intent(this, LearnClockActivity.class);
                gameName = "Game 1";
                break;
            case R.id.image_game2:
                intent = new Intent(this, LearnSeasonsActivity.class);
                gameName = "Game 2";
                break;
            case R.id.image_game3:
                intent = new Intent(this, LearnDaysActivity.class);
                gameName = "Game 3";
                break;
            case R.id.image_game4:
                intent = new Intent(this, LearnMonthsActivity.class);
                gameName = "Game 4";
                break;
            case R.id.image_game5:
                intent = new Intent(this, MultiplicationTableActivity.class);
                gameName = "Game 5";
                break;
            case R.id.image_game6:
                intent = new Intent(this, Game6Activity.class);
                gameName = "Game 6";
                break;
            case R.id.image_game7:
                intent = new Intent(this, Game7Activity.class);
                gameName = "Game 7";
                break;
            case R.id.image_game8:
                intent = new Intent(this, HomeActivity.class);
                gameName = "Game 8";
                break;
        }
        startActivity(intent);

        if (!gameName.isEmpty()) {
            String message = gameName + " started";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}

