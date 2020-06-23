package com.opus_bd.lostandfound.Activity.DASHBOARD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Activity.ENRTY.FoundAndRecoveredDetailsActicity;
import com.opus_bd.lostandfound.Activity.ENRTY.VehicleEntryActivity;
import com.opus_bd.lostandfound.Adapter.NewsFeedAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_wise_news_feed);
        ButterKnife.bind(this);
        tvVehicleCategoryTitle.setText(Constants.ENTRY_TYPE_Name+" "+Constants.VEHICLE_TYPE_NAME+" List");
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
        Utilities.showLogcatMessage(" token " + token);
        Utilities.showLogcatMessage(" UserName " + UserName);
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<NewsFeedViewModel>> listCall = retrofitService.GetALLNewFeedsInfo(SharedPrefManager.BEARER
                + token, UserName,Constants.VEHICLE_TYPE_ID);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FoundAndRecoveredDetailsActicity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}