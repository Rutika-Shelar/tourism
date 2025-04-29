package com.example.tourism.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.example.tourism.R;
import com.example.tourism.ui.viewmodel.ItineraryViewModel;

public class ItineraryDisplayActivity extends AppCompatActivity {

    private TextView placeName, description, itinerary;
    private ProgressBar progressBar;
    private final ItineraryViewModel itineraryViewModel = new ItineraryViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_display);

        placeName = findViewById(R.id.place_name);
        description = findViewById(R.id.place_description);
        itinerary = findViewById(R.id.day_wise_itinerary);
        progressBar = findViewById(R.id.progress_bar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String place = extras.getString("place");
            String date = extras.getString("date");
            String duration = extras.getString("duration");
            String budget = extras.getString("budget");

            placeName.setText(place);
            progressBar.setVisibility(View.VISIBLE);

            itineraryViewModel.fetchItinerary(place, date, duration, budget);

            itineraryViewModel.getShortDescription().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String desc) {
                    if (desc != null && !desc.isEmpty()) {
                        description.setText(desc);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        description.setText("Description not available");
                    }
                }
            });

            itineraryViewModel.getItinerary().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String itin) {
                    if (itin != null && !itin.isEmpty()) {
                        itinerary.setText(itin);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        itinerary.setText("Itinerary not available");
                    }
                }
            });
        }
    }
}