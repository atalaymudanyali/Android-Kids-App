package com.example.kidsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class AnalogClockView extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private Paint textPaint;
    private PointF center;
    private float radius;

    private int hour;
    private int minute;
    private boolean showNumbers;

    private static final int DEFAULT_PADDING = 20;

    public AnalogClockView(Context context) {
        super(context);
        init();
    }

    public AnalogClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnalogClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);
        linePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        center = new PointF();
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        invalidate();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setShowNumbers(boolean showNumbers) {
        this.showNumbers = showNumbers;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = w / 2f;
        center.y = h / 2f;
        radius = Math.min(w, h) / 2f - DEFAULT_PADDING;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw clock circle
        canvas.drawCircle(center.x, center.y, radius, circlePaint);

        // Draw outer line
        canvas.drawCircle(center.x, center.y, radius - linePaint.getStrokeWidth() / 2, linePaint);

        // Draw hour hand
        float hourAngle = (float) ((hour % 12 + minute / 60.0) * 30 * Math.PI / 180);
        float hourHandLength = radius * 0.5f;
        float hourX = (float) (center.x + Math.sin(hourAngle) * hourHandLength);
        float hourY = (float) (center.y - Math.cos(hourAngle) * hourHandLength);
        canvas.drawLine(center.x, center.y, hourX, hourY, linePaint);

        // Draw minute hand
        float minuteAngle = (float) (minute * 6 * Math.PI / 180);
        float minuteHandLength = radius * 0.8f;
        float minuteX = (float) (center.x + Math.sin(minuteAngle) * minuteHandLength);
        float minuteY = (float) (center.y - Math.cos(minuteAngle) * minuteHandLength);
        canvas.drawLine(center.x, center.y, minuteX, minuteY, linePaint);

        // Draw clock text
        if (showNumbers) {
            float numberRadius = radius - DEFAULT_PADDING;
            float numberAngle = (float) (Math.PI / 6); // Angle between numbers (30 degrees)
            float textOffset = textPaint.getTextSize() / 3; // Offset to center the text vertically

            for (int i = 1; i <= 12; i++) {
                float numberX = (float) (center.x + Math.sin(i * numberAngle) * numberRadius);
                float numberY = (float) (center.y - Math.cos(i * numberAngle) * numberRadius);
                canvas.drawText(String.valueOf(i), numberX, numberY + textOffset, textPaint);
            }
        }
    }
}
