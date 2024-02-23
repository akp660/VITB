package com.abhijeet.vitb;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Mess extends Fragment {

    private TextView text;
    private TextView textView;

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

        // Initialize views
        text = rootView.findViewById(R.id.text);
        ImageView imageView = rootView.findViewById(R.id.mess_selection);
        textView = rootView.findViewById(R.id.mess_name);
        CardView calender = rootView.findViewById(R.id.calender);

        // Set today's date in the text TextView by default
        Calendar calendar = Calendar.getInstance();
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH);
        int initialDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        text.setText(String.valueOf(initialDayOfMonth));



        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Mess").child("CRCL").child("Mon").child("Breakfast")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("my1log", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("my1log", "Failed to read value.", error.toException());
                    }
                });




        // Set OnClickListener for the calender CardView
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                // Display only the day of the month in the text TextView
                                text.setText(String.valueOf(dayOfMonth));
                            }
                        }, initialYear, initialMonth, initialDayOfMonth); // Set initial date to today's date

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

        // Set OnClickListener for the imageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the popup with names
                showNameSelectionPopup();
            }
        });

        return rootView;
    }

    private void showNameSelectionPopup() {
        // Array of names
        final String[] names = {"C R C L", "Mayuri (Boys)", "Foodex", "AB", "Mayuri (Girls)"};

        // Create AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Name");
        builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Set the selected name to textView
                textView.setText(names[which]);
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



}
