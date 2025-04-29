package com.example.tourism.ui.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tourism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;

public class HomeFragment extends Fragment {

    private Button openPlannerButton;
    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        mapView = new MapView(requireContext());


        MapboxMap mapboxMap = mapView.getMapboxMap();
        CameraOptions cameraOptions = new CameraOptions.Builder()
                .center(Point.fromLngLat(-98.0, 39.5))
                .pitch(0.0)
                .zoom(2.0)
                .bearing(0.0)
                .build();
        mapboxMap.setCamera(cameraOptions);


        ViewGroup mapContainer = view.findViewById(R.id.map_container);
        mapContainer.addView(mapView);


        openPlannerButton = view.findViewById(R.id.open_planner_button);

        openPlannerButton.setOnClickListener(v -> {
            Intent intent = requireContext().getPackageManager().getLaunchIntentForPackage("com.dmbfm.MapboxRoutes");
            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(requireContext(), "App not installed.", Toast.LENGTH_LONG).show();
                            }
        });

//        openPlannerButton.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(android.net.Uri.parse("https://ee00890ee2e5240d7d.gradio.live"));
//            startActivity(intent);
//        });

        FloatingActionButton fabAddStory = view.findViewById(R.id.fabAddStory);
        fabAddStory.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StorySubmissionActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mapView != null) mapView.onStart();
    }

    @Override
    public void onStop() {
        if (mapView != null) mapView.onStop();
        super.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        if (mapView != null) mapView.onDestroy();
        super.onDestroyView();
    }
}
