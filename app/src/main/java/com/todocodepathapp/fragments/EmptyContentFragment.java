package com.todocodepathapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todocodepathapp.R;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class EmptyContentFragment extends Fragment {

    public static EmptyContentFragment mEmptyContentFragment;

    public static EmptyContentFragment getInstance() {
        if (mEmptyContentFragment == null)
            mEmptyContentFragment = new EmptyContentFragment();
        return mEmptyContentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_content, null);
    }

}
