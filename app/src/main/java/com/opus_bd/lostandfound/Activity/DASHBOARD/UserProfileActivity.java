package com.opus_bd.lostandfound.Activity.DASHBOARD;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.opus_bd.lostandfound.Activity.ENRTY.VehicleEntryActivity;
import com.opus_bd.lostandfound.Activity.LOGREG.LoginActivity;
import com.opus_bd.lostandfound.Activity.LOGREG.RegisterTypeActivity;
import com.opus_bd.lostandfound.Model.Dashboard.ApplicationUser;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserInfo;
import com.opus_bd.lostandfound.Model.User.UserProfileModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehiclePostModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    @BindView(R.id.ivProfilePic)
    ImageView ivProfilePic;

    @BindView(R.id.user_prifile_pic)
    ImageView user_prifile_pic;

    @BindView(R.id.profile_Name)
    TextView profile_Name;

    @BindView(R.id.etProfileName)
    EditText etProfileName;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassWord)
    EditText etConfirmPassWord;
    ProgressDialog progress;
    String fileInfo,fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        getUserInfo();
        String profileName = SharedPrefManager.getInstance(this).getProfileName();
        String imageUrl = SharedPrefManager.getInstance(this).getImageUrl();
        profile_Name.setText(profileName);
        if(imageUrl==""){
            user_prifile_pic.setImageResource(R.drawable.ic_human_db);
        }else {
            Glide.with(this).load(imageUrl).circleCrop().into(user_prifile_pic);
        }

    }

    @OnClick(R.id.ivProfilePic)
    public void ImageAdd() {
        CropImage.activity().start(UserProfileActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    fileInfo = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
                } catch (IOException e) {
                    Utilities.showLogcatMessage("IMAGE_BASE_64 " + e.toString());
                }

                //imagePath=getRealPathFromUri(uri);
                ivProfilePic.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void setProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Data Processing.... ");
    }

    @OnClick(R.id.btnPost)
    public void btnPost() {
        submitToServer();
    }


    public void submitToServer() {
        progress.show();
        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        String UserFrom = SharedPrefManager.getInstance(this).getLogInWith();

        String password="123456";//etPassword.getText().toString();
        String conPassword="123456";//etConfirmPassWord.getText().toString();
        String fullName=etProfileName.getText().toString();
        String personEmail=etEmail.getText().toString();
        String phoneNo=etPhone.getText().toString();

        final UserProfileModel model = new UserProfileModel("Bangladeshi",1,"1234321",1,phoneNo,UserName,personEmail,password,conPassword,"",fullName,UserFrom,fileInfo);

        Utilities.showLogcatMessage("UserProfileModel :" + model.toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<RegistrationModel> registrationRequest = retrofitService.ProfileUpdate(SharedPrefManager.BEARER + token,model);
        registrationRequest.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                try {
                    Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    //Toast.makeText(RegistrationActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {
                        progress.dismiss();
                        Uri imageUri= Uri.parse(response.body().getImagePath());
                        user_prifile_pic.setImageURI(imageUri);
                        profile_Name.setText(response.body().getFullName());
                        SharedPrefManager.getInstance(UserProfileActivity.this).saveProfileName(response.body().getFullName());
                        SharedPrefManager.getInstance(UserProfileActivity.this).saveImageUrl(String.valueOf(imageUri));
                        Intent intent=new Intent(UserProfileActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        progress.dismiss();
                        Toast.makeText(UserProfileActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    progress.dismiss();
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(UserProfileActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                progress.dismiss();
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                Toast.makeText(UserProfileActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getUserInfo() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<RegistrationModel> registrationRequest = retrofitService.GetUserInfo(SharedPrefManager.BEARER + token,UserName);
        registrationRequest.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                try {
                    Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    if (response.body() != null) {
                        etProfileName.setText(response.body().getFullName());
                        etEmail.setText(response.body().getEmail());
                        etPhone.setText(response.body().getPhoneNumber());
                        if(response.body().getImagePath()==""){
                            ivProfilePic.setImageResource(R.drawable.ic_human_db);
                        }else {
                            Uri imageUri=Uri.fromFile(new File(response.body().getImagePath()));
                            ivProfilePic.setImageURI(imageUri);
                        }
                    }
                    else {
                        Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                Toast.makeText(UserProfileActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}