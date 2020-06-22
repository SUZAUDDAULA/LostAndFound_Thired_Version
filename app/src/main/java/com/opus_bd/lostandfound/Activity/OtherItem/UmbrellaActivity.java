package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.opus_bd.lostandfound.Activity.ENRTY.CodeGenerateActivity;
import com.opus_bd.lostandfound.Model.OthersItem.ComputerInfo;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UmbrellaActivity extends AppCompatActivity {

    @BindView(R.id.spnUmbrellaType)
    Spinner spnUmbrellaType;

    @BindView(R.id.spnUmbrellaBrand)
    Spinner spnUmbrellaBrand;

    @BindView(R.id.spnModelType)
    Spinner spnModelType;

    @BindView(R.id.spnColor)
    Spinner spnColor;

    @BindView(R.id.etSize)
    EditText etSize;

    @BindView(R.id.etUmbrellaSpecification)
    EditText etUmbrellaSpecification;

    @BindView(R.id.etPrice)
    EditText etPrice;

    @BindView(R.id.etQuantity)
    EditText etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umbrella);
    }

    @OnClick(R.id.btnSubmit)
    public void Submit(){

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final ComputerInfo model = new ComputerInfo();

        //GD Information
        model.setUserName(UserName);
        model.setGdFor(Constants.GDFOR);
        model.setGDTypeId(Constants.ENTRY_TYPE_ID);
        model.setProductTypeId(Constants.PRODUCT_TYPE_ID);
        model.setDocumentDescription("");

        model.setProductTypeId(spnUmbrellaType.getSelectedItemPosition());
        model.setBrandTypeId(spnUmbrellaBrand.getSelectedItemPosition());
        model.setColorId(spnColor.getSelectedItemPosition());
        model.setModel(String.valueOf(spnModelType.getSelectedItemPosition()));

        // etSize model.(etSize.getText().toString());
        model.setPrice(Double.valueOf(etPrice.getText().toString()));
        model.setIdentificationMark(etUmbrellaSpecification.getText().toString());

        // etQuantity model.(etQuantity.getText().toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        Call<String> registrationRequest = retrofitService.SaveComputerInfo(SharedPrefManager.BEARER+token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(UmbrellaActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        CodeGenerateActivity.code = response.body().toString();
                        Intent intent = new Intent(UmbrellaActivity.this, CodeGenerateActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UmbrellaActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(UmbrellaActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(UmbrellaActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
