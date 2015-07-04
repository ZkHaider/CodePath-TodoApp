package com.todocodepathapp.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ZkHaider on 7/4/15.
 */
@Element(name = "image")
public class Image {

    @Element(name = "url")
    private String mUrl;
    public String getUrl() {
        return mUrl;
    }

    @Element(name = "id")
    private String mId;
    public String getId() {
        return mId;
    }

    @Element(name = "source_url")
    public String mSourceUrl;
    public String getSourceUrl() {
        return mSourceUrl;
    }

}
