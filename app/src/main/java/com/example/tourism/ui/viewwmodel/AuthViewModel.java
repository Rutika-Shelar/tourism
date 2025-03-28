package com.example.tourism.ui.viewwmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;

public class AuthViewModel extends AndroidViewModel {
    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<Boolean> authSuccess = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> authSuccess.setValue(task.isSuccessful()));
    }

    public void signUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> authSuccess.setValue(task.isSuccessful()));
    }

    public LiveData<Boolean> getAuthSuccess() {
        return authSuccess;
    }
}