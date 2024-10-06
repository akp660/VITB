package com.abhijeet.vitb.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.abhijeet.vitb.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Settings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final long CACHE_EXPIRY_TIME = 2 * 60 * 60 * 1000; // 2 hours

    private ImageView plusIcon, messLogo;

    public Settings() {
        // Required empty public constructor
    }

    // Create new instance of Settings fragment with parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
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

    // Inflate layout and initialize UI components
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ShimmerFrameLayout shimmerLayout = view.findViewById(R.id.shimmerLayout);
        CardView weatherCardView = view.findViewById(R.id.cardView110);
        TextView tempTextView = view.findViewById(R.id.textView21);
        ImageView weatherIcon = view.findViewById(R.id.imageView2);
        TextView weatherDesc = view.findViewById(R.id.textView23);
        plusIcon = view.findViewById(R.id.imageView5);
        messLogo = view.findViewById(R.id.CRCLlogo);
        MaterialCardView checkUpdateButton = view.findViewById(R.id.check_update);

        // Set listener for the "Check for update" button
        checkUpdateButton.setOnClickListener(view1 -> {
            vibrate();
            openPlayStoreLink();
        });

        // Initialize mess details
        setupMessDetails();

        // Set listener to allow mess selection or change
        plusIcon.setOnClickListener(v -> showMessSelectionPopup());
        messLogo.setOnClickListener(v -> showMessSelectionPopup());

        // Load weather data
        loadWeatherData(shimmerLayout, weatherCardView, tempTextView, weatherDesc, weatherIcon);

        return view;
    }

    // Setup mess details from SharedPreferences and update UI
    private void setupMessDetails() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String messName = preferences.getString("messName", "");

        if (!messName.isEmpty()) {
            plusIcon.setVisibility(View.GONE);
            messLogo.setVisibility(View.VISIBLE);

            // Update the logo based on selected mess name
            switch (messName) {
                case "CRCL":
                    messLogo.setImageResource(R.drawable.crcl_logo);
                    break;
                case "Mayuri (Boys)":
                    messLogo.setImageResource(R.drawable.mayurib_logo);
                    break;
                case "Mayuri (Girls)":
                    messLogo.setImageResource(R.drawable.mayurig_logo);
                    break;
                case "AB":
                    messLogo.setImageResource(R.drawable.ab_logo);
                    break;
                case "Foodex":
                    messLogo.setImageResource(R.drawable.foodex_logo);
                    break;
            }
        } else {
            plusIcon.setVisibility(View.VISIBLE);
        }
    }

    // Load weather data from cache or API
    private void loadWeatherData(ShimmerFrameLayout shimmerLayout, CardView weatherCardView,
                                 TextView tempTextView, TextView weatherDesc, ImageView weatherIcon) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        long currentTimeMillis = System.currentTimeMillis();
        long lastApiCallTimestamp = preferences.getLong("last_api_call_timestamp", 0);

        if (currentTimeMillis - lastApiCallTimestamp < CACHE_EXPIRY_TIME) {
            // Load cached weather data
            loadCachedWeatherData(preferences, tempTextView, weatherDesc, weatherIcon, shimmerLayout, weatherCardView);
        } else {
            // Fetch new weather data from API
            fetchWeatherDataFromApi(shimmerLayout, weatherCardView, tempTextView, weatherDesc, weatherIcon, preferences, currentTimeMillis);
        }
    }

    // Load cached weather data
    private void loadCachedWeatherData(SharedPreferences preferences, TextView tempTextView,
                                       TextView weatherDesc, ImageView weatherIcon,
                                       ShimmerFrameLayout shimmerLayout, CardView weatherCardView) {
        String cachedTemperature = preferences.getString("cached_temperature", "25°C");
        String cachedIconUrl = preferences.getString("icon_id", "https://openweathermap.org/img/wn/03d@2x.png");
        String cachedDescription = preferences.getString("icon_desc", "scattered clouds");

        tempTextView.setText(cachedTemperature);
        weatherDesc.setText(cachedDescription);
        Picasso.get().load(cachedIconUrl).into(weatherIcon);

        shimmerLayout.stopShimmer();
        shimmerLayout.setVisibility(View.GONE);
        weatherCardView.setVisibility(View.VISIBLE);
    }

    // Fetch weather data from API
    private void fetchWeatherDataFromApi(ShimmerFrameLayout shimmerLayout, CardView weatherCardView,
                                         TextView tempTextView, TextView weatherDesc, ImageView weatherIcon,
                                         SharedPreferences preferences, long currentTimeMillis) {
        shimmerLayout.startShimmer();

        String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=23.077428360226556&lon=76.85120708035352&appid=d9e0ccb1ddbc14dd33b4d765916ec92d";
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, weatherUrl, null,
                response -> {
                    try {
                        JSONObject main = response.getJSONObject("main");
                        int temp = main.getInt("temp") - 275;
                        String tempCelsius = temp + "°C";
                        tempTextView.setText(tempCelsius);

                        JSONArray weatherArray = response.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);
                        String icon = weather.getString("icon");
                        String description = weather.getString("description");
                        weatherDesc.setText(description);
                        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
                        Picasso.get().load(iconUrl).into(weatherIcon);

                        shimmerLayout.stopShimmer();
                        shimmerLayout.setVisibility(View.GONE);
                        weatherCardView.setVisibility(View.VISIBLE);

                        // Cache the fetched weather data
                        cacheWeatherData(preferences, tempCelsius, description, iconUrl, currentTimeMillis);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle API error with default values
                    tempTextView.setText("25°C");
                    weatherDesc.setText("scattered clouds");
                    Picasso.get().load("https://openweathermap.org/img/wn/03d@2x.png").into(weatherIcon);

                    shimmerLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);
                    weatherCardView.setVisibility(View.VISIBLE);
                });

        requestQueue.add(jsonObjectRequest);
    }

    // Cache fetched weather data in SharedPreferences
    private void cacheWeatherData(SharedPreferences preferences, String temperature,
                                  String description, String iconUrl, long currentTimeMillis) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cached_temperature", temperature);
        editor.putString("icon_desc", description);
        editor.putString("icon_id", iconUrl);
        editor.putLong("last_api_call_timestamp", currentTimeMillis);
        editor.apply();
    }

    // Show mess selection popup
    private void showMessSelectionPopup() {
        // List of mess options
        final String[] messOptions = {"CRCL", "Mayuri (Boys)", "Mayuri (Girls)", "AB", "Foodex"};

        // Build an AlertDialog
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        builder.setTitle("Select Mess");

        // Set the list of mess options
        builder.setItems(messOptions, (dialog, which) -> {
            // Get the selected mess name
            String selectedMess = messOptions[which];

            // Save the selected mess in SharedPreferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("messName", selectedMess);
            editor.apply();

            // Update the mess logo
            setupMessDetails();
        });

        // Show the AlertDialog
        builder.show();
    }

    // Vibrate on user interaction
    private void vibrate() {
        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    // Open Play Store link for app updates
    private void openPlayStoreLink() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + requireContext().getPackageName()));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + requireContext().getPackageName()));
            startActivity(intent);
        }
    }
}
