package com.theyestech.yestechpremium;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpProvider {
    private static final String BASE_URL = "https://localhost:44338/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), null, params, null, responseHandler);
    }

    public static void post2(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postLogin(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), null, params, null, responseHandler);
    }

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static String getBaseURL() {
        return BASE_URL.replace("api/", "");
    }

    //Directory Files
    public static String getNewsfeedDir() {
        return BASE_URL.replace("controllerClass/", "newsfeed-files/");
    }

    public static String getVideoLabDir() {
        return BASE_URL.replace("controllerClass/", "");
    }

    public static String getProfileDir() {
        return BASE_URL.replace("controllerClass/", "user_images/");
    }

    public static String getSubjectDir() {
        return BASE_URL.replace("controllerClass/", "subject-files/");
    }

    public static String getTopicDir() {
        return BASE_URL.replace("controllerClass/", "topic-files/");
    }

    public static String getQuizDir() {
        return BASE_URL.replace("controllerClass/", "");
    }

    public static String getStickerDir() {
        return BASE_URL.replace("controllerClass/", "img/");
    }

    public static String getNotesDir() {
        return BASE_URL.replace("controllerClass/", "notes-files/");
    }
}
