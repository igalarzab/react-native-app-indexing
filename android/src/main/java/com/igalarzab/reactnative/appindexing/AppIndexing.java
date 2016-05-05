package com.igalarzab.reactnative.appindexing;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing.Builder;
import com.google.android.gms.common.api.GoogleApiClient;


public class AppIndexing extends ReactContextBaseJavaModule {

    private static final String LOGTAG = "ReactNativeAppIndexing";

    private GoogleApiClient googleApiClient;

    private Boolean started = false;
    private String title;
    private String description;
    private Uri url;

    public AppIndexing(ReactApplicationContext reactContext, Activity activity) {
        super(reactContext);
        googleApiClient = new GoogleApiClient.Builder(activity).addApi(AppIndex.API).build();
    }

    @Override
    public String getName() {
        return "AppIndexing";
    }

    @ReactMethod
    public void startViewAction(String title, String url, String description) {
        if (started) {
            Log.e(LOGTAG, "Starting new view action before ending the active one");
            stopViewAction();
        }

        setAction(title, url, description);

        googleApiClient.connect();
        AppIndex.AppIndexApi.start(googleApiClient, getAction());

        Log.d(LOGTAG, "Started view action with the URL: " + url);
        started = true;
    }

    @ReactMethod
    public void stopViewAction() {
        if (!started) {
            Log.w(LOGTAG, "There is no active view action");
            return;
        }

        AppIndex.AppIndexApi.end(googleApiClient, getAction());
        googleApiClient.disconnect();

        Log.d(LOGTAG, "Ended view action");
        started = false;
    }

    private Action getAction() {
        Builder builder = new Builder()
            .setName(title)
            .setUrl(url);

        if (description != null) {
            builder.setDescription(description);
        }

        return new Action.Builder(Action.TYPE_VIEW)
            .setObject(builder.build())
            .setActionStatus(Action.STATUS_TYPE_COMPLETED)
            .build();
    }

    private void setAction(String title, String url, String description) {
        this.title = title;
        this.description = description;
        this.url = Uri.parse(url);
    }
}
