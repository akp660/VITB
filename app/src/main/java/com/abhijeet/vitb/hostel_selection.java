package com.abhijeet.vitb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.airbnb.lottie.LottieAnimationView;

public class hostel_selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_selection);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView3);
        lottieAnimationView.setOnClickListener(view -> girl());

        LottieAnimationView lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);
        lottieAnimationView2.setOnClickListener(view -> boy());

    }

    public void girl(){
        Intent intent = new Intent(hostel_selection.this, mess_menu_femail.class);
        startActivity(intent);
    }

    public void boy(){
        Intent intent = new Intent(hostel_selection.this, mess_menu.class);
        startActivity(intent);
    }

}