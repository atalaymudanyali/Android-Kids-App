package com.example.kidsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isRunning;
    private int screenWidth;
    private int screenHeight;
    private float x, y;
    private float speedX, speedY;
    private float radius;
    private Paint paint;
    private boolean movingRight;
    private boolean movingDown;

    private boolean replayRequested;

    public BallView(Context context, int screenWidth, int screenHeight) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Set initial position and speed of the ball
        x = radius;
        y = radius;
        speedX = 10;
        speedY = 10;

        radius = 50; // Adjust the size of the ball here

        paint = new Paint();
        paint.setColor(Color.RED);

        movingRight = true;
        movingDown = true;

        replayRequested = false;

        setFocusable(true);
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!getHolder().getSurface().isValid()) {
                continue;
            }

            if (replayRequested) {
                resetBall();
                replayRequested = false;
            }

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.WHITE);

            // Update the position of the ball
            if (movingRight) {
                x += speedX;
                if (x > screenWidth - radius) {
                    x = screenWidth - radius;
                    movingRight = false;
                    y += 2 * radius;
                }
            } else {
                x -= speedX;
                if (x < radius) {
                    x = radius;
                    movingRight = true;
                    y += 2 * radius;
                }
            }

            if (y > screenHeight - radius) {
                // Stop the ball's movement when it reaches the bottom of the screen
                pause();
            }

            // Draw the ball on the canvas
            canvas.drawCircle(x, y, radius, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void resume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void replay() {
        replayRequested = true;
        resume();
    }

    private void resetBall() {
        x = radius;
        y = radius;
        movingRight = true;
        movingDown = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            replay();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
