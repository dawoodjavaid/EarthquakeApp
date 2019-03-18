package com.example.earthquakecomplete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class MyAsyckTaskLoader extends AsyncTaskLoader<ArrayList<MyEarthquakeData>> {
    ArrayList<MyEarthquakeData> myEarthquakeDataArrayList;

    public MyAsyckTaskLoader(@NonNull Context context, ArrayList<MyEarthquakeData> myEarthquakeDataArrayList) {
        super(context);
        this.myEarthquakeDataArrayList = myEarthquakeDataArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<MyEarthquakeData> loadInBackground() {
        return myEarthquakeDataArrayList;
    }
}
