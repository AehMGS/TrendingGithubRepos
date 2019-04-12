package com.aeh.trendinggithubrepos.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestInterface {

    @GET
    Call<List<String>> getRepos(@Url String url);
}
