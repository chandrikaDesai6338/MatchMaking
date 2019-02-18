package com.work.matchmaking.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.databinding.DataBindingUtil;

import com.work.matchmaking.R;
import com.work.matchmaking.databinding.FragmentUserBinding;
import com.work.matchmaking.service.model.Response1;
import com.work.matchmaking.service.model.Result;
import com.work.matchmaking.view.adapter.UserAdapter;
import com.work.matchmaking.view.callback.UserClickCallback;
import com.work.matchmaking.viewmodel.UserViewModel;

import java.util.List;


public class UserFragment extends Fragment {

    public static final String TAG = "UserFragment";
    private UserAdapter userAdapter;
    private FragmentUserBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        userAdapter = new UserAdapter(userClickCallback);
        binding.userList.setAdapter(userAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final UserViewModel viewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(UserViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getUserObservable().observe(this, new Observer<Response1>() {
            @Override
            public void onChanged(@Nullable Response1 response1) {
                if (response1 != null) {
                    binding.setIsLoading(false);
                    userAdapter.setUserList(response1.getResults());
                }
            }
        });
    }

    private final UserClickCallback userClickCallback = new UserClickCallback() {
        @Override
        public void onClick(List<Result> resultList) {
        }

        @Override
        public void onClickCancel() {

        }
    };
}
