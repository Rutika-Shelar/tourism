package com.example.tourism.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.tourism.entity.Story;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class StorySubmissionViewModel extends ViewModel {
    private FirebaseFirestore firestore;

    public StorySubmissionViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void submitStory(String title, String description, String userName, String imageUrl) {
        long timestamp = System.currentTimeMillis();
        String status = "Pending"; // Default status

        Story story = new Story(null, title, description, userName, timestamp, status, imageUrl);

        Map<String, Object> storyMap = new HashMap<>();
        storyMap.put("title", story.getTitle());
        storyMap.put("description", story.getDescription());
        storyMap.put("userName", story.getUserName());
        storyMap.put("timestamp", story.getTimestamp());
        storyMap.put("status", story.getStatus());
        storyMap.put("imageUrl", story.getImageUrl());

        firestore.collection("stories")
                .add(storyMap)
                .addOnSuccessListener(documentReference -> {
                    // Success callback
                })
                .addOnFailureListener(e -> {
                    // Error handling
                });
    }
}
