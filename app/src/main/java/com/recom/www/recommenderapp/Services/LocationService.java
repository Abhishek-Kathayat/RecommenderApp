package com.recom.www.recommenderapp.Services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.Geofence;

import java.util.ArrayList;
import java.util.List;

import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;
import timber.log.Timber;

/**
 * Created by roberto on 9/29/16.
 */

public class LocationService extends Service {
    private static final String TAG = "MyLocationService";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 0;
    private static final float LOCATION_DISTANCE = 10f;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class LocationListener implements OnLocationUpdatedListener, OnActivityUpdatedListener, OnGeofencingTransitionListener {
        Location mLastLocation;
        private void startLocation() {

            LocationGooglePlayServicesProvider provider = new LocationGooglePlayServicesProvider();
            provider.setCheckLocationSettings(true);

            SmartLocation smartLocation = new SmartLocation.Builder(getApplicationContext()).logging(true).build();

            smartLocation.location(provider).start(this);
            smartLocation.activity().start(this);

            // Create some geofences
            //GeofenceModel mestalla = new GeofenceModel.Builder("1").setTransition(Geofence.GEOFENCE_TRANSITION_ENTER).setLatitude(39.47453120000001).setLongitude(-0.358065799999963).setRadius(500).build();
           // smartLocation.geofencing().add(mestalla).start(this);
        }

        @Override
        public void onLocationUpdated(Location location) {
            showLocation(location);
        }

        @SuppressLint("SetTextI18n")
        private void showLocation(Location location) {
            if (location != null) {
                @SuppressLint("DefaultLocale") final String text = String.format("Latitude %.6f, Longitude %.6f",
                        location.getLatitude(),
                        location.getLongitude());
                Timber.i(text);


            } else {
               Timber.i("NULL");
            }
        }



        @Override
        public void onActivityUpdated(DetectedActivity detectedActivity) {

        }

        @Override
        public void onGeofenceTransition(TransitionGeofence transitionGeofence) {

        }
    }
}