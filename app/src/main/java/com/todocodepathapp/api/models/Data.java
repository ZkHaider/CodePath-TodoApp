package com.todocodepathapp.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ZkHaider on 7/4/15.
 */
@Root(name = "data")
public class Data {

    @ElementList(name = "images", entry = "image")
    private List<Image> mImages;
    public List<Image> getImages() {
        return mImages;
    }

}
