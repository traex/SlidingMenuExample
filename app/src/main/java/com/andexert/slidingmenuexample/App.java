package com.andexert.slidingmenuexample;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by TraeX on 24/09/2014.
 */
public class App extends Application
{
    private static Bus bus;

    @Override
    public void onCreate()
    {
        super.onCreate();
        bus = new Bus();
    }

    public static Bus getBus()
    {
        return bus;
    }
}
