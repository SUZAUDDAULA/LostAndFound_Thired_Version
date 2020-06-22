package com.opus_bd.lostandfound.Fragments.Registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.Activity.RegistrationProcessActivity.AddressType;
import static com.opus_bd.lostandfound.Activity.RegistrationProcessActivity.NationalIdentityNo;
import static com.opus_bd.lostandfound.Activity.RegistrationProcessActivity.NationalIdentityType;
import static com.opus_bd.lostandfound.Activity.RegistrationProcessActivity.citizen;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegFragment extends Fragment {

    @BindView(R.id.ivpassShow)
    ImageView ivpassShow;
    @BindView(R.id.etPhn)
    EditText etPhn; @BindView(R.id.etEmail)
    EditText etEmail; @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassWord)
    EditText etConfirmPassWord;
    boolean isPassChecked = true;
    Context context;

    public RegFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step=6;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }
    @OnClick(R.id.textNext)
    public void btnLogIn() {
       /* IdFragment idFragment=new IdFragment();
        idFragment.setId(1);*/
        // ((RegistrationActivity) context).selectPage(5);
        submitToServer();
        try{
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit);
            ft.replace(R.id.fragmentContainer, new OTPFragment(getContext()), "NewFragmentTag");
            ft.commit();}
        catch (Exception e){

            Utilities.showLogcatMessage(" Execption "+e.toString());
        }

    }

    @OnClick(R.id.ivpassShow)
    public void Passwordshow() {

        if (isPassChecked) {
            // show password
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etConfirmPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_visibility_off).into(ivpassShow);
            isPassChecked=false;
        } else {
            // hide password
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etConfirmPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
            Glide.with(this).load(R.drawable.ic_view).into(ivpassShow);
            isPassChecked=true;
        }
    }

    private void submitToServer() {

        SharedPrefManager.getInstance(context).clearToken();
        SharedPrefManager.getInstance(context).clearotp();
        SharedPrefManager.getInstance(context).clearUser();
        final RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setCitizenship(citizen);
        registrationModel.setNationalIdentityType(NationalIdentityType);
        registrationModel.setNationalIdentityNo(NationalIdentityNo);
        registrationModel.setAddressType(AddressType);
        registrationModel.setPhoneNumber(etPhn.getText().toString());
        registrationModel.setUserName(etPhn.getText().toString());
        registrationModel.setEmail(etEmail.getText().toString());
        registrationModel.setPassword(etPassword.getText().toString());
        registrationModel.setConfirmPassword(etConfirmPassWord.getText().toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.Register(registrationModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                    try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");


                        try{
                           /* String auth=response.body().getJwt().replace("{\"auth_token\":\"","");
                            String auth1=auth.replace("\"}","");
                            Utilities.showLogcatMessage(" "+auth1);
                            SharedPrefManager.getInstance(context).saveToken(auth1);*/
                            SharedPrefManager.getInstance(context).saveotp(response.body().getUserInfo().getOtpCode());
                            SharedPrefManager.getInstance(context).saveUser(response.body().getUserInfo().getUserName());
                        }
                        catch (Exception e) {
                            Utilities.showLogcatMessage("Exception 1"+e.toString());
                            Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(context, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                  /*      Intent intent = new Intent(context, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);*/

                    } else {
                        Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                        Utilities.showLogcatMessage("Exception 2"+e.toString());
                    Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(context, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
