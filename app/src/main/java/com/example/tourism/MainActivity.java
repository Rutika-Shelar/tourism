package com.example.tourism;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.ui.view.HomeActivity;
import com.example.tourism.ui.view.LoginActivity;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {

            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}