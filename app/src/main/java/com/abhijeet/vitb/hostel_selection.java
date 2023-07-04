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
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

    public void boy(){
        Intent intent = new Intent(hostel_selection.this, mess_menu.class);
        startActivity(intent);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

}