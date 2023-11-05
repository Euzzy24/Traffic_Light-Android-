package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton red, yellow, green;

    Button trigger;

    final Handler colorChangeHandler = new Handler();
    private int colorIndex = 0; // Initialize the color index to zero
    private boolean isAutomaticChangeEnabled = false; // Initialize as disabled



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
        trigger = findViewById(R.id.trigger_button);


        int color_white = ResourcesCompat.getColor(getResources(), R.color.white, null);
        ColorStateList whiteTint = ColorStateList.valueOf(color_white);

        // Set the initial background tints
        ViewCompat.setBackgroundTintList(red, whiteTint);
        ViewCompat.setBackgroundTintList(yellow, whiteTint);
        ViewCompat.setBackgroundTintList(green, whiteTint);

        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trigger.getText().toString().equals("Start")) {
                    trigger.setText("Pause");
                    isAutomaticChangeEnabled = true;
                    startColorChangeTimer();
                } else {
                    trigger.setText("Start");
                    isAutomaticChangeEnabled = false;
                    colorChangeHandler.removeCallbacksAndMessages(null);
                }
            }
        });


    }
    private void startColorChangeTimer() {
        colorChangeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Execute colorChange method
                colorChange();
                if (isAutomaticChangeEnabled) {
                    // Repeat every 3000 milliseconds (3 seconds)
                    colorChangeHandler.postDelayed(this, 3000);
                }
            }
        }, 3000); // Initial delay of 3 seconds before the first color change
    }
    private void colorChange() {
        //default color
        int whiteColor = ResourcesCompat.getColor(getResources(), R.color.white, null);

        //for the red, yellow, and green states
        int redColor = ResourcesCompat.getColor(getResources(), R.color.red, null);
        int yellowColor = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        int greenColor = ResourcesCompat.getColor(getResources(), R.color.green, null);

        // Create an array of ColorStateList objects for each state
        ColorStateList redTint = ColorStateList.valueOf(redColor);
        ColorStateList yellowTint = ColorStateList.valueOf(yellowColor);
        ColorStateList greenTint = ColorStateList.valueOf(greenColor);
        ColorStateList whiteTint = ColorStateList.valueOf(whiteColor);

        // Set the background tint for each ImageButton based on the colorIndex
        switch (colorIndex) {
            case 0:
                ViewCompat.setBackgroundTintList(red, redTint);
                ViewCompat.setBackgroundTintList(yellow, whiteTint);
                ViewCompat.setBackgroundTintList(green, whiteTint);
                break;
            case 1:
                ViewCompat.setBackgroundTintList(red, whiteTint);
                ViewCompat.setBackgroundTintList(yellow, yellowTint);
                ViewCompat.setBackgroundTintList(green, whiteTint);
                break;
            case 2:
                ViewCompat.setBackgroundTintList(red, whiteTint);
                ViewCompat.setBackgroundTintList(yellow, whiteTint);
                ViewCompat.setBackgroundTintList(green, greenTint);
                break;
        }

        // Increment the color index to the next state
        colorIndex = (colorIndex + 1) % 3;
    }

}









