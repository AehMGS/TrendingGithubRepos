package com.aeh.trendinggithubrepos.rest;

import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestInterface {

    @GET
    Call<JSONObject> getRepos(@Url String url);
}
