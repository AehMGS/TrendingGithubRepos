package com.aeh.trendinggithubrepos.rest;

public class ApiUtils {

    private static final String BaseUrl = "https://api.github.com";

    public static RestInterface getAPIService()
    {
        return RetrofitClient.getClient(BaseUrl).create(RestInterface.class);
    }
}
