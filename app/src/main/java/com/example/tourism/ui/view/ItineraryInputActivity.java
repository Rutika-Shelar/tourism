package com.example.tourism.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.R;
import com.example.tourism.data.repository.ItineraryRepository;

public class ItineraryInputActivity extends AppCompatActivity {

    private EditText placeInput, dateInput, durationInput, budgetInput;
    private Button generatePlanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_input);

        // Initialize UI components
        placeInput = findViewById(R.id.place_input);
        dateInput = findViewById(R.id.trip_date_input);
        durationInput = findViewById(R.id.trip_duration_input);
        budgetInput = findViewById(R.id.budget_input);
        generatePlanButton = findViewById(R.id.generate_plan_button);

        // Set placeholders (hints) for input fields
        placeInput.setHint("Enter destination (e.g., Paris)");
        dateInput.setHint("Enter start date (e.g., 2023-12-01)");
        durationInput.setHint("Enter duration in days (e.g., 5)");
        budgetInput.setHint("Enter budget (e.g., 1000)");

        generatePlanButton.setOnClickListener(v -> {
            String place = placeInput.getText().toString().trim();
            String date = dateInput.getText().toString().trim();
            String duration = durationInput.getText().toString().trim();
            String budget = budgetInput.getText().toString().trim();

            if (place.isEmpty() || date.isEmpty() || duration.isEmpty() || budget.isEmpty()) {
                Toast.makeText(ItineraryInputActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Fetch itinerary using ItineraryRepository
                ItineraryRepository.getInstance().fetchItinerary(place, date, duration, budget, (description, itinerary) -> {
                    runOnUiThread(() -> {
                        // Display the fetched itinerary
                        Toast.makeText(ItineraryInputActivity.this, "Itinerary generated successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ItineraryInputActivity.this, ItineraryDisplayActivity.class);
                        intent.putExtra("description", description);
                        intent.putExtra("itinerary", itinerary);
                        startActivity(intent);
                    });
                });
            }
        });
    }
}