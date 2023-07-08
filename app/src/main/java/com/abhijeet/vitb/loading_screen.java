package com.abhijeet.vitb;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class loading_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Intent iHome = new Intent(loading_screen.this,MainActivity.class);

        new Handler().postDelayed(() -> startActivity(iHome),1500);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Start the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // Continue with your normal flow
            // Set the content view, initialize views, etc.
            setContentView(R.layout.activity_loading_screen);
            // Other initialization or setup code...
        }

        finish();

    }

}
