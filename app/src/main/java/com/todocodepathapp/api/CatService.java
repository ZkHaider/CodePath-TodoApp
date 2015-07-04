package com.todocodepathapp.api;

import com.todocodepathapp.api.interfaces.ICat;
import com.todocodepathapp.api.models.Response;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by ZkHaider on 7/4/15.
 */
public class CatService {

    private static final String API_URL = "http://thecatapi.com/api";

    private static CatService mCatService;
    private static RestAdapter mAsyncRestAdapter;

    public static CatService getClient() {
        if (mCatService == null)
            mCatService = new CatService();
        return mCatService;
    }
    private CatService() {
        mAsyncRestAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new SimpleXMLConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public void getCatImages(String format, int perPage, Callback<Response> callback) {
        ICat iCat = mAsyncRestAdapter.create(ICat.class);
        iCat.getCatImages(format, perPage, callback);
    }

}
