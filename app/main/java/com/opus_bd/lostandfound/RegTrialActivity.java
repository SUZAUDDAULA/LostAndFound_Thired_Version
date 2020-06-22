package com.opus_bd.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Fragments.Registration.CameraFragment;
import com.opus_bd.lostandfound.Fragments.Registration.CitizenFragment;
import com.opus_bd.lostandfound.Fragments.Registration.IdFragment;
import com.opus_bd.lostandfound.Fragments.Registration.InputFragment;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegTrialActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    Geocoder geocoder;
    List<Address> addresses;
    int c = 0;

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.iv7)
    ImageView iv7;

    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.v5)
    View v5;
    @BindView(R.id.v6)
    View v6;
    @BindView(R.id.lcitizen)
    ConstraintLayout lcitizen;
    @BindView(R.id.lId)
    ConstraintLayout lId;


    //id
    int iid, passid;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.textView2)
    TextView textView2;

//input

    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.etbinNum)
    EditText etbinNum;
    @BindView(R.id.etPassNum)
    EditText etPassNum;
    @BindView(R.id.etExpary)
    EditText etExpary;

    @BindView(R.id.rrNid)
    RelativeLayout tNidNum;
    @BindView(R.id.rrbinNum)
    RelativeLayout tbinNum;
    @BindView(R.id.rrPassNum)
    RelativeLayout tPassNum;
    @BindView(R.id.rrExpary)
    RelativeLayout tExpary;
    @BindView(R.id.rrEmail)
    RelativeLayout rrEmail;
    @BindView(R.id.rrCountryCode)
    RelativeLayout rrCountryCode;

    int inputID, passId, view;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //camera
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    private static final int PICK_IMG = 1;
    private int uploads = 0;

    int count = 0;
    String FIRST_UPLOADED_IMAGE_LINK;

    @BindView(R.id.civimage)
    CircularImageView civimage;

    int FIRST_IMAGE = 1;
    int SECOND_IMAGE = 2;
    int CURRENTLY_SELECTING_IMAGEVIEW;

    //reg



    @BindView(R.id.ivpassShow)
    ImageView ivpassShow;
    @BindView(R.id.etPhn)
    EditText etPhn; @BindView(R.id.etEmail)
    EditText etEmail; @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassWord)
    EditText etConfirmPassWord;
    boolean isPassChecked = true;
    //address

    @BindView(R.id.llnid)
    LinearLayout llnid;
    @BindView(R.id.llnid2)
    LinearLayout llnid2; @BindView(R.id.textView)
    TextView textView;
    //otp

    @BindView(R.id.tvTimer)
    TextView tvTimer;


    @BindView(R.id.etOtp)
    TextInputEditText etOtp;
    String user;
    //activity
    public static int Step;

    public static String citizen, NationalIdentityType, NationalIdentityNo, AddressType, PhoneNumber, UserName, Email, Password, ConfirmPassword;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_trial);
        ButterKnife.bind(this);
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

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

    //citizen
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.cvCitizen, R.id.textNext})
    public void cvCitizen() {
        citizen = "Bangladeshi";
        lcitizen.setVisibility(View.GONE);
        lId.setVisibility(View.VISIBLE);
        setView(1);
        v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.cvFore)
    public void button2() {
        citizen = "Foreigner";
        lcitizen.setVisibility(View.GONE);
        lId.setVisibility(View.VISIBLE);
        setView(2);
        v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    //id
    public void setView(int i) {
        if (i == 1) {
            rl.setVisibility(View.VISIBLE);
            passid = 1;

        } else if (i == 2) {
            rl.setVisibility(View.GONE);
            textView2.setText("Select below");
            passid = 2;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.cvNID, R.id.textNext})
    public void cvNID() {
        setView2(1,passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.cvBID)
    public void cvBID() {
        setView2(2,passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.cvpassport)
    public void cvpassport() {

        setView2(3,passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        RegistrationProcessActivity.NationalIdentityType="3";
        v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }


    //input


    public void setView2(int i, int pass) {
        if (i == 1 && pass == 1) {
            tNidNum.setVisibility(View.VISIBLE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
            view = 1;
        } else if (i == 2 & pass == 1) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.VISIBLE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
            view = 2;
        } else if (i == 3 & pass == 1) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.VISIBLE);
            tExpary.setVisibility(View.VISIBLE);
            view = 3;
        } else if (i == 3 && pass == 2) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.VISIBLE);
            tExpary.setVisibility(View.VISIBLE);
            rrEmail.setVisibility(View.VISIBLE);
            rrCountryCode.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.textNext)
    public void textNext() {
        if (view == 1) {
            RegistrationProcessActivity.NationalIdentityNo = etNidNum.getText().toString();
        } else if (view == 2) {
            RegistrationProcessActivity.NationalIdentityNo = etbinNum.getText().toString();
        } else if (view == 3) {
            RegistrationProcessActivity.NationalIdentityNo = etPassNum.getText().toString();
        }
    }
 /*   @RequiresApi(AppAPI = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage("c"+citizen);
            if (Step == 1) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 2) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 3) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 4) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 5) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 6) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v5.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv6.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 7) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v5.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv6.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v6.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv7.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
        }
    }*/

    private void submitToServer() {
//        Utilities.showProgress(this);
        SharedPrefManager.getInstance(RegTrialActivity.this).clearToken();
        final RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setCitizenship(citizen);

        //SharedPrefManager.getInstance(this).saveUserName(etUserName.getText().toString());
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.Register(registrationModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                //              Utilities.hideProgress(LoginActivity.this);
                try {
                    if (response.body() != null) {

                        String auth = response.body().getJwt().replace("{\"auth_token\":\"", "");
                        String auth1 = auth.replace("\"}", "");
                        Utilities.showLogcatMessage(" " + auth1);
                        SharedPrefManager.getInstance(RegTrialActivity.this).saveToken(auth1);
                        Toast.makeText(RegTrialActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegTrialActivity.this, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(RegTrialActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RegTrialActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(RegTrialActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

        Utilities.showLogcatMessage("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();

        Utilities.showLogcatMessage("address "+address+"city "+city);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
