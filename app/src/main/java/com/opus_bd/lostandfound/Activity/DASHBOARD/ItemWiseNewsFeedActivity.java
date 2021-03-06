package com.opus_bd.lostandfound.Activity.DASHBOARD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.opus_bd.lostandfound.Activity.ENRTY.FoundAndRecoveredDetailsActicity;
import com.opus_bd.lostandfound.Activity.ENRTY.VehicleEntryActivity;
import com.opus_bd.lostandfound.Adapter.NewsFeedAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.Model.Vehichel.Likes;
import com.opus_bd.lostandfound.Model.Vehichel.VehiclePostModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
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

public class ItemWiseNewsFeedActivity extends AppCompatActivity {
    @BindView(R.id.rvGDInfoItem)
    RecyclerView rvGDInfoItem;
    @BindView(R.id.tvVehicleCategoryTitle)
    TextView tvVehicleCategoryTitle;


    NewsFeedAdapter newsFeedAdapter;
    ArrayList<NewsFeedViewModel> newsFeedViewModelArrayList = new ArrayList<>();

    @BindView(R.id.user_prifile_pic)
    ImageView user_prifile_pic;

    @BindView(R.id.profile_Name)
    TextView profile_Name;

    /*@BindView(R.id.totalLike)
    TextView totalLike;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_wise_news_feed);
        ButterKnife.bind(this);
        tvVehicleCategoryTitle.setText(Constants.ENTRY_TYPE_Name+" "+Constants.VEHICLE_TYPE_NAME+" List");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        String profileName = SharedPrefManager.getInstance(this).getProfileName();
        String imageUrl = SharedPrefManager.getInstance(this).getImageUrl();
        String userRoles = SharedPrefManager.getInstance(this).getUserRoles();

        profile_Name.setText(profileName);
        if(imageUrl==""){
            user_prifile_pic.setImageResource(R.drawable.ic_human_db);
        }else {
            Glide.with(this).load(imageUrl).circleCrop().into(user_prifile_pic);
        }
/*        Toast.makeText(getApplicationContext(), userRoles, Toast.LENGTH_LONG).show();
        Utilities.showLogcatMessage("Roles : " + userRoles);*/

        if (Integer.parseInt(userRoles.trim()) == 2){
            findViewById(R.id.btnPost).setVisibility(View.VISIBLE);
        }

        getAllNewsFeedInfo();
        intRecyclerView();

    }

    @OnClick(R.id.btnPost)
    public void btnPost() {
        Constants.VEHICLE_TYPE_ID=Constants.VEHICLE_TYPE_ID;
        Constants.ENTRY_TYPE_Name=Constants.ENTRY_TYPE_Name;
        Constants.VEHICLE_TYPE_NAME=Constants.VEHICLE_TYPE_NAME;
        Intent intent = new Intent(this, VehicleEntryActivity.class);
        startActivity(intent);
    }

    public void intRecyclerView() {
        newsFeedAdapter = new NewsFeedAdapter(newsFeedViewModelArrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        rvGDInfoItem.setLayoutManager(layoutManager);
        rvGDInfoItem.setAdapter(newsFeedAdapter);
    }

    public void getAllNewsFeedInfo() {
        //progress.show();
        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<NewsFeedViewModel>> listCall = retrofitService.GetALLNewFeedsInfo(SharedPrefManager.BEARER
                + token, UserName,Constants.GD_TYPE_ID,Constants.VEHICLE_TYPE_ID);
        listCall.enqueue(new Callback<List<NewsFeedViewModel>>() {
            @Override
            public void onResponse(Call<List<NewsFeedViewModel>> call, Response<List<NewsFeedViewModel>> response) {
                Utilities.showLogcatMessage(" r " + response.body());
                if (response.body() != null) {
                    //progress.dismiss();
                    Utilities.showLogcatMessage("gdin" + response.body().size());
                    newsFeedViewModelArrayList.clear();
                    newsFeedViewModelArrayList.addAll(response.body());

                    newsFeedAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<NewsFeedViewModel>> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(ItemWiseNewsFeedActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.ivappLogo)
    public void ivappLogo() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.profile_Name,R.id.user_prifile_pic})
    public void profile_Name() {
        Intent intent = new Intent(ItemWiseNewsFeedActivity.this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FoundAndRecoveredDetailsActicity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

//    public void likeOnclick(View view) {
//
//        Toast.makeText(getApplicationContext(), "Total Likes will be show here " , Toast.LENGTH_LONG).show();
//        likesSubmitToServer();
//
//    }

    private void likesSubmitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final Likes model = new Likes ();

        model.setUserName(UserName);
        model.setVehicleId(26);
        model.setStatusId(1);

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveLikes(SharedPrefManager.BEARER + token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(ItemWiseNewsFeedActivity.this, response.body(), Toast.LENGTH_LONG).show();
                try {
                    if (response.body() == "1" || response.body() == "0") {
                        Utilities.showLogcatMessage("response" + response.body());
                        //progress.dismiss();
                        Toast.makeText(ItemWiseNewsFeedActivity.this, "Likes Done!", Toast.LENGTH_SHORT).show();

                        /*Intent intent = new Intent(ItemWiseNewsFeedActivity.this, ItemWiseNewsFeedActivity.class);
                        startActivity(intent);
                        finish();*/

                    } else {
                        Toast.makeText(ItemWiseNewsFeedActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    //progress.dismiss();
                    Toast.makeText(ItemWiseNewsFeedActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                //progress.dismiss();
                Toast.makeText(ItemWiseNewsFeedActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}