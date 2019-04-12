package com.aeh.trendinggithubrepos.rest;

public class ApiUtils {

    private static final String BaseUrl = "https://api.github.com";
    public static final int REST_STATUS_SUCCESS = 200;

    public static RestInterface getAPIService()
    {
        return RetrofitClient.getClient(BaseUrl).create(RestInterface.class);
    }
}
