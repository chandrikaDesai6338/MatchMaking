package com.work.matchmaking.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.work.matchmaking.R;

public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.match)
                .error(R.drawable.match);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(options)
                .into(view);
    }
}