package com.work.matchmaking.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.work.matchmaking.service.model.Response1;
import com.work.matchmaking.service.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final LiveData<Response1> userObservable;

    public UserViewModel(Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        userObservable = UserRepository.getInstance().getUserList(10);
    }

    /**
     * Expose the LiveData User query so the UI can observe it.
     */
    public LiveData<Response1> getUserObservable() {
        return userObservable;
    }
}
