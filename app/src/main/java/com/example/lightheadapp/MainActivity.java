package com.example.lightheadapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private RelativeLayout bg;
    private int currentColor = Color.RED;
    private Handler handler;
    private Runnable colorChangeRunnable;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg = findViewById(R.id.main_act);
        bg.setBackgroundColor(currentColor);
        buttonClicked();
        handler = new Handler();
        colorChangeRunnable = new Runnable() {
            @Override
            public void run() {
                changeColorAutomatically();
                handler.postDelayed(this, 3000); // Repeat every 3 seconds
            }
        };
    }

    public void buttonClicked() {
        button = findViewById(R.id.change);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isRunning) {
            // If it's in the "Pause" state, change it to "Start" and stop automatic color changes
            isRunning = false;
            button.setText("Start");
            handler.removeCallbacks(colorChangeRunnable);
        } else {
            // If it's in the "Start" state, change it to "Pause" and start automatic color changes
            isRunning = true;
            button.setText("Pause");
            handler.postDelayed(colorChangeRunnable, 3000); // Initial delay of 3 seconds
        }
    }

    // Method to change the color automatically
    private void changeColorAutomatically() {
        if (currentColor == Color.RED) {
            currentColor = Color.YELLOW;
        } else if (currentColor == Color.YELLOW) {
            currentColor = Color.GREEN;
        } else {
            currentColor = Color.RED;
        }
        bg.setBackgroundColor(currentColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback to prevent memory leaks
        handler.removeCallbacks(colorChangeRunnable);
    }
}
