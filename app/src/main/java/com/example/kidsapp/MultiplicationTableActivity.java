package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MultiplicationTableActivity extends AppCompatActivity {
    private static final int TABLE_SIZE = 9;

    private TextView tableTextView;
    private int currentNumber = 1;
    private static final int MAX_NUMBER = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_table);

        tableTextView = findViewById(R.id.tableTextView);
        generateMultiplicationTable();
    }

    private void generateMultiplicationTable() {
        StringBuilder tableBuilder = new StringBuilder();

        for (int i = 1; i <= TABLE_SIZE; i++) {
            int result = currentNumber * i;
            tableBuilder.append(currentNumber).append(" x ").append(i).append(" = ").append(result);
            tableBuilder.append("\n");
        }

        tableTextView.setText(tableBuilder.toString());
    }

    public void onNextButtonClicked(View view) {
        currentNumber++;

        if (currentNumber > MAX_NUMBER) {
            goToQuizActivity();
        } else {
            generateMultiplicationTable();
        }
    }

    private void goToQuizActivity() {
        Intent intent = new Intent(this, Game5Activity.class);
        startActivity(intent);
        finish();
    }
}
