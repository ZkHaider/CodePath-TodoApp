package com.todocodepathapp.api.interfaces;

import com.todocodepathapp.api.models.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ZkHaider on 7/4/15.
 */
public interface ICat {

    @GET("/images/get")
    void getCatImages(@Query("format") String format,
                      @Query("results_per_page") int perPage,
                      Callback<Response> callback);

}
