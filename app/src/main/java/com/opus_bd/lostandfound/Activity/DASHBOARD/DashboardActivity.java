package com.opus_bd.lostandfound.Activity.DASHBOARD;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.opus_bd.lostandfound.Activity.ENRTY.FoundAndRecoveredDetailsActicity;
import com.opus_bd.lostandfound.Activity.ENRTY.InformationEntryActivity;
import com.opus_bd.lostandfound.Activity.ENRTY.VehicleEntryActivity;
import com.opus_bd.lostandfound.Activity.LOGREG.LoginActivity;
import com.opus_bd.lostandfound.Activity.SEARCH.VehicleSearchActivity;
import com.opus_bd.lostandfound.Adapter.GDInformationAdapter;
import com.opus_bd.lostandfound.Adapter.NewsFeedAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.StatusTypeViewModel;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivappLogo)
    ImageView ivappLogo;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.user_prifile_pic)
    ImageView user_prifile_pic;

    @BindView(R.id.profile_Name)
    TextView profile_Name;

    private MenuItem item;
    GDInformationAdapter gdInformationAdapter;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<GDInformation> gdInformationArrayList = new ArrayList<>();
    ArrayList<NewsFeedViewModel> newsFeedViewModelArrayList = new ArrayList<>();

    ProgressDialog progress;

    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSION = 0x3;
    //LOCATION eNABLE
//    private GoogleApiClient googleApiClient;
//    private Location mylocation;
    GoogleSignInClient mGoogleSignInClient;

   // @Override
//    protected void onRestart() {
//        gpsanable();
//        super.onRestart();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }

    //LOCATION eNABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //setProgress();
        //getAllGDInfoStatus();
        //getAllGDInfo();
        //getAllNewsFeedInfo();
        //intRecyclerView();

        //LOCATION eNABLE

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            profile_Name.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).circleCrop().into(user_prifile_pic);
        }

        //setUpGClient();
        //LOCATION eNABLE
    }
    public void setProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Data Loading.... ");
    }



    @OnClick(R.id.ivappLogo)
    public void ivappLogo() {
        mDrawerLayout.openDrawer(GravityCompat.END);
    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
        }
    }


    private void displaySelectedScreen(int itemId) {

        switch (itemId) {
            case R.id.ihelp:

                break;
            case R.id.iNotification:
                break;
            case R.id.ilogout: {
                try {
//                    SharedPrefManager.getInstance(this).clearToken();
//                    Toast.makeText(this, "Logged out successfully!!", Toast.LENGTH_SHORT).show();
//                    Intent intent2 = new Intent(this, LoginActivity.class);
//                    startActivity(intent2);
//                    finish();
                    signOut();

                } catch (Exception e) {
                    Utilities.showLogcatMessage("Logout " + e.toString());
                }
            }
            break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DashboardActivity.this, "Signed Out Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @OnClick({R.id.fabTheft})
    public void llTheft() {
        Constants.ENTRY_TYPE_ID = 1;
        Constants.ENTRY_TYPE_Name = "Lost/Stolen";
        Intent intent = new Intent(DashboardActivity.this, FoundAndRecoveredDetailsActicity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.fablost})
    public void fablost() {
        Constants.ENTRY_TYPE_ID = 2;
        Constants.ENTRY_TYPE_Name = "Found/Recovered";
        Intent intent = new Intent(DashboardActivity.this, FoundAndRecoveredDetailsActicity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        try {
            displaySelectedScreen(menuItem.getItemId());
        } catch (Exception e) {
            Utilities.showLogcatMessage("Logout " + e.toString());
        }

        return true;
    }


    //lOCATION ENABLE

//
//    public void gpsanable() {
//        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        boolean gps_enabled = false;
//        boolean network_enabled = false;
//
//        try {
//            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        } catch (Exception ex) {
//        }
//
//        try {
//            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception ex) {
//        }
//
//        if (!gps_enabled && !network_enabled) {
//            // getMyLocation();
//            // notify user
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.gps_network_not_enabled)
//                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                            // startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                            //getMyLocation();
//                        }
//                    }).setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).show();
//        }
//    }
//
//    private synchronized void setUpGClient() {
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, 0, DashboardActivity.this)
//                .addConnectionCallbacks(this)
//
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//        googleApiClient.connect();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mylocation = location;
//        if (mylocation != null) {
//            /*Intent i = new Intent(HardwareInformationActivity.this, LoginActivity.class);
//            startActivity(i);
//            // close this activity
//            finish();*/
//        } else {
//            Utilities.showLogcatMessage(" No Thanks 2");
//            // showDialog();
//            finish();
//        }
//
//    }
//
//
//    private void getMyLocation() {
//        if (googleApiClient != null) {
//            if (googleApiClient.isConnected()) {
//                int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION);
//                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//                    @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
//                    locationRequest.setInterval(3000);
//                    locationRequest.setFastestInterval(3000);
//                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                            .addLocationRequest(locationRequest);
//                    builder.setAlwaysShow(true);
//                    LocationServices.FusedLocationApi
//                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) DashboardActivity.this);
//                    PendingResult<LocationSettingsResult> result =
//                            LocationServices.SettingsApi
//                                    .checkLocationSettings(googleApiClient, builder.build());
//                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//
//                        @Override
//                        public void onResult(LocationSettingsResult result) {
//                            final Status status = result.getStatus();
//                            switch (status.getStatusCode()) {
//                                case LocationSettingsStatusCodes.SUCCESS:
//                                    // All location settings are satisfied.
//                                    // You can initialize location requests here.
//                                    int permissionLocation = ContextCompat
//                                            .checkSelfPermission(DashboardActivity.this,
//                                                    Manifest.permission.ACCESS_FINE_LOCATION);
//                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                                        mylocation = LocationServices.FusedLocationApi
//                                                .getLastLocation(googleApiClient);
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                                    // Location settings are not satisfied.
//                                    // But could be fixed by showing the user a dialog.
//                                    try {
//                                        // Show the dialog by calling startResolutionForResult(),
//                                        // and check the result in onActivityResult().
//                                        // Ask to turn on GPS automatically
//                                        status.startResolutionForResult(DashboardActivity.this,
//                                                REQUEST_CHECK_SETTINGS_GPS);
//                                    } catch (IntentSender.SendIntentException e) {
//                                        // Ignore the error.
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                                    // Location settings are not satisfied.
//                                    // However, we have no way
//                                    // to fix the
//                                    // settings so we won't show the dialog.
//                                    // finish();
//                                    break;
//                            }
//                        }
//                    });
//                }
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //updateGPSStatus("GPS is Enabled in your device");
                        //getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // showDialog();
                        finish();
                        break;
                }
                break;
        }
    }

//
//    private void updateGPSStatus(String status) {
//        // gps_status.setText(status);
//    }


//    private void checkPermissions() {
//        int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION);
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
//            if (!listPermissionsNeeded.isEmpty()) {
//                ActivityCompat.requestPermissions(this,
//                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSION);
//            }
//        } else {
//            getMyLocation();
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//            getMyLocation();
//        }
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
}
