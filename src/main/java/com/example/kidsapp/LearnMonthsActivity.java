package com.example.kidsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LearnMonthsActivity extends AppCompatActivity {

    private ListView monthsListView;
    private ImageView monthImageView;
    private TextView monthInfoTextView;

    private String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    private String[] monthInfo = {
            "January is the first month of the year.",
            "February is the second month of the year.",
            "March is the third month of the year.",
            "April is the fourth month of the year.",
            "May is the fifth month of the year.",
            "June is the sixth month of the year.",
            "July is the seventh month of the year.",
            "August is the eighth month of the year.",
            "September is the ninth month of the year.",
            "October is the tenth month of the year.",
            "November is the eleventh month of the year.",
            "December is the twelfth month of the year."
    };

    private int[] monthImages = {
            R.drawable.january, R.drawable.february, R.drawable.march,
            R.drawable.april, R.drawable.may, R.drawable.june,
            R.drawable.july, R.drawable.august, R.drawable.september,
            R.drawable.october, R.drawable.november, R.drawable.december
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_months);

        monthsListView = findViewById(R.id.monthsListView);
        monthImageView = findViewById(R.id.monthImageView);
        monthInfoTextView = findViewById(R.id.monthInfoTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, months);
        monthsListView.setAdapter(adapter);

        monthsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthImageView.setImageResource(monthImages[position]);
                monthInfoTextView.setText(monthInfo[position]);
            }
        });

        Button playGameButton = findViewById(R.id.playGameButton);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity();
            }
        });
    }

    private void startGameActivity() {
        Intent intent = new Intent(this, Game4Activity.class);
        intent.putExtra("months", months);
        startActivity(intent);
    }
}

