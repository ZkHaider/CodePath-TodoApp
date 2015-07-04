package com.todocodepathapp.eventbus.events;

import com.todocodepathapp.api.models.Image;

import java.util.List;

/**
 * Created by ZkHaider on 7/4/15.
 */
public class LoadedCatImages {

    private List<Image> mImages;

    public LoadedCatImages(List<Image> images) {
        this.mImages = images;
    }

    public List<Image> getImages() {
        return mImages;
    }

}
