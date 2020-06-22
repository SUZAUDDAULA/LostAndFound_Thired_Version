package com.opus_bd.lostandfound.GeneralPeople;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.opus_bd.lostandfound.Fragments.Registration.CitizenFragment;
import com.opus_bd.lostandfound.Fragments.Registration.AddressVerificationFragment;
import com.opus_bd.lostandfound.Fragments.Registration.CameraFragment;
import com.opus_bd.lostandfound.Fragments.Registration.IdFragment;
import com.opus_bd.lostandfound.Fragments.Registration.InputFragment;
import com.opus_bd.lostandfound.Fragments.Registration.OTPFragment;
import com.opus_bd.lostandfound.Fragments.Registration.RegFragment;
import com.opus_bd.lostandfound.Activity.LoginActivity;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private static final int MAX_STEP = 6;
    private int current_step = 1;
    public static int id,inputid;


    @BindView(R.id.viewPager)
   public ViewPager viewPager;
    InkPageIndicator mIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        getAllList();
        setupViewPager(viewPager);
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);
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
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CitizenFragment(), "1");
        adapter.addFragment(new IdFragment(), "2");
        adapter.addFragment(new InputFragment(), "3");
        adapter.addFragment(new CameraFragment(), "4");
        adapter.addFragment(new AddressVerificationFragment(), "5");
        adapter.addFragment(new RegFragment(getApplicationContext()), "6");
        adapter.addFragment(new OTPFragment(getApplicationContext()), "7");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.viewPager, fragment);
        fragmentTransaction.commit();
      /*  toolbar.setTitle(title);
        backPressed = false;*/
    }


    public void selectPage(int page) {
        viewPager.setCurrentItem(page);
    }
    public void setId(int id1) {
        id = id1;
    }

  /*  public void setId(int id) {
        this.id = id;
    }

    public void setInputid(int inputid) {
        this.inputid = inputid;
    }*/
/*
    public int getId() {
        return id;
    }*/

    /*public int getInputid() {
        return inputid;
    }*/

    /*private void initComponent() {
        status = (TextView) findViewById(R.id.status);

        ((LinearLayout) findViewById(R.id.lyt_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backStep(current_step);
                bottomProgressDots(current_step);
            }
        });

        ((LinearLayout) findViewById(R.id.lyt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep(current_step);
                bottomProgressDots(current_step);
            }
        });

        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        status.setText(str_progress);
        bottomProgressDots(current_step);
    }

    private void nextStep(int progress) {
        if (progress < MAX_STEP) {
            progress++;
            current_step = progress;
            ViewAnimation.fadeOutIn(status);
        }
        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        status.setText(str_progress);
    }

    private void backStep(int progress) {
        if (progress > 1) {
            progress--;
            current_step = progress;
            ViewAnimation.fadeOutIn(status);
        }
        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        status.setText(str_progress);
    }

    private void bottomProgressDots(int current_index) {
        current_index--;
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }*/


    public void getAllList() {
        Utilities.showLogcatMessage("Responce");
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        //  Call<LoginResponce> registrationRequest = retrofitService.GetFinishFabricReceivedList(userModel);
        String token = SharedPrefManager.getInstance(RegistrationActivity.this).getToken();

        if (token != null) {
            Call<List<NationalIdentityTypesModel>> registrationRequest = retrofitService.GetNationalIdentityTypes();

            try {
                registrationRequest.enqueue(new Callback<List<NationalIdentityTypesModel>>() {
                    @Override
                    public void onResponse(Call<List<NationalIdentityTypesModel>> call, @NonNull Response<List<NationalIdentityTypesModel>> response) {

                        Utilities.showLogcatMessage(" Response 1"+response);
                        if (response.body() != null) {
                            for(int i=0;i<response.body().size();i++){
                                Utilities.showLogcatMessage(" Response "+response.body().get(i).getNationalIdentityName());
                            }


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
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
