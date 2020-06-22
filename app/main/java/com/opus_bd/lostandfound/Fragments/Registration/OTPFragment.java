package com.opus_bd.lostandfound.Fragments.Registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
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
public class OTPFragment extends Fragment {

    @BindView(R.id.tvTimer)
    TextView tvTimer;


    @BindView(R.id.etOtp)
    TextInputEditText etNidNum;
    @BindView(R.id.etNidNum1)
    TextInputEditText etNidNum1;
    @BindView(R.id.etNidNum2)
    TextInputEditText etNidNum2;
    @BindView(R.id.etNidNum3)
    TextInputEditText etNidNum3;
    String user;

    Context context;

    public OTPFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_o_t_p, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step = 7;

        EventBus.getDefault().post(new MessageEvent(true));
        //String otp = SharedPrefManager.getInstance(getContext()).getotp();
        String otp = "1234";
        //user = SharedPrefManager.getInstance(getContext()).getUser();
        Utilities.showLogcatMessage(" OTP " + otp);
        etNidNum.setText(String.valueOf((otp.charAt(0))));
        etNidNum1.setText(String.valueOf(otp.charAt(1)));
        etNidNum2.setText(String.valueOf(otp.charAt(2)));
        etNidNum3.setText(String.valueOf(otp.charAt(3)));

        //  etNidNum.setText(otp);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText("00:" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tvTimer.setText("00:00");

            }

        }.start();
        return v;
    }

    @OnClick(R.id.textNext)
    public void btnLogIn() {
        Intent intent = new Intent(getContext(), DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        // submitToServer();
    }

    private void submitToServer() {

        final RegistrationModel registrationModel = new RegistrationModel();

        registrationModel.setUserName(user);
        // registrationModel.setOtpCode(etNidNum.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.OTPVarified(registrationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");


                     /*   try{
                            String auth=response.body().getJwt().replace("{\"auth_token\":\"","");
                            String auth1=auth.replace("\"}","");
                            Utilities.showLogcatMessage(" "+auth1);
                            SharedPrefManager.getInstance(context).saveToken(auth1);
                            SharedPrefManager.getInstance(context).saveotp(response.body().getOtpCode());
                            SharedPrefManager.getInstance(context).saveotp(response.body().getUserInfo().getUserName());
                        }
                        catch (Exception e) {
                            Utilities.showLogcatMessage("Exception 1"+e.toString());
                            Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                        }*/

                        Toast.makeText(context, "Successfully Registered in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(context, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
