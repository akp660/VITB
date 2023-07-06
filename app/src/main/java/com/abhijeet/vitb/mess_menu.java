package com.abhijeet.vitb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class mess_menu extends AppCompatActivity {

    ImageView refresh_button;

    Spinner spinner;
    ArrayList<String> arrNames = new ArrayList<>();
    ArrayList<String> arrDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu);

        spinner = findViewById(R.id.spinner);

        refresh_button = findViewById(R.id.refresh_button);

        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                rotateAnimation.setDuration(500);
                refresh_button.startAnimation(rotateAnimation);
            }
        });

        arrDays.add("Monday");
        arrDays.add("Tuesday");
        arrDays.add("Wednesday");
        arrDays.add("Thursday");
        arrDays.add("Friday");
        arrDays.add("Saturday");
        arrDays.add("Sunday");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_view, arrDays);
        spinnerAdapter.setDropDownViewResource(R.layout.dropdown_view);
        spinner.setAdapter(spinnerAdapter);

        // Automatically select the current day
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        spinner.setSelection(dayOfWeek - 2); // Adjusting for 0-based index and skipping Sunday

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String arrDays = spinner.getSelectedItem().toString();
                View contentView = null;

                if (arrDays.equals("Monday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_monday, null);
                    vibrate();
                } else if (arrDays.equals("Tuesday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_tuesday, null);
                    vibrate();
                } else if (arrDays.equals("Wednesday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_wednesday, null);
                    vibrate();
                } else if (arrDays.equals("Thursday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_thursday, null);
                    vibrate();
                } else if (arrDays.equals("Friday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_friday, null);
                    vibrate();
                } else if (arrDays.equals("Saturday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_saturday, null);
                    vibrate();
                } else if (arrDays.equals("Sunday")) {
                    contentView = LayoutInflater.from(mess_menu.this).inflate(R.layout.activity_boys_sunday, null);
                    vibrate();
                }

                FrameLayout contentLayout = findViewById(R.id.constentLayout);
                contentLayout.removeAllViews();
                if (contentView != null) {
                    contentLayout.addView(contentView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // Method to trigger vibration
    private void vibrate() {
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
