package com.abhijeet.vitb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setOnClickListener(view -> boys());

        LottieAnimationView lottieAnimationView3 = findViewById(R.id.lottieAnimationView3);
        lottieAnimationView3.setOnClickListener(view -> girl());

        LottieAnimationView developer = findViewById(R.id.profile);
        developer.setOnClickListener(view -> profile());
    }

    public void boys(){
        Intent intent = new Intent(MainActivity.this, mess_menu.class);
        startActivity(intent);
    }

    public void girl() {
        Intent intent = new Intent(MainActivity.this, mess_menu_femail.class);
        startActivity(intent);
    }

    public void profile(){
        Intent intent = new Intent(MainActivity.this, dev_profile.class);
        startActivity(intent);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
