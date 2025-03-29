package com.example.tourism.ui.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fabAddStory = view.findViewById(R.id.fabAddStory);
        fabAddStory.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StorySubmissionActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
