package com.todocodepathapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.todocodepathapp.R;

/**
 * Created by ZkHaider on 7/4/15.
 */
public class CatFragment extends Fragment {

    private Context mContext;
    private ImageView mCatImage;
    private String mImageUrl;

    public void setContent(Context context) {
        this.mContext = context;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cat, null);

        mCatImage = (ImageView) root.findViewById(R.id.catImage);

        Picasso.with(mContext)
                .load(mImageUrl)
                .fit()
                .centerInside()
                .into(mCatImage);

        return root;
    }
}
