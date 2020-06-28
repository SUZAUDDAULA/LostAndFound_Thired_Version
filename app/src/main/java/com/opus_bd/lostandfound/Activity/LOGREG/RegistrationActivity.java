package com.opus_bd.lostandfound.Activity.LOGREG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
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
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.Text;
import com.google.android.material.textfield.TextInputEditText;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.hbb20.CountryCodePicker;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.PoliceInterface.SorothalReportActivity;
import com.opus_bd.lostandfound.Adapter.Extra.CountryCodesAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Adapter.MobileOperatorAdapter;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.CustomListeners;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.ybs.passwordstrengthmeter.PasswordStrength;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.Utils.Utilities.capturedBitmap;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;
public class RegistrationActivity extends AppCompatActivity{

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.v1)
    View v1;
//    @BindView(R.id.v2)
//    View v2;

    @BindView(R.id.ivpassShow)
    ImageView ivpassShow;
    @BindView(R.id.etPhn)
    EditText etPhn;

@BindView(R.id.otpVarified)
TextView otpVarified;

    @BindView(R.id.textNext)
    TextView textNext;

    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassWord)
    EditText etConfirmPassWord;


    @BindView(R.id.llReg)
    LinearLayout llReg;

    @BindView(R.id.llOTP)
    LinearLayout llOTP;

    //OTP Layout
    public String otp;
    @BindView(R.id.tvTimer)
    TextView tvTimer;


    @BindView(R.id.etOtp)
    TextInputEditText etOtp;
    @BindView(R.id.etNidNum1)
    TextInputEditText etNidNum1;
    @BindView(R.id.etNidNum2)
    TextInputEditText etNidNum2;
    @BindView(R.id.etNidNum3)
    TextInputEditText etNidNum3;
    String user;
    public int Step;

    public static String citizen, NationalIdentityType, NationalIdentityNo, AddressType, PhoneNumber, UserName, Email, Password, ConfirmPassword;

    public String Language, english, banglaa;

    //LOCATION eNABLE
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        ButterKnife.bind(this);
        Boolean languageStatus = getSharedPrefValue();
        english = getResources().getString(R.string.english);
        banglaa = getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language = english;
        } else {
            Language = banglaa;
        }
        llReg.setVisibility(View.VISIBLE);
        llOTP.setVisibility(View.GONE);
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

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

    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }



public void  getOTP(){
    String userNAME = SharedPrefManager.getInstance(RegistrationActivity.this).getUser();
    String otpCODE = SharedPrefManager.getInstance(RegistrationActivity.this).getotp();
    if (userNAME != null && otpCODE != null) {
        submitOTP(userNAME, otpCODE);
    }
}




    /*OTP Layout*/

    public void OTPLayout() {
        Step = 6;
        llOTP.setVisibility(View.VISIBLE);
        llReg.setVisibility(View.GONE);

        //user = SharedPrefManager.getInstance(getContext()).getUser();
        Utilities.showLogcatMessage(" OTP " + otp);
        try {
            etOtp.setText(String.valueOf((otp.charAt(0))));
            etNidNum1.setText(String.valueOf(otp.charAt(1)));
            etNidNum2.setText(String.valueOf(otp.charAt(2)));
            etNidNum3.setText(String.valueOf(otp.charAt(3)));
        } catch (Exception e) {
        }

        otpVarified.setVisibility(View.VISIBLE);
        textNext.setVisibility(View.GONE);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText("00:" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tvTimer.setText("00:00");

            }

        }.start();
    }

    @OnClick(R.id.textNext)
    public void textNext() {
        submitToServer();
    }

    @OnClick({R.id.otpVarified})
    public void tvResigtration() {
        try {
            Intent intent = new Intent(RegistrationActivity.this, DashboardActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }
    }


    public void submitToServer() {
        Random rand = new Random();
        String id = String.format("%04d", rand.nextInt(10000));
        otp=id;
//        OTPLayout();
//        Toast.makeText(RegistrationActivity.this, "OTP " + otp, Toast.LENGTH_SHORT).show();

        SharedPrefManager.getInstance(RegistrationActivity.this).clearToken();
        SharedPrefManager.getInstance(RegistrationActivity.this).clearotp();
        SharedPrefManager.getInstance(RegistrationActivity.this).clearUser();
        final RegistrationModel registrationModel = new RegistrationModel();

        registrationModel.setPhoneNumber(etPhn.getText().toString());
        registrationModel.setUserName(etPhn.getText().toString());
//        registrationModel.setEmail("suza@gmail.com");
        registrationModel.setPassword(etPassword.getText().toString());
        registrationModel.setConfirmPassword(etConfirmPassWord.getText().toString());
        registrationModel.setConfirmPassword(etConfirmPassWord.getText().toString());
        registrationModel.setUserFrom("mobile");
        registrationModel.setNationalIdentityType(1);
        registrationModel.setAddressType(1);
        registrationModel.setNationalIdentityNo("12345678");
        Utilities.showLogcatMessage("RegistrationModel :" + registrationModel.toString());
//
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.Register(registrationModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                try {
                    Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    //Toast.makeText(RegistrationActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {
                        String auth = response.body().getJwt().replace("{\"auth_token\":\"", "");
                        String auth1 = auth.replace("\"}", "");
                        Constants.PHONE_NO=etPhn.getText().toString();
                        Constants.PROFILE_NAME=etPhn.getText().toString();
                        Constants.LOGIN_WITH="mobile";
                        Utilities.showLogcatMessage("token " + auth1);
                        SharedPrefManager.getInstance(RegistrationActivity.this).saveToken(auth1);
                        Utilities.showLogcatMessage("responce");

                        try {
                            otp = response.body().getUserInfo().getOtpCode();
                            if (otp != null && otp != "") {
                                getSmSOTP(etPhn.getText().toString(), otp);
                                OTPLayout();
                                Toast.makeText(RegistrationActivity.this, "OTP " + otp, Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(RegistrationActivity.this, "Otp Not get", Toast.LENGTH_SHORT).show();

                            }

                            SharedPrefManager.getInstance(RegistrationActivity.this).saveotp(response.body().getUserInfo().getOtpCode());
                            SharedPrefManager.getInstance(RegistrationActivity.this).saveUser(response.body().getUserInfo().getUserName());
                        } catch (Exception e) {
                            Utilities.showLogcatMessage("Exception 1" + e.toString());
                            Toast.makeText(RegistrationActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(RegistrationActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                Toast.makeText(RegistrationActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getSmSOTP(String mobile, String otp) {
        String token = "Opus@1234";
        //progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> StringCall = retrofitService.SendSMSOTP(mobile, otp, token);
        StringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body() != null) {
                    Toast.makeText(RegistrationActivity.this, "OTP " + otp, Toast.LENGTH_SHORT).show();
                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(RegistrationActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void submitOTP(String user, String otp) {

        final RegistrationModel registrationModel = new RegistrationModel();

        registrationModel.setUserName(user);
        registrationModel.setOtpCode(otp);

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.OTPVarified(registrationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(RegistrationActivity.this, "Sussecfullly Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(RegistrationActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Fail to connect ", Toast.LENGTH_SHORT).show();

            }
        });
    }


}