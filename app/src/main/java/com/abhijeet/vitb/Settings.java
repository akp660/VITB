package com.abhijeet.vitb;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;

public class Settings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private boolean isJsonResponseReceived = false;

    private static final long TWO_HOURS_IN_MILLIS = 2 * 60 * 60 * 1000;

//    private long lastApiCallTimestamp = 0;

    public Settings() {
        // Required empty public constructor
    }

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ShimmerFrameLayout shimmerLayout = view.findViewById(R.id.shimmerLayout);
        CardView cardview = view.findViewById(R.id.cardView110);
        TextView myTextView = view.findViewById(R.id.textView21);
        ImageView imageView = view.findViewById(R.id.imageView2);

        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        long lastApiCallTimestamp = preferences.getLong("last_api_call_timestamp", 0);

        if (currentTimeMillis - lastApiCallTimestamp < TWO_HOURS_IN_MILLIS) {
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            String cachedTemperature = preferences.getString("cached_temperature", "25°C");
            String cachedIconUrl = preferences.getString("cached_icon_url", "https://openweathermap.org/img/wn/03d@2x.png");
            Log.d("icon_url", cachedIconUrl);
            myTextView.setText(cachedTemperature);
            Picasso.get().load(cachedIconUrl).into(imageView);

            shimmerLayout.stopShimmer();
            shimmerLayout.setVisibility(View.GONE);
            cardview.setVisibility(View.VISIBLE);
        }

        else {
            shimmerLayout.startShimmer();

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(getContext());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    "https://api.openweathermap.org/data/2.5/weather?lat=23.077428360226556&lon=76.85120708035352&appid=d9e0ccb1ddbc14dd33b4d765916ec92d",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject currentObj = response.getJSONObject("main");
                                Log.d("check", "json got response.");
                                int temp = currentObj.getInt("temp") - 275;
                                String temp_c = temp+"°C";
                                myTextView.setText(temp_c);

                                JSONArray weatherArray = response.getJSONArray("weather");
                                JSONObject weatherObject = weatherArray.getJSONObject(0);
                                String icon = weatherObject.getString("icon");
                                String icon_url = "https://openweathermap.org/img/wn/"+icon+"@2x.png";

                                Picasso.get().load("https://openweathermap.org/img/wn/"+icon+"@2x.png").into(imageView);

                                isJsonResponseReceived = true;
                                shimmerLayout.stopShimmer();
                                shimmerLayout.setVisibility(View.GONE);
                                cardview.setVisibility(View.VISIBLE);

                                saveDataToCache(temp_c,icon_url,currentTimeMillis);
//                            Log.d("myapp", "The temperature in Celsius is : " + icon + "°C");

//                            Log.d("myapp", "The temperature in Celsius is : " + temp + "°C");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    myTextView.setText("25°C");
                    isJsonResponseReceived = true;
                    shimmerLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);
                    cardview.setVisibility(View.VISIBLE);
//                Log.d("myapp", "onErrorResponse: dance");
                }
            });
            requestQueue.add(jsonObjectRequest);

            lastApiCallTimestamp = currentTimeMillis;
        }
        return view;
    }

    private void saveDataToCache(String curr_t, String icon_id,long currentTimeMillis) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = preferences.edit();
        
        editor.putString("cached_temperature", curr_t);
        editor.putString("icon_id", icon_id);
        editor.putLong("last_api_call_timestamp", currentTimeMillis);

        editor.apply();
    }

}