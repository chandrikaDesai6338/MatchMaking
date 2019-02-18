package com.work.matchmaking.view.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.work.matchmaking.R;
import com.work.matchmaking.service.model.Response1;
import com.work.matchmaking.service.model.Result;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            UserFragment fragment = new UserFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, UserFragment.TAG).commit();
        }
    }

    public void show(List<Result> resultsList) {

    }
}
