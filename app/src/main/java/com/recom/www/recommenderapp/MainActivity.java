package com.recom.www.recommenderapp;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.login.LoginManager;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.Geofence;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.recom.www.recommenderapp.Fragments.HomeFragment;
import com.recom.www.recommenderapp.Fragments.NearbyFragment;
import com.recom.www.recommenderapp.Fragments.ProfileFragment;
import com.recom.www.recommenderapp.Models.Events;
import com.recom.www.recommenderapp.Models.GlobalBus;
import com.recom.www.recommenderapp.Services.LocationService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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


public class MainActivity extends AppCompatActivity implements OnLocationUpdatedListener {
    private LocationGooglePlayServicesProvider provider;

    private static final int LOCATION_PERMISSION_ID = 1001;
    private ActionBar toolbar;
    private FragmentManager fm=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        com.facebook.accountkit.AccessToken accessToken1 = AccountKit.getCurrentAccessToken();
        if(accessToken == null || accessToken1 == null){
            if (LoginManager.getInstance()!=null){
                LoginManager.getInstance().logOut();
            }
            Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
            getApplicationContext().startActivity(myIntent);
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams1 = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams1.setBehavior(new BottomNavigationBehavior());

        loadFragment(new HomeFragment());



        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        requestPermission();
        startLocation();
               // startService(new Intent(this, LocationService.class));
    }
    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_nearby:
                    fragment=new NearbyFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_user:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
    private void startLocation() {

        provider = new LocationGooglePlayServicesProvider();
        provider.setCheckLocationSettings(true);

        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();

        smartLocation.location(provider).continuous().start(this);
       // smartLocation.activity().start(this);

        // Create some geofences
      //  GeofenceModel mestalla = new GeofenceModel.Builder("1").setTransition(Geofence.GEOFENCE_TRANSITION_ENTER).setLatitude(39.47453120000001).setLongitude(-0.358065799999963).setRadius(500).build();
       // smartLocation.geofencing().add(mestalla).start(this);
    }

    private void stopLocation() {
        SmartLocation.with(this).location().stop();
        Timber.tag("Location").i("Stopped");
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
            Toast.makeText(this, text,Toast.LENGTH_LONG).show();

            Events.ActivityFragmentMessage activityFragmentMessageEvent =
                    new Events.ActivityFragmentMessage(text);
            Events.ActivityActivityMessage activityMessageEvent =
                    new Events.ActivityActivityMessage("Hello From Main");

            GlobalBus.getBus().postSticky(activityFragmentMessageEvent);
            GlobalBus.getBus().postSticky(activityMessageEvent);
            //EventBus.getDefault().postSticky(new LocationReceivedEvent(6.4531, 3.3958));
            // We are going to get the address for the current position

        } else {
            Timber.i("Null");
        }
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.FragmentActivityMessage fragmentActivityMessage) {
       /*
        Toast.makeText(getApplicationContext(),fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
        */
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (provider != null) {
            provider.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        stopLocation();
        GlobalBus.getBus().unregister(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Register this fragment to listen to event.
        if (!GlobalBus.getBus().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

}