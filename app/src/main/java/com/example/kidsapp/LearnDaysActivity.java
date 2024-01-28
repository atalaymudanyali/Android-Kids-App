package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class LearnDaysActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView dayImageView;
    private TextView dayNameTextView;
    private TextView infoTextView;
    private Button startGameButton;
    private Button prevButton;
    private Button nextButton;

    private String[] days;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_days);

        dayImageView = findViewById(R.id.day_image_view);
        dayNameTextView = findViewById(R.id.day_name_text_view);
        infoTextView = findViewById(R.id.day_info_text_view);
        startGameButton = findViewById(R.id.start_game_button);
        prevButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);

        startGameButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        // Initialize the days array
        days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        currentIndex = 0;
        showCurrentDay();
    }

    @Override
    public void onClick(View v) {
        if (v == startGameButton) {
            // Start the Game3Activity
            startActivity(new Intent(LearnDaysActivity.this, Game3Activity.class));
        } else if (v == prevButton) {
            showPreviousDay();
        } else if (v == nextButton) {
            showNextDay();
        }
    }

    private void showCurrentDay() {
        String day = days[currentIndex];
        int imageResId = getResources().getIdentifier(day.toLowerCase(), "drawable", getPackageName());

        dayImageView.setImageResource(imageResId);
        dayNameTextView.setText(day);
        infoTextView.setText(getDayInfo(day));

        // Show the Start Game button at the last day
        if (currentIndex == days.length - 1) {
            startGameButton.setVisibility(View.VISIBLE);
        } else {
            startGameButton.setVisibility(View.GONE);
        }
    }

    private void showPreviousDay() {
        if (currentIndex > 0) {
            currentIndex--;
            showCurrentDay();
        } else {
            Toast.makeText(this, "Already at the first day", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNextDay() {
        if (currentIndex < days.length - 1) {
            currentIndex++;
            showCurrentDay();
        } else {
            Toast.makeText(this, "Already at the last day", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDayInfo(String day) {
        // Replace this with your own logic to retrieve the information for each day
        if (day.equalsIgnoreCase("Sunday")) {
            return "Sunday is the day of the week between Saturday and Monday. It is the first day of the week.";
        } else if (day.equalsIgnoreCase("Monday")) {
            return "Monday is the day of the week between Sunday and Tuesday. It is the second day of the week.";
        } else if (day.equalsIgnoreCase("Tuesday")) {
            return "Tuesday is the day of the week between Monday and Wednesday. It is the third day of the week.";
        } else if (day.equalsIgnoreCase("Wednesday")) {
            return "Wednesday is the day of the week between Tuesday and Thursday. It is the fourth day of the week.";
        } else if (day.equalsIgnoreCase("Thursday")) {
            return "Thursday is the day of the week between Wednesday and Friday. It is the fifth day of the week.";
        } else if (day.equalsIgnoreCase("Friday")) {
            return "Friday is the day of the week between Thursday and Saturday. It is the sixth day of the week.";
        } else if (day.equalsIgnoreCase("Saturday")) {
            return "Saturday is the day of the week between Friday and Sunday. It is the seventh day of the week.";
        }
        return "";
    }
}
