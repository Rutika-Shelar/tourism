package com.example.tourism.ui.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.tourism.data.repository.ItineraryRepository;

public class ItineraryViewModel extends ViewModel {

    private final MutableLiveData<String> shortDescription = new MutableLiveData<>();
    private final MutableLiveData<String> dayWiseItinerary = new MutableLiveData<>();
    private final ItineraryRepository repository = ItineraryRepository.getInstance();

    public LiveData<String> getShortDescription() {
        return shortDescription;
    }

    public LiveData<String> getItinerary() {
        return dayWiseItinerary;
    }

    public void fetchItinerary(String place, String date, String duration, String budget) {
        repository.fetchItinerary(place, date, duration, budget, (description, itinerary) -> {
            shortDescription.postValue(description);
            dayWiseItinerary.postValue(itinerary);
        });
    }
}
