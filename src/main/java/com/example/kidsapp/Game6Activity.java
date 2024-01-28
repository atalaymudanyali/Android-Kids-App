package com.example.kidsapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game6Activity extends AppCompatActivity {
    private TextView textViewDigits;
    private TextView textViewFeedback;
    private Button[] buttons;
    private List<Integer> appSequence;
    private StringBuilder userSequence;
    private int sequenceLength;
    private boolean isReverseSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);

        textViewDigits = findViewById(R.id.textViewDigits);
        textViewFeedback = findViewById(R.id.textViewFeedback);

        buttons = new Button[10];
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);

        sequenceLength = 6;
        isReverseSequence = false;
        appSequence = new ArrayList<>();
        userSequence = new StringBuilder();

        for (int i = 0; i < buttons.length; i++) {
            final int digit = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isReverseSequence) {
                        userSequence.insert(0, digit);
                    } else {
                        userSequence.append(digit);
                    }
                    textViewDigits.setText(userSequence.toString());
                    checkSequence();
                }
            });
        }

        generateAppSequence();
        showAppSequence();
    }

    private void generateAppSequence() {
        appSequence.clear();
        Random random = new Random();
        for (int i = 0; i < sequenceLength; i++) {
            int digit = random.nextInt(10);
            appSequence.add(digit);
        }
    }

    private void showAppSequence() {
        textViewDigits.setText(R.string.watch_closely);
        isReverseSequence = false;
        userSequence.setLength(0);

        // Randomly decide if the sequence should be shown in reverse order
        Random random = new Random();
        isReverseSequence = random.nextBoolean();

        Handler handler = new Handler();
        for (int i = 0; i < appSequence.size(); i++) {
            final int digit = appSequence.get(i);
            final int delay = (i + 1) * 1000;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewDigits.setText("");
                    Handler innerHandler = new Handler();
                    innerHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textViewDigits.setText(String.valueOf(digit));
                        }
                    }, 500);
                }
            }, delay);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isReverseSequence) {
                    textViewDigits.setText(R.string.write_sequence_reverse);
                } else {
                    textViewDigits.setText(R.string.your_turn);
                }
            }
        }, (appSequence.size() + 1) * 1000);
    }



    private void checkSequence() {
        if (userSequence.length() == sequenceLength) {
            boolean isCorrect = true;
            for (int i = 0; i < sequenceLength; i++) {
                int userDigit = Character.getNumericValue(userSequence.charAt(i));
                int appDigit = appSequence.get(i);
                if (userDigit != appDigit) {
                    isCorrect = false;
                    break;
                }
            }

            if (isCorrect) {
                textViewFeedback.setText("Correct! Starting next sequence...");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        generateAppSequence();
                        showAppSequence();
                        textViewFeedback.setText("");
                    }
                }, 2000);
            } else {
                textViewFeedback.setText("Incorrect! Try again...");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showAppSequence();
                        textViewFeedback.setText("");
                    }
                }, 2000);
            }
        }
    }
}
