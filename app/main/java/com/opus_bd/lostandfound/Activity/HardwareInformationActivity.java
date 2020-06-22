package com.opus_bd.lostandfound.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

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
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.*;

public class HardwareInformationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static int sLastCpuCoreCount = -1;
    @BindView(R.id.IMEINumber)
    TextView IMEINumber;
    @BindView(R.id.SubscriberID)
    TextView SubscriberID;
    @BindView(R.id.SIMNumber)
    TextView SIMNumber;
    @BindView(R.id.IPAddress)
    TextView IPAddress;
    @BindView(R.id.WiFiMACAddress)
    TextView WiFiMACAddress;
    @BindView(R.id.OperatorName)
    TextView OperatorName;
    @BindView(R.id.CountryCode)
    TextView CountryCode;
    @BindView(R.id.DNSAddress)
    TextView DNSAddress;
    @BindView(R.id.MotherBoard)
    TextView MotherBoard;
    @BindView(R.id.Processor)
    TextView Processor; @BindView(R.id.DeviceSerialNumber)
    TextView DeviceSerialNumber;
    TelephonyManager telephonyManager;
    ConnectivityManager connectivityManager;
    List<SubscriptionInfo> subscription;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;

    private Location mylocation;
    @Override
    protected void onRestart() {
        Utilities.showLogcatMessage(" onRestart");
      gpsanable();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Utilities.showLogcatMessage(" onPause");
        super.onPause();
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_information);
        ButterKnife.bind(this);
        setUpGClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    subscription = SubscriptionManager.from(getApplicationContext()).getActiveSubscriptionInfoList();
        telephony();
        IPAddress();
        DNSAddress();
        WiFiMACAddress();
        //MotherBoard
        MotherBoard.setText(BOARD);
        Processor.setText(CPU_ABI);
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            DeviceSerialNumber.setText(getSerial());
        }

    }


    @RequiresApi(api = VERSION_CODES.LOLLIPOP_MR1)
    public void telephony() {
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {


            //IMEINumber
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                IMEINumber.setText(telephonyManager.getDeviceId(0) + "\n" + telephonyManager.getDeviceId(1));
            }
            //  SubscriberID


            SubscriberID.setText(telephonyManager.getSubscriberId());
            //SIMNumber

            SIMNumber.setText(telephonyManager.getSimSerialNumber());

            // CountryCode
            CountryCode.setText(telephonyManager.getNetworkCountryIso());
        }
        //OperatorName
        if (telephonyManager.getSimOperatorName() != null) {
            OperatorName.setText(telephonyManager.getSimOperatorName());
        } else {
            for (int i = 0; i < subscription.size(); i++) {
                SubscriptionInfo info = subscription.get(i);
                OperatorName.setText(info.getCarrierName());
            }

        }
    }

    public void IPAddress() {
        try {
            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            assert wm != null;
            IPAddress.setText(String.valueOf(Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress())));
        } catch (Exception e) {
            Utilities.showLogcatMessage("wifi" + e.toString());
        }
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)

    public void DNSAddress() {
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Service.CONNECTIVITY_SERVICE);

        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());
            if (connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI) {
                DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());

            } else if (connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_ETHERNET) {
                /* there is no EthernetManager class, there is only WifiManager. so, I used this below trick to get my IP range, dns, gateway address etc */
                DNSAddress.setText(connectivityManager.getLinkProperties(connectivityManager.getActiveNetwork()).getDnsServers().toString());
            } else {

            }
        }
    }

    public void WiFiMACAddress() {
        DeviceAdminReceiver admin = new DeviceAdminReceiver();
        DevicePolicyManager devicepolicymanager = admin.getManager(getApplicationContext());
        ComponentName name1 = admin.getWho(getApplicationContext());
        if (devicepolicymanager.isAdminActive(name1)) {
            String mac_address = null;
            if (VERSION.SDK_INT >= VERSION_CODES.N) {
                mac_address = devicepolicymanager.getWifiMacAddress(name1);
                WiFiMACAddress.setText(mac_address);
            }


        } else {
            WiFiMACAddress.setText(getMacAddr());
        }
    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    // res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }


    @OnClick(R.id.btnNext1)
    public void btnNext1() {
        Intent intent = new Intent(HardwareInformationActivity.this, LoginActivity.class);
        startActivity(intent);
    }



    public void gpsanable(){
        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
           // getMyLocation();
            // notify user
            new AlertDialog.Builder(this)
                    .setMessage(R.string.gps_network_not_enabled)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                           // startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            getMyLocation();
                        }
                    }).setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
    }
    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, HardwareInformationActivity.this)
                .addConnectionCallbacks(this)

                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if(mylocation != null){
            /*Intent i = new Intent(HardwareInformationActivity.this, LoginActivity.class);
            startActivity(i);
            // close this activity
            finish();*/
        }
        else {
            Utilities.showLogcatMessage(" No Thanks 2");
            // showDialog();
            finish();
        }

    }



    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(HardwareInformationActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) HardwareInformationActivity.this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(HardwareInformationActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(googleApiClient);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(HardwareInformationActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        updateGPSStatus("GPS is Enabled in your device");
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                       // showDialog();
                        finish();
                        break;
                }
                break;
        }
    }


    private void updateGPSStatus(String status) {
        // gps_status.setText(status);
    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(HardwareInformationActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(HardwareInformationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
