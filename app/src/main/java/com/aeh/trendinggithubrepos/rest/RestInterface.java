package com.aeh.trendinggithubrepos.rest;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestInterface {

    @GET
    Call<JsonElement> getRepos(@Url String url);
}
