package com.example.tourism.ui.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.ui.viewmodel.StorySubmissionViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StorySubmissionActivity extends AppCompatActivity {
    private EditText edtTitle, edtDescription;
    private Button btnSubmit;
    private ImageView imgPreview;
    private StorySubmissionViewModel viewModel;
    private String imageUrl = "https://via.placeholder.com/150";
    private String userName = "Anonymous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_submission);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        imgPreview = findViewById(R.id.imgPreview);

        viewModel = new ViewModelProvider(this).get(StorySubmissionViewModel.class);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userName = currentUser.getEmail();
        }

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.image_background)
                .into(imgPreview);

        btnSubmit.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.submitStory(title, description, userName, imageUrl);
            Toast.makeText(this, "Story submitted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
