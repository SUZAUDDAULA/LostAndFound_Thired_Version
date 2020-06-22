package com.opus_bd.lostandfound.GeneralPeople;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.opus_bd.lostandfound.Adapter.NationalIdentityListAdapter;
import com.opus_bd.lostandfound.Activity.LoginActivity;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationSearchActivity extends AppCompatActivity {
@BindView(R.id.rvNationalIdentity)
    RecyclerView rvNationalIdentity;
ArrayList<NationalIdentityTypesModel> nationalIdentityTypesModelArrayList=new ArrayList<>();
NationalIdentityListAdapter nationalIdentityListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_search);
        ButterKnife.bind(this);
        getAllList();
        iniviews();
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
    public void iniviews()
    {
        nationalIdentityListAdapter=new NationalIdentityListAdapter(nationalIdentityTypesModelArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rvNationalIdentity.setLayoutManager(layoutManager);
        rvNationalIdentity.setAdapter(nationalIdentityListAdapter);
    }
   /* @OnClick(R.id.button1)
    public void button1() {
        Intent intent = new Intent(InformationSearchActivity.this, MobileSearchActivity.class);

        startActivity(intent);
    }
    @OnClick(R.id.button2)
    public void button2() {
        Intent intent = new Intent(InformationSearchActivity.this, vehicleActivity.class);

        startActivity(intent);
    }*/

    public void getAllList() {
        Utilities.showLogcatMessage("Responce");
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        //  Call<LoginResponce> registrationRequest = retrofitService.GetFinishFabricReceivedList(userModel);
        String token = SharedPrefManager.getInstance(InformationSearchActivity.this).getToken();

        if (token != null) {
            Call<List<NationalIdentityTypesModel>> registrationRequest = retrofitService.GetNationalIdentityTypes();

            try {
                registrationRequest.enqueue(new Callback<List<NationalIdentityTypesModel>>() {
                    @Override
                    public void onResponse(Call<List<NationalIdentityTypesModel>> call, @NonNull Response<List<NationalIdentityTypesModel>> response) {

                        Utilities.showLogcatMessage(" Response 1"+response);
                        if (response.body() != null) {
                           /* for(int i=0;i<response.body().size();i++){
                                Utilities.showLogcatMessage(" Response "+response.body().get(i).getNationalIdentityName());
                            }*/
                            Utilities.showLogcatMessage(" Response 1"+response.body());
                           nationalIdentityTypesModelArrayList.clear();

                            nationalIdentityTypesModelArrayList.addAll(response.body());
                            nationalIdentityListAdapter.notifyDataSetChanged();


                        } else {
                            //Toasty.error(RegistrationActivity.this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
                           /* Intent intent = new Intent(RegistrationActivity.this, List2Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);*/
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NationalIdentityTypesModel>> call, Throwable t) {
                        Utilities.showLogcatMessage("error " + t.toString());
                    }
                });

            } catch (Exception e) {
            }
        } else {
            // Toasty.error(RegistrationActivity.this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InformationSearchActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
