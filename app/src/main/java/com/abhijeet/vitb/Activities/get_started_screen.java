package com.abhijeet.vitb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;

import com.abhijeet.vitb.R;

public class get_started_screen extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_screen);

        //Get_started_page to Home_page
        button = findViewById(R.id.getstarted);
        button.setOnClickListener(view -> home_page());


    }

    public void home_page(){
        Intent intent = new Intent(get_started_screen.this, home_page.class);
        startActivity(intent);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
        }

    }
}