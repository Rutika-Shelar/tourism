package com.example.tourism.ui.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.entity.AppDatabase;
import com.example.tourism.entity.UserProfile;
import com.example.tourism.entity.UserProfileDao;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;

public class ProfileFragment extends Fragment {
    private ImageView imgProfileBackground, iconEditProfile;
    private TextView txtUserName, txtAboutUser;
    private Button btnLogout;
    private UserProfileDao userProfileDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgProfileBackground = view.findViewById(R.id.imgProfileBackground);
        iconEditProfile = view.findViewById(R.id.iconEditProfile);
        txtUserName = view.findViewById(R.id.txtUserName);
        txtAboutUser = view.findViewById(R.id.txtAboutUser);
        btnLogout = view.findViewById(R.id.btnLogout);

        userProfileDao = AppDatabase.getInstance(getContext()).userProfileDao();

        loadUserProfile();

        iconEditProfile.setOnClickListener(v -> {
            EditProfileDialog editDialog = new EditProfileDialog();
            editDialog.setTargetFragment(ProfileFragment.this, 1);
            editDialog.show(getParentFragmentManager(), "EditProfileDialog");
        });

        btnLogout.setOnClickListener(v -> showLogoutDialog());

        return view;
    }

    private void loadUserProfile() {
        UserProfile userProfile = userProfileDao.getProfile();
        if (userProfile != null) {
            txtUserName.setText(userProfile.getName());
            txtAboutUser.setText(userProfile.getAbout());
            Glide.with(this).load(Uri.parse(userProfile.getProfileImageUri())).into(imgProfileBackground);
        }
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> logoutUser())
                .setNegativeButton("No", null)
                .show();
    }


    public void updateUserProfile(String name, String about, String imageUri) {
        UserProfile profile = new UserProfile(name, about, imageUri);
        userProfileDao.insertProfile(profile);
        loadUserProfile();
    }
    private void logoutUser() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        userProfileDao.deleteAllProfiles();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }
}
