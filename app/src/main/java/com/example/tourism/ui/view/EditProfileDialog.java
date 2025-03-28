package com.example.tourism.ui.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tourism.R;
import android.net.Uri;
import android.widget.ImageView;

public class EditProfileDialog extends DialogFragment {
    private EditText edtName, edtAbout;
    private ImageView imgProfileEdit;
    private Button btnSave;
    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);

        edtName = view.findViewById(R.id.editName);
        edtAbout = view.findViewById(R.id.editAbout);
        imgProfileEdit = view.findViewById(R.id.profileImage);
        btnSave = view.findViewById(R.id.saveButton);

        imgProfileEdit.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String about = edtAbout.getText().toString();
            String imageUri = selectedImageUri != null ? selectedImageUri.toString() : "";

            ProfileFragment parentFragment = (ProfileFragment) getTargetFragment();
            if (parentFragment != null) {
                parentFragment.updateUserProfile(name, about, imageUri);
            }
            dismiss();
        });

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imgProfileEdit.setImageURI(selectedImageUri);
        }
    }
}
