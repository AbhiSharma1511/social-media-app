package com.appdeb.socialmedia;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qUDAsK6Ql7EMVLCEP4k2Qq942ni5Rc2tId6kBsCE")
                // if defined
                .clientKey("H4Dzv8FD5fgnj2n0JX0PhJHqdMVsNJDcaoRLtYGX")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
