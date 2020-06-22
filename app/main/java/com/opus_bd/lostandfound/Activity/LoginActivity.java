package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.opus_bd.lostandfound.Activity.PoliceInterface.ContactActivity;
import com.opus_bd.lostandfound.Activity.PoliceInterface.PoliceMainActivity;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
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

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;

public class LoginActivity extends AppCompatActivity {

    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    boolean isChecked = true;
    boolean isPassChecked = true;

    @BindView(R.id.tvLangugeName)
    Button tvLangugeName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.ivpassShow)
    ImageView ivpassShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Boolean languageStatus = getSharedPrefValue();

        if (languageStatus) {
            tvLangugeName.setText(R.string.bangla);
        } else {
            tvLangugeName.setText(R.string.english);
        }


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

    private void changeLanguage(String language) {
        LocaleHelper.setLocale(LoginActivity.this, language);
        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();

    }


    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }

    private void saveIntoSharedPref(boolean isLanguageEnglish) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_State, isLanguageEnglish);
        editor.apply();
    }

    @OnClick(R.id.tvLangugeName)
    public void button2() {
        isChecked = getSharedPrefValue();
        if (isChecked == true) {

            isChecked = false;

            saveIntoSharedPref(false);

            changeLanguage(Constants.BANGLA);
            tvLangugeName.setText(R.string.english);
        } else {
            isChecked = true;

            saveIntoSharedPref(true);

            changeLanguage(Constants.ENGLISH);
            tvLangugeName.setText(R.string.bangla);
        }
    }

    @OnClick(R.id.btnLogIn)
    public void btnLogIn() {
        if (!validatedForm())
            return;
        submitToServer();

   /*     Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }


    private boolean validatedForm() {
        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            etUserName.setError(getResources().getString(R.string.field_null_error));
            Toast.makeText(this, "Contact field can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(getResources().getString(R.string.field_null_error));
            Toast.makeText(this, "Password field can not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @OnClick(R.id.tvResigtration)
    public void tvResigtration() {
        try {
            Intent intent = new Intent(LoginActivity.this, RegistrationProcessActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }
    }

    @OnClick(R.id.tvForgetPassword)
    public void tvForgetPassword() {
        try {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }
    }

    @OnClick(R.id.ivpassShow)
    public void Passwordshow() {

        if (isPassChecked) {
            // show password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_visibility_off).into(ivpassShow);
            isPassChecked = false;
        } else {
            // hide password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_view).into(ivpassShow);
            isPassChecked = true;
        }
    } @OnClick(R.id.btnSupport)
    public void btnSupport() {
       /* try {
            Intent intent = new Intent(LoginActivity.this, ChatBotActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }*/
  customDialog();
    }


    private void submitToServer() {
//        Utilities.showProgress(this);
        SharedPrefManager.getInstance(LoginActivity.this).clearToken();
       final UserLoginModel userModel = new UserLoginModel(etUserName.getText().toString(), etPassword.getText().toString());
       // final UserLoginModel userModel = new UserLoginModel("Police", "Police");

        //SharedPrefManager.getInstance(this).saveUserName(etUserName.getText().toString());
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.login(userModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                //              Utilities.hideProgress(LoginActivity.this);
                try {
                    if (response.body() != null) {

                        String auth = response.body().getJwt().replace("{\"auth_token\":\"", "");
                        String userName=response.body().getUserInfo().getUserName();
                        String auth1 = auth.replace("\"}", "");
                        Utilities.showLogcatMessage(" " + auth1);
                        SharedPrefManager.getInstance(LoginActivity.this).saveToken(auth1);
                        SharedPrefManager.getInstance(LoginActivity.this).saveUser(response.body().getUserInfo().getUserName());

                        Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();

                        if(userName.equals("suza")){
                            Intent intent = new Intent(LoginActivity.this, PoliceMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }


                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
Utilities.showLogcatMessage(""+ t.toString());
            }
        });
    }
    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(LoginActivity.this,
                android.Manifest.permission.CALL_PHONE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CALL_PHONE);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
           // getMyLocation();
        }

    }


    public void customDialog() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.dialog_custom_support);
        dialog.setTitle(R.string.select_item_for_support);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // set the custom dialog components - text, image and button
        ImageView ivLiveChat = (ImageView) dialog.findViewById(R.id.ivLiveChat);
        ImageView ivEmail = (ImageView) dialog.findViewById(R.id.ivEmail);
        ImageView ivCall = (ImageView) dialog.findViewById(R.id.ivCall);


        CardView cvLiveChat = (CardView) dialog.findViewById(R.id.cvLiveChat);
        CardView cvEmail = (CardView) dialog.findViewById(R.id.cvEmail);
        CardView cvCall = (CardView) dialog.findViewById(R.id.cvCall);

        ivLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                try {
                    intent = new Intent(LoginActivity.this,ChatBotActivity.class);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cvLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(LoginActivity.this,ChatBotActivity.class);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
         ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                checkPermissions();
                try {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01737366028"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        }); cvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01737366028"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailto = "mailto:lostandfound@police.gov.bd" +
                        "?cc=" + "" +
                        "&subject=" + Uri.encode("Lost and Found Support") /*+
                        "?body=" + Uri.encode(bodyText)*/;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                }
                /*Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, "Juelrananatore@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Lost and Found Support");
                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                    intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }*/
            }
        });cvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:lostandfound@police.gov.bd" +
                        "?cc=" + "" +
                        "&subject=" + Uri.encode("Lost and Found Support") /*+
                        "?body=" + Uri.encode(bodyText)*/;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                }
               /* Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, "Juelrananatore@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Lost and Found Support");
                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                    intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                 *//*
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }*/
            }
        });



        dialog.show();
    }
}

