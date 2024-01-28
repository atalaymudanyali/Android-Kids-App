package com.example.kidsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsapp.AnalogClockView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game1Activity extends AppCompatActivity {
    private AnalogClockView analogClock;
    private Button optionButton1, optionButton2, optionButton3, optionButton4;
    private TextView resultTextView;

    private int targetHour, targetMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        analogClock = findViewById(R.id.analogClock);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);
        resultTextView = findViewById(R.id.resultTextView);

        generateRandomTime();
        generateOptions();

        optionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButton1.getText().toString());
            }
        });

        optionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButton2.getText().toString());
            }
        });

        optionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButton3.getText().toString());
            }
        });

        optionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionButton4.getText().toString());
            }
        });
    }

    private void generateRandomTime() {
        Random random = new Random();
        targetHour = random.nextInt(12) + 1;
        targetMinute = random.nextInt(4) * 15;
        analogClock.setTime(targetHour, targetMinute);
    }

    private void generateOptions() {
        Random random = new Random();
        List<String> timeOptions = new ArrayList<>();

        // Add the target time as the correct option
        timeOptions.add(getTargetTime());

        // Generate random times for the remaining options
        while (timeOptions.size() < 4) {
            int randomHour = random.nextInt(12) + 1;
            int randomMinute = random.nextInt(4) * 15;
            String randomTime = formatTime(randomHour, randomMinute);

            // Check if the random time is already present in the options
            if (!timeOptions.contains(randomTime)) {
                timeOptions.add(randomTime);
            }
        }

        // Shuffle the time options
        Collections.shuffle(timeOptions);

        // Set the options on the buttons
        optionButton1.setText(timeOptions.get(0));
        optionButton2.setText(timeOptions.get(1));
        optionButton3.setText(timeOptions.get(2));
        optionButton4.setText(timeOptions.get(3));
    }

    private String getTargetTime() {
        // Format the target hour and minute values into the desired time format
        String formattedHour = String.format("%02d", targetHour);
        String formattedMinute = String.format("%02d", targetMinute);
        return formattedHour + ":" + formattedMinute;
    }

    private String formatTime(int hour, int minute) {
        // Format the hour and minute values into the desired time format
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);
        return formattedHour + ":" + formattedMinute;
    }

    private void checkAnswer(String selectedOption) {
        String correctOption = getTargetTime();

        if (selectedOption.equals(correctOption)) {
            resultTextView.setText("Correct!");

            // Generate a new random time and new options
            generateRandomTime();
            generateOptions();
        } else {
            resultTextView.setText("Try again!");
        }
    }
}
