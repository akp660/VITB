package com.abhijeet.vitb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setOnClickListener(view -> vtop());

        LottieAnimationView lottieAnimationView3 = findViewById(R.id.lottieAnimationView3);
        lottieAnimationView3.setOnClickListener(view -> foody());

        LottieAnimationView developer = findViewById(R.id.profile);
        developer.setOnClickListener(view -> profile());
    }

    public void vtop(){
        Intent intent = new Intent(MainActivity.this, web_view.class);
        startActivity(intent);
    }

    public void foody(){
        Intent intent = new Intent(MainActivity.this, hostel_selection.class);
        startActivity(intent);
    }

    public void profile(){
        Intent intent = new Intent(MainActivity.this, dev_profile.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

}