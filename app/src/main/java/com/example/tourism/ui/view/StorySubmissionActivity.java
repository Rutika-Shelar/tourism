package com.example.tourism.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.tourism.R;
import com.example.tourism.ui.viewmodel.StorySubmissionViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StorySubmissionActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText edtTitle, edtDescription;
    private Button btnSubmit;
    private ImageView imgPreview;
    private StorySubmissionViewModel viewModel;
    private Uri selectedImageUri;
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

        imgPreview.setOnClickListener(v -> openGallery());

        btnSubmit.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String imageUrl = selectedImageUri != null ? selectedImageUri.toString() : "";
            viewModel.submitStory(title, description, userName, imageUrl);
            Toast.makeText(this, "Story submitted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imgPreview.setImageURI(selectedImageUri);
        }
    }
}