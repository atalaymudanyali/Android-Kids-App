package com.example.kidsapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game4Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private Button[] answerButtons;
    private String[] months;
    private int correctAnswerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        questionTextView = findViewById(R.id.questionTextView);
        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.answerButton1);
        answerButtons[1] = findViewById(R.id.answerButton2);
        answerButtons[2] = findViewById(R.id.answerButton3);
        answerButtons[3] = findViewById(R.id.answerButton4);

        // Retrieve the month data from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            months = extras.getStringArray("months");
        }

        // Set click listeners for answer buttons
        for (Button button : answerButtons) {
            button.setOnClickListener(this);
        }

        // Start the quiz game
        startGame();
    }

    private void startGame() {
        generateQuestion();
        setAnswerOptions();
    }

    private void generateQuestion() {
        Random random = new Random();
        correctAnswerIndex = random.nextInt(months.length);

        // Display the question
        String question = "What is the order of the month " + months[correctAnswerIndex] + "?";
        questionTextView.setText(question);
    }

    private void setAnswerOptions() {
        List<Integer> answerIndices = generateUniqueRandomIndices(correctAnswerIndex, months.length, answerButtons.length);

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(String.valueOf(answerIndices.get(i) + 1));
        }
    }

    private List<Integer> generateUniqueRandomIndices(int correctIndex, int maxRange, int count) {
        List<Integer> indices = Arrays.asList(new Integer[count]);
        Collections.fill(indices, -1);
        indices.set(0, correctIndex);

        Random random = new Random();
        for (int i = 1; i < count; i++) {
            int index = random.nextInt(maxRange);
            while (indices.contains(index)) {
                index = random.nextInt(maxRange);
            }
            indices.set(i, index);
        }

        Collections.shuffle(indices);
        return indices;
    }

    @Override
    public void onClick(View v) {
        Button selectedButton = (Button) v;
        String selectedAnswer = selectedButton.getText().toString();
        String correctAnswer = String.valueOf(correctAnswerIndex + 1);

        String message;
        if (selectedAnswer.equals(correctAnswer)) {
            message = "Correct!";
        } else {
            message = "Wrong answer!";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Wait for a short duration before moving to the next question
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        }, 1000); // Delay in milliseconds (1 second)
    }
}
