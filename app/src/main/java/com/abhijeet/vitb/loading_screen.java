package com.abhijeet.vitb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class loading_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Intent iHome = new Intent(loading_screen.this,MainActivity.class);

        new Handler().postDelayed(() -> startActivity(iHome),1500);

    }

}
