package com.todocodepathapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.todocodepathapp.api.models.Image;
import com.todocodepathapp.fragments.CatFragment;

import java.util.List;

/**
 * Created by ZkHaider on 7/4/15.
 */
public class CaturdayAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Image> mImages;

    public CaturdayAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    public void setImages(List<Image> images) {
        this.mImages = images;
    }

    @Override
    public Fragment getItem(int position) {
        CatFragment catFragment = new CatFragment();
        catFragment.setContent(mContext);
        catFragment.setImageUrl(mImages.get(position).getUrl());
        return catFragment;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }
}
