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
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeysInformationActivity extends AppCompatActivity {

    @BindView(R.id.spnKeyType)
    Spinner spnKeyType;

    @BindView(R.id.spnKeyColor)
    Spinner spnKeyColor;

    @BindView(R.id.etKeySpecification)
    EditText etKeySpecification;

    @BindView(R.id.etKeySize)
    EditText etKeySize;

    @BindView(R.id.etKeyQuantity)
    EditText etKeyQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keys_information);
    }

    @OnClick(R.id.btnWatchSubmit)
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

        model.setProductTypeId(spnKeyType.getSelectedItemPosition());
        model.setColorId(spnKeyColor.getSelectedItemPosition());
        // size  model.(Double.valueOf(etKeySize.getText().toString()));
        // etKeyQuantity model.(etKeyQuantity.getText().toString());
        model.setIdentificationMark(etKeySpecification.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        Call<String> registrationRequest = retrofitService.SaveComputerInfo(SharedPrefManager.BEARER+token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(KeysInformationActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        CodeGenerateActivity.code = response.body().toString();
                        Intent intent = new Intent(KeysInformationActivity.this, CodeGenerateActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(KeysInformationActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(KeysInformationActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(KeysInformationActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
