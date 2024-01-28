package com.example.kidsapp;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class Game7Activity extends Activity {

    private BallView ballView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the size of the screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        // Create a BallView with the screen size
        ballView = new BallView(this, screenWidth, screenHeight);
        setContentView(ballView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ballView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ballView.pause();
    }
}
