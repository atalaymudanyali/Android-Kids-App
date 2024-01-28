package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsapp.AnalogClockView;

import java.util.Locale;
import java.util.Random;

public class LearnClockActivity extends AppCompatActivity {

    private AnalogClockView analogClock;
    private TextView instructionTextView;
    private Button nextButton;
    private Button restartButton;
    private Button startGameButton;

    private Random random;

    private String[] instructionTexts;
    private int currentInstructionIndex;

    private boolean isTeachingPhase; // Flag to indicate whether it's the teaching phase or game phase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_clock);

        analogClock = findViewById(R.id.analogClock);
        instructionTextView = findViewById(R.id.instructionTextView);
        nextButton = findViewById(R.id.nextButton);
        restartButton = findViewById(R.id.restartButton);
        startGameButton = findViewById(R.id.startGameButton);

        random = new Random();

        instructionTexts = getResources().getStringArray(R.array.clock_instructions);
        currentInstructionIndex = -1;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextInstruction();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartLesson();
            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        isTeachingPhase = true; // Set the initial phase to teaching
        showNumbersOnClock(true); // Show numbers on the analog clock

        nextInstruction();
    }

    private void nextInstruction() {
        currentInstructionIndex++;
        if (currentInstructionIndex < instructionTexts.length) {
            String instruction = instructionTexts[currentInstructionIndex];
            instructionTextView.setText(instruction);
            resetClock();
        } else {
            instructionTextView.setText("Lesson completed");
            nextButton.setVisibility(View.GONE);
            restartButton.setVisibility(View.VISIBLE);
            startGameButton.setVisibility(View.VISIBLE);
        }
    }

    private void resetClock() {
        int targetHour = random.nextInt(12) + 1;
        int targetMinute = random.nextInt(4) * 15;
        analogClock.setTime(targetHour, targetMinute);

        String timeString = String.format(Locale.getDefault(), "%02d:%02d", targetHour, targetMinute);
        instructionTextView.setText("Read the hour hand\n\nTime: " + timeString);
    }

    private void showNumbersOnClock(boolean showNumbers) {
        analogClock.setShowNumbers(showNumbers);
    }

    private void restartLesson() {
        currentInstructionIndex = -1;
        nextInstruction();
        nextButton.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.GONE);
        startGameButton.setVisibility(View.GONE);
    }

    private void startGame() {
        isTeachingPhase = false; // Set the phase to game
        showNumbersOnClock(false); // Hide numbers on the analog clock

        // Start the game activity
        Intent intent = new Intent(this, Game1Activity.class);
        startActivity(intent);
    }
}

