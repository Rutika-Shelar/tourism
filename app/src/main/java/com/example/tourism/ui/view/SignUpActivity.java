package com.example.tourism.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.tourism.R;
import com.example.tourism.ui.viewmodel.AuthViewModel;


public class SignUpActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button signUpButton = findViewById(R.id.signUpButton);
        TextView goToLogin = findViewById(R.id.goToLogIn);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getAuthSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sign Up failed", Toast.LENGTH_SHORT).show();
            }
        });

        signUpButton.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            authViewModel.signUp(userEmail, userPassword);
        });
        goToLogin.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
