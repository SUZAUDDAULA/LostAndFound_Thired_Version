package com.opus_bd.lostandfound.Activity.DASHBOARD;

import androidx.appcompat.app.AppCompatActivity;

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
import com.opus_bd.lostandfound.Activity.LOGREG.RegisterTypeActivity;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    @BindView(R.id.ivProfilePhoto)
    ImageView ivProfilePhoto;

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

    String fileInfo,fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        profile_Name.setText(Constants.PROFILE_NAME);
        Glide.with(this).load(String.valueOf(Constants.IMAGE_URI)).circleCrop().into(user_prifile_pic);

        etProfileName.setText(Constants.PROFILE_NAME);
        etEmail.setText(Constants.EMAIL);
        etPhone.setText(Constants.PHONE_NO);
        ivProfilePhoto.setImageURI(Constants.IMAGE_URI);

    }

    @OnClick(R.id.ivProfilePhoto)
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
                ivProfilePhoto.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @OnClick(R.id.btnPost)
    public void btnPost() {
        submitToServer();
    }


    public void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        String password=etPassword.getText().toString();
        String conPassword=etConfirmPassWord.getText().toString();
        String fullName=etProfileName.getText().toString();
        String personEmail=etEmail.getText().toString();
        String phoneNo=etPhone.getText().toString();

        final RegistrationModel registrationModel = new RegistrationModel();

        registrationModel.setUserName(UserName);
        registrationModel.setPassword(password);
        registrationModel.setConfirmPassword(conPassword);
        registrationModel.setFullName(fullName);
        registrationModel.setPhoneNumber(phoneNo);
        registrationModel.setUserFrom("google");
        registrationModel.setEmail(personEmail);
        registrationModel.setImagePath(fileInfo);
        registrationModel.setNationalIdentityType(1);
        registrationModel.setAddressType(1);
        Utilities.showLogcatMessage("RegistrationModel :" + registrationModel.toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<RegistrationModel> registrationRequest = retrofitService.ProfileUpdate(SharedPrefManager.BEARER + token,registrationModel);
        registrationRequest.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
                try {
                    Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    //Toast.makeText(RegistrationActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {
                        Intent intent=new Intent(UserProfileActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(UserProfileActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(UserProfileActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
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