package com.work.matchmaking.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.work.matchmaking.service.model.Response1;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    private UserService userService;
    private static UserRepository userRepository;

    /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
*/
    private UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.HTTPS_API_USER_URL)
                .client(new OkHttpClient().newBuilder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

    public synchronized static UserRepository getInstance() {
        if (userRepository == null) {
            if (userRepository == null) {
                userRepository = new UserRepository();
            }
        }
        return userRepository;
    }

    public LiveData<Response1> getUserList(int resultNo) {
        final MutableLiveData<Response1> data = new MutableLiveData<>();

        userService.queryUser(resultNo).enqueue(new Callback<Response1>() {
            @Override
            public void onResponse(Call<Response1> call, Response<Response1> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Response1> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
