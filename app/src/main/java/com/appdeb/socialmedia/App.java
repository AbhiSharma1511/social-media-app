package com.appdeb.socialmedia;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ymzQ8atk5zl64mJuhFGOI6LVNRtqFvgTOFTJgnrq")
                // if defined
                .clientKey("BCvXXaqkzUhvMeO4v3W2izP9tIQ5MsB4BgSXuAkq")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
