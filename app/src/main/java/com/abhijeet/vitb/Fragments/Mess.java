package com.abhijeet.vitb.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.abhijeet.vitb.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class Mess extends Fragment {

    private TextView text, text2;
    private TextView textView, breakfastVeg, breakfastNonVeg, lunchVeg, lunchNonVeg, lunchSides, lunchStaples, snacks, dinnerVeg, dinnerNonVeg, dinnerSides, dinnerStaples;
    private View breakfastNonVegDivider, lunchNonVegDivider, dinnerNonVegDivider;
    private ImageView breakfastNonVegIcon, lunchNonVegIcon, dinnerNonVegIcon;
    public String messName;
    public String day;
    ImageView refresh_button;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Mess() {
        // Required empty public constructor
    }

    public static Mess newInstance(String param1, String param2) {
        Mess fragment = new Mess();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mess, container, false);

        refresh_button = rootView.findViewById(R.id.refresh_button);

        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                rotateAnimation.setDuration(500);
                refresh_button.startAnimation(rotateAnimation);
                MessMenuRetrieval(messName,day);
                vibrate();

                // Create custom toast
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.fragment_toast_layout, null); // Remove the toast_layout_root parameter
                TextView text = layout.findViewById(R.id.text_toast);
                text.setText("Refreshing...");

                Toast toast = new Toast(requireContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });



        // Initialize views
        breakfastVeg = rootView.findViewById(R.id.breakfastVeg);
        breakfastNonVeg = rootView.findViewById(R.id.breakfastNonVeg);
        breakfastNonVegIcon = rootView.findViewById(R.id.breakFastNonVegIcon);
        breakfastNonVegDivider = rootView.findViewById(R.id.divider);
        lunchVeg = rootView.findViewById(R.id.lunchVeg);
        lunchNonVeg = rootView.findViewById(R.id.lunchNonVeg);
        lunchNonVegIcon = rootView.findViewById(R.id.lunchNonVegIcon);
        lunchNonVegDivider = rootView.findViewById(R.id.divider6);
        lunchSides = rootView.findViewById(R.id.lunchSides);
        lunchStaples = rootView.findViewById(R.id.lunchStaples);
        snacks = rootView.findViewById(R.id.snacksVeg);
        dinnerVeg = rootView.findViewById(R.id.dinnerVeg);
        dinnerNonVeg = rootView.findViewById(R.id.dinnerNonVeg);
        dinnerNonVegIcon = rootView.findViewById(R.id.dinnerNonVegIcon);
        dinnerNonVegDivider = rootView.findViewById(R.id.divider10);
        dinnerSides = rootView.findViewById(R.id.dinnerSides);
        dinnerStaples = rootView.findViewById(R.id.dinnerStaples);

        text = rootView.findViewById(R.id.text);
        text2 = rootView.findViewById(R.id.text2);
        ImageView imageView = rootView.findViewById(R.id.mess_selection);
        textView = rootView.findViewById(R.id.mess_name);
        CardView calender = rootView.findViewById(R.id.calender);

        Calendar calendar = Calendar.getInstance();
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH);
        int initialDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        text.setText(String.valueOf(initialDayOfMonth));

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        day = getDayOfWeekString(dayOfWeek);
        text2.setText(day);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        messName = preferences.getString("messName","");

        if (messName.equals("")){
            //animation pointing select a mess.
        }
        else{
            textView.setText(messName);
            MessMenuRetrieval(messName,day);
        }

        // Set OnClickListener for the calender CardView
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, dayOfMonth);

                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                String day1 = getDayOfWeekString(dayOfWeek);
                                day = day1;
                                text.setText(String.valueOf(dayOfMonth));
                                text2.setText(day);

                                MessMenuRetrieval(messName,day);
                            }
                        }, initialYear, initialMonth, initialDayOfMonth);

                // Show the DatePickerDialog
                datePickerDialog.show();

                haptic ();
            }
        });

        // Set OnClickListener for the imageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the popup with names
                showNameSelectionPopup(day);
                haptic();
            }
        });

        return rootView;

    }

    public void MessMenuRetrieval(String messName ,String day){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Mess").child(messName).child(day).child("Breakfast").child("Veg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        breakfastVeg.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Breakfast").child("NonVeg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);

                        if (Objects.equals(value, "NIL")){
                            breakfastNonVeg.setVisibility(View.GONE);
                            breakfastNonVegIcon.setVisibility(View.GONE);
                            breakfastNonVegDivider.setVisibility(View.GONE);
                        }
                        else{
                            breakfastNonVegIcon.setVisibility(View.VISIBLE);
                            breakfastNonVegDivider.setVisibility(View.VISIBLE);
                            breakfastNonVeg.setVisibility(View.VISIBLE);
                            breakfastNonVeg.setText(value);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Lunch").child("Veg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        lunchVeg.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Lunch").child("NonVeg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);

                        if (Objects.equals(value, "NIL")){
                            lunchNonVeg.setVisibility(View.GONE);
                            lunchNonVegIcon.setVisibility(View.GONE);
                            lunchNonVegDivider.setVisibility(View.GONE);
                        }
                        else{
                            lunchNonVegIcon.setVisibility(View.VISIBLE);
                            lunchNonVegDivider.setVisibility(View.VISIBLE);
                            lunchNonVeg.setVisibility(View.VISIBLE);
                            lunchNonVeg.setText(value);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Lunch").child("Sides")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        lunchSides.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Lunch").child("Staples")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        lunchStaples.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Snacks").child("Veg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        snacks.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Dinner").child("Veg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        dinnerVeg.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Dinner").child("NonVeg")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        if (Objects.equals(value, "NIL")){
                            dinnerNonVeg.setVisibility(View.GONE);
                            dinnerNonVegIcon.setVisibility(View.GONE);
                            dinnerNonVegDivider.setVisibility(View.GONE);
                        }
                        else{
                            dinnerNonVegIcon.setVisibility(View.VISIBLE);
                            dinnerNonVegDivider.setVisibility(View.VISIBLE);
                            dinnerNonVeg.setVisibility(View.VISIBLE);
                            dinnerNonVeg.setText(value);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Dinner").child("Sides")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        dinnerSides.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });

        mDatabase.child("Mess").child(messName).child(day).child("Dinner").child("Staples")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                        dinnerStaples.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });
    }

    public void showNameSelectionPopup(String day) {
        // Array of names

        final String[] names = {"CRCL", "Mayuri (Boys)", "Foodex", "AB", "Mayuri (Girls)"};

        // Create AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Name");
        builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Set the selected name to textView
                String Name = names[which];
                messName = Name;
                textView.setText(Name);

                MessMenuRetrieval(messName, day);
            }

        });

        // Set positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, just dismiss the dialog
            }
        });

        // Show the dialog
        builder.show();

    }


    private String getDayOfWeekString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sun";
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tues";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thurs";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            default:
                return "";
        }
    }


    // Define the vibrate method outside of the onClick method
    private void vibrate() {
        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
        }
    }

    private void haptic() {
        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

}
