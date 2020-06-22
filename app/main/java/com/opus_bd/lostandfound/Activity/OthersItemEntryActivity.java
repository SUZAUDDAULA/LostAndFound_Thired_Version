package com.opus_bd.lostandfound.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.opus_bd.lostandfound.Adapter.Documentation.OthersItemListAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
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

public class OthersItemEntryActivity extends AppCompatActivity {
    @BindView(R.id.rvItem)
    RecyclerView rvItem;

    OthersItemListAdapter othersItemListAdapter;
    private ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
    Boolean languageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_item_entry);
        ButterKnife.bind(this);
        languageStatus = getSharedPrefValue();
        intRecyclerView();
        getAllOtherItemList();
    }

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

    public void intRecyclerView() {
        othersItemListAdapter = new OthersItemListAdapter(documentTypeArrayList, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvItem.setLayoutManager(layoutManager);
        rvItem.setAdapter(othersItemListAdapter);
    }


    public void getAllOtherItemList() {
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        Call<List<DocumentType>> registrationRequest = retrofitService.GetAllDocumentType();

        try {
            registrationRequest.enqueue(new Callback<List<DocumentType>>() {
                @Override
                public void onResponse(Call<List<DocumentType>> call, @NonNull Response<List<DocumentType>> response) {
                    if (response.body() != null) {
                        documentTypeArrayList.clear();
                        documentTypeArrayList.addAll(response.body());
                        othersItemListAdapter.notifyDataSetChanged();
                    } else {
                       /* Toasty.error(DocumentTypeActivity.this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DocumentTypeActivity.this, List2Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);*/

                        Utilities.showLogcatMessage(" No Items");
                    }
                }

                @Override
                public void onFailure(Call<List<DocumentType>> call, Throwable t) {
                    Utilities.showLogcatMessage("error " + t.toString());
                }
            });

        } catch (Exception e) {
        }

    }


    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InformationEntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }


    //DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}

