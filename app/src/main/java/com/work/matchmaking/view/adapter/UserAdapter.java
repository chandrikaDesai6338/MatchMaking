package com.work.matchmaking.view.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.work.matchmaking.R;
import com.work.matchmaking.databinding.UserListItemBinding;
import com.work.matchmaking.service.model.Result;
import com.work.matchmaking.view.callback.UserClickCallback;

import java.util.List;
import java.util.Objects;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<? extends Result> userList;

    @Nullable
    private final UserClickCallback userClickCallback;

    public UserAdapter(@Nullable UserClickCallback projectClickCallback) {
        this.userClickCallback = projectClickCallback;
    }

    public void setUserList(final List<? extends Result> userList) {
        if (this.userList == null) {
            this.userList = userList;
            notifyItemRangeInserted(0, userList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.userList.size();
                }

                @Override
                public int getNewListSize() {
                    return userList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.userList.get(oldItemPosition).getId() ==
                            userList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Result result = userList.get(newItemPosition);
                    Result old = userList.get(oldItemPosition);
                    return result.getId() == old.getId();
                }
            });
            this.userList = userList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        UserListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.user_list_item,
                        viewGroup, false);

        binding.setCallback(userClickCallback);

        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder userViewHolder, int i) {

        userViewHolder.binding.setResult(userList.get(i));
        userViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        final UserListItemBinding binding;

        public UserViewHolder(UserListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
