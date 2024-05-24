package ru.mirea.tmenovapa.yandexmaps;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class YandexMapApplication extends Application {
    private final static String YANDEX_MAP_API_KEY = "e60dfaa9-9419-4679-b847-842f2003f1a9";

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(YANDEX_MAP_API_KEY);
    }
}