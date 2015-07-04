package com.todocodepathapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todocodepathapp.R;
import com.todocodepathapp.adapters.CaturdayAdapter;
import com.todocodepathapp.api.models.Image;

import java.util.List;

/**
 * Created by ZkHaider on 7/4/15.
 */
public class CaturdayFragment extends Fragment {

    private List<Image> mImages;
    private ViewPager mCatViewPager;
    private CaturdayAdapter mCaturdayAdapter;
    private FragmentManager mFragmentManager;

    public void setImages(List<Image> images) {
        this.mImages = images;
    }

    public void setFragmentManager(FragmentManager fm) {
        this.mFragmentManager = fm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_caturday, null);

        mCatViewPager = (ViewPager) root.findViewById(R.id.catViewPager);
        mCaturdayAdapter = new CaturdayAdapter(getActivity(), mFragmentManager);
        mCaturdayAdapter.setImages(mImages);
        mCatViewPager.setAdapter(mCaturdayAdapter);

        return root;
    }
}
