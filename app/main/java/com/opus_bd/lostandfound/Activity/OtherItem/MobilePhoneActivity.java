package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.opus_bd.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobilePhoneActivity extends AppCompatActivity {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;
    // Information View
    @BindView(R.id.mcvCardInformation)
    MaterialCardView mcvCardInformation;
    @BindView(R.id.mcvCardtIdendityInformation)
    MaterialCardView mcvCardtIdendityInformation;
    @BindView(R.id.mcvLostPlaceTimeInformation)
    MaterialCardView mcvLostPlaceTimeInformation;

    boolean isllCardInformationChecked = true;
    @BindView(R.id.llCardInformation)
    LinearLayout llCardInformation;

    @BindView(R.id.ivCardInformation)
    ImageView ivCardInformation;

    boolean isllCardtIdendityInformationChecked = true;
    @BindView(R.id.llCardtIdendityInformation)
    LinearLayout llCardtIdendityInformation;

    @BindView(R.id.ivCardtIdendityInformation)
    ImageView ivCardtIdendityInformation;

    boolean isllLostPlaceTimeInformationChecked = true;
    @BindView(R.id.llLostPlaceTimeInformation)
    LinearLayout llLostPlaceTimeInformation;

    @BindView(R.id.ivLostPlaceTimeInformation)
    ImageView ivLostPlaceTimeInformation;

@BindView(R.id.etIMENumber)
    EditText etIMENumber;

    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.trPhoneAccessories)
    TableRow trPhoneAccessories;

    @BindView(R.id.spnPhoneType)
    Spinner spnPhoneType;
    @BindView(R.id.spnPhoneAccessoriesType)
    Spinner spnPhoneAccessoriesType;
    String selectOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phone);
        ButterKnife.bind(this);

        mcvCardInformation.setVisibility(View.VISIBLE);
        mcvCardtIdendityInformation.setVisibility(View.GONE);
        mcvLostPlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        selectOne = getResources().getString(R.string.select_option);

        trPhoneAccessories.setVisibility(View.GONE);
        spnPhoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Phone Accessories"))
                {
                    trPhoneAccessories.setVisibility(View.VISIBLE);
                }else {
                    trPhoneAccessories.setVisibility(View.GONE);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        etIMENumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // if user is typing string one character at a time
                if (count == 1) {
                    // auto insert dashes while user typing their ssn
                    if (start == 2 || start == 6|| start == 10|| start == 14) {
                        etIMENumber.setText(etIMENumber.getText() + "-");
                        etIMENumber.setSelection(etIMENumber.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title

                picker.setListener(new CurrencyPickerListener() {
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        // Implement your code here
                        tvPrice.setText(code);
                        picker.dismiss();
                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
            }
        });
    }

    @OnClick(R.id.ivCardInformation)
    public void ivCardInformation() {
        if (isllCardInformationChecked) {
            // show password
            llCardInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivCardInformation);
            isllCardInformationChecked = false;
        } else {
            // hide password
            llCardInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardInformation);
            isllCardInformationChecked = true;
        }

    }

    @OnClick({R.id.ivCardtIdendityInformation, R.id.btnNext1})
    public void ivCardtIdendityInformation() {

        if (isllCardtIdendityInformationChecked) {
            // show password
            llCardInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardInformation);
            isllCardInformationChecked = true;

            mcvCardtIdendityInformation.setVisibility(View.VISIBLE);
            llCardtIdendityInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = false;

        } else {
            // hide password
            llCardtIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = true;
        }
    }



    @OnClick({R.id.ivLostPlaceTimeInformation, R.id.btnNext2})
    public void ivLostPlaceTimeInformation() {
        if (isllLostPlaceTimeInformationChecked) {
            // show password
            llCardtIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = true;

            mcvLostPlaceTimeInformation.setVisibility(View.VISIBLE);
            llLostPlaceTimeInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivLostPlaceTimeInformation);
            isllLostPlaceTimeInformationChecked = false;

        } else {
            // hide password
            llLostPlaceTimeInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivLostPlaceTimeInformation);
            isllLostPlaceTimeInformationChecked = true;
        }

    }


    @OnClick(R.id.btnNext3)
    public void ReportShown() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
       /*     tvProductType.setText(spnProductType.getSelectedItem().toString());
            tvProductDate.setText(etProductDate.getText().toString());
            tvModel.setText(etModel.getText().toString());
            //tvMadeBy.setText(spnMadeBy.getSelectedItem().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvRegNoName.setText(spnRegNoName1.getSelectedItem().toString() + " " + spnRegNoName2.getSelectedItem().toString()
                    + " " + etRegNoName.getText().toString());
            tvEngineNo.setText(etServiceTag.getText().toString());
            tvChesisNo.setText(etEMCProductID.getText().toString());
            tvCCNo.setText(etCCNo.getText().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvMadeDate.setText(etMadeDate.getText().toString());
            tvColor.setText(spnColor.getSelectedItem().toString());
            tvIdentitySign.setText(etIdentitySign.getText().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvProductDate.setText(etProductDate.getText().toString());
            tvProductTime.setText(etProductTime.getText().toString());*/
            //ivrPhoto.setImageResource(ivProductAttachment.getSourceLayoutResId());
            //Log.i("reportinfo", "engineno: " + etServiceTag.getText().toString() + ",chesisno: " + etEMCProductID.getText().toString() + ",date: " + etMadeDate.getText().toString() + "");
        } catch (Exception e) {
            Log.e("reportinfo", "btnNext4: ", e);
        }

    }
}
