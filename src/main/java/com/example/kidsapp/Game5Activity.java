package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game5Activity extends AppCompatActivity {
    private static final int MAX_NUMBER = 10;
    private static final int OPTION_COUNT = 4;

    private TextView questionTextView;
    private List<Integer> answerOptions;
    private int correctAnswer;
    private int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);

        questionTextView = findViewById(R.id.questionTextView);

        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(MAX_NUMBER) + 1;
        int num2 = random.nextInt(MAX_NUMBER) + 1;
        correctAnswer = num1 * num2;

        String questionText = "What is " + num1 + " multiplied by " + num2 + "?";
        questionTextView.setText(questionText);

        generateAnswerOptions();
    }

    private void generateAnswerOptions() {
        answerOptions = new ArrayList<>();
        answerOptions.add(correctAnswer);

        Random random = new Random();
        while (answerOptions.size() < OPTION_COUNT) {
            int option = random.nextInt(MAX_NUMBER * MAX_NUMBER) + 1;
            if (!answerOptions.contains(option)) {
                answerOptions.add(option);
            }
        }

        Collections.shuffle(answerOptions);

        setAnswerButtonOptions();
    }

    private void setAnswerButtonOptions() {
        List<Button> answerButtons = new ArrayList<>();
        answerButtons.add(findViewById(R.id.answerButton1));
        answerButtons.add(findViewById(R.id.answerButton2));
        answerButtons.add(findViewById(R.id.answerButton3));
        answerButtons.add(findViewById(R.id.answerButton4));

        for (int i = 0; i < answerButtons.size(); i++) {
            Button button = answerButtons.get(i);
            int option = answerOptions.get(i);
            button.setText(String.valueOf(option));
        }
    }

    public void submitAnswer(View view) {
        Button selectedButton = (Button) view;
        int selectedOption = Integer.parseInt(selectedButton.getText().toString());

        if (selectedOption == correctAnswer) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            generateQuestion();
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
    }





    public void showMultiplicationTable(View view) {
        Intent intent = new Intent(this, MultiplicationTableActivity.class);
        startActivity(intent);
    }
}
