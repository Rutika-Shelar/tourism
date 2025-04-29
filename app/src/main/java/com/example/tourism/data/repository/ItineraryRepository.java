package com.example.tourism.data.repository;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class ItineraryRepository {

    private static ItineraryRepository instance;
    private static final String GEMINI_API_KEY = "AIzaSyDrx8-1DOXx8Zd0j8GBy6C2KZ3dGYNarSQ";
    private static final String API_URL = "https://api.gemini.com/v1/generate";
    private final OkHttpClient client;

    private ItineraryRepository() {
        client = new OkHttpClient();
    }

    public static ItineraryRepository getInstance() {
        if (instance == null) {
            instance = new ItineraryRepository();
        }
        return instance;
    }

    public interface ItineraryCallback {
        void onResponse(String description, String itinerary);
    }

    public void fetchItinerary(String place, String date, String duration, String budget, ItineraryCallback callback) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("temperature", 1);
            jsonBody.put("top_p", 0.95);
            jsonBody.put("top_k", 40);
            jsonBody.put("max_output_tokens", 8192);
            jsonBody.put("prompt", "Create a travel itinerary for " + place + " starting from " + date + " for " + duration + " days within a budget of " + budget + ". Provide a short description and day-wise itinerary.");

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody.toString());
            Request request = new Request.Builder()
                    .url(API_URL)
                    .header("Authorization", "Bearer " + GEMINI_API_KEY)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        System.out.println("API Response: " + responseData);
                        try {
                            JSONObject jsonResponse = new JSONObject(responseData);
                            String text = jsonResponse.getString("output"); // Adjust based on Gemini API response structure
                            String[] parts = text.trim().split("\n", 2);
                            String description = (parts.length > 0) ? parts[0].trim() : "Description not available";
                            String itinerary = (parts.length > 1) ? parts[1].trim() : "Itinerary not available";

                            callback.onResponse(description, itinerary);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}