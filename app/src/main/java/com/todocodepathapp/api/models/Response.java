package com.todocodepathapp.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ZkHaider on 7/4/15.
 */
@Root(name = "response")
public class Response {

    @Element(name = "data")
    private Data mData;
    public Data getData() {
        return mData;
    }
}
