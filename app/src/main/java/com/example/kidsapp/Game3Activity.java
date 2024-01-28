package com.example.kidsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game3Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView questionTextView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;
    private Button option5Button;
    private Button option6Button;

    private List<String> daysOfWeek;
    private List<String> shuffledDays;
    private String correctAnswer;

    private int questionIndex;
    private boolean answerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        questionTextView = findViewById(R.id.question_text_view);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        option5Button = findViewById(R.id.option5_button);
        option6Button = findViewById(R.id.option6_button);

        option1Button.setOnClickListener(this);
        option2Button.setOnClickListener(this);
        option3Button.setOnClickListener(this);
        option4Button.setOnClickListener(this);
        option5Button.setOnClickListener(this);
        option6Button.setOnClickListener(this);

        // Initialize the list of days of the week
        daysOfWeek = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

        // Shuffle the days of the week for each game session
        shuffledDays = new ArrayList<>(daysOfWeek);
        Collections.shuffle(shuffledDays);

        questionIndex = 0;
        answerSelected = false;
        showQuestion();
    }

    @Override
    public void onClick(View view) {
        if (!answerSelected) {
            Button clickedButton = (Button) view;
            String selectedAnswer = clickedButton.getText().toString();

            if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                answerSelected = true;
            } else {
                Toast.makeText(this, "Wrong answer! Try again.", Toast.LENGTH_SHORT).show();
                return; // Skip moving to the next question until the correct answer is chosen
            }
        }

        // Move to the next question
        questionIndex++;
        answerSelected = false;
        if (questionIndex < shuffledDays.size()) {
            showQuestion();
        } else {
            // Quiz completed, show a message or perform any necessary actions
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();
        }
    }



    private void showQuestion() {
        // Get the current day of the week and its correct position
        String currentDay = shuffledDays.get(questionIndex);
        int correctIndex = (daysOfWeek.indexOf(currentDay) + 1) % 7; // Calculate the correct index of the next day

        // Set the question text
        String question = getQuestionForDay(currentDay);
        questionTextView.setText(question);

        // Create a list of options excluding the current day
        List<String> options = new ArrayList<>(daysOfWeek);
        options.remove(currentDay);

        // Shuffle the options
        Collections.shuffle(options);

        // Add the correct answer at a random position
        int randomIndex = (int) (Math.random() * (options.size() + 1));
        options.add(randomIndex, daysOfWeek.get(correctIndex));

        // Set the options on the buttons
        option1Button.setText(options.get(0));
        option2Button.setText(options.get(1));
        option3Button.setText(options.get(2));
        option4Button.setText(options.get(3));
        option5Button.setText(options.get(4));
        option6Button.setText(options.get(5));

        // Set the correct answer
        correctAnswer = daysOfWeek.get(correctIndex);
    }






    private String getQuestionForDay(String day) {
        if (day.equalsIgnoreCase("Sunday")) {
            return "Which day comes after Sunday?";
        } else if (day.equalsIgnoreCase("Monday")) {
            return "Which day comes after Monday?";
        } else if (day.equalsIgnoreCase("Tuesday")) {
            return "Which day comes after Tuesday?";
        } else if (day.equalsIgnoreCase("Wednesday")) {
            return "Which day comes after Wednesday?";
        } else if (day.equalsIgnoreCase("Thursday")) {
            return "Which day comes after Thursday?";
        } else if (day.equalsIgnoreCase("Friday")) {
            return "Which day comes after Friday?";
        } else if (day.equalsIgnoreCase("Saturday")) {
            return "Which day comes after Saturday?";
        }
        return "";
    }
}
