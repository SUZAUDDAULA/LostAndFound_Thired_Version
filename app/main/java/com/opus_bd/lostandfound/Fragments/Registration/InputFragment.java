package com.opus_bd.lostandfound.Fragments.Registration;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.opus_bd.lostandfound.GeneralPeople.RegistrationActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {
    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.etbinNum)
    EditText etbinNum;
    @BindView(R.id.etPassNum)
    EditText etPassNum;
    @BindView(R.id.etExpary)
    EditText etExpary;

    @BindView(R.id.rrNid)
    RelativeLayout tNidNum;
    @BindView(R.id.rrbinNum)
    RelativeLayout tbinNum;
    @BindView(R.id.rrPassNum)
    RelativeLayout tPassNum;
    @BindView(R.id.rrExpary)
    RelativeLayout tExpary;
    @BindView(R.id.rrEmail)
    RelativeLayout rrEmail;
    @BindView(R.id.rrCountryCode)
    RelativeLayout rrCountryCode;

    int inputID, passId, view;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    public void setInputID(int inputID) {
        this.inputID = inputID;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step = 3;

        EventBus.getDefault().post(new MessageEvent(true));
        Utilities.showLogcatMessage(" inputId 1" + RegistrationActivity.inputid);
        setView(inputID, passId);
        Utilities.showLogcatMessage("passid 2" + passId);
        initializeVariables();
        return v;
    }

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
       // etExpary.setText(formatter.format(calendar.getTime()));
    }

    @OnClick(R.id.etExpary)
    public void etDateOnClick() {
        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int
                            selectedYear, int selectedMonth, int selectedDay) {
                        Calendar saleDateCalender = Calendar.getInstance();
                        saleDateCalender.set(Calendar.YEAR, selectedYear);
                        saleDateCalender.set(Calendar.MONTH, selectedMonth);
                        saleDateCalender.set(Calendar.DAY_OF_MONTH, selectedDay);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        etExpary.setText(formatter.format(saleDateCalender.getTime()));
                    }
                }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    }

    public void setView(int i, int pass) {
        if (i == 1 && pass == 1) {
            tNidNum.setVisibility(View.VISIBLE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
            view = 1;
        } else if (i == 2 & pass == 1) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.VISIBLE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
            view = 2;
        } else if (i == 3 & pass == 1) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.VISIBLE);
            tExpary.setVisibility(View.VISIBLE);
            view = 3;
        } else if (i == 3 && pass == 2) {
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.VISIBLE);
            tExpary.setVisibility(View.VISIBLE);
            rrEmail.setVisibility(View.VISIBLE);
            rrCountryCode.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.textNext)
    public void btnLogIn() {
        /*IdFragment idFragment=new IdFragment();
        idFragment.setId(1);*/
        //((RegistrationActivity) getActivity()).selectPage(3);

        if (view == 1) {
            RegistrationProcessActivity.NationalIdentityNo = etNidNum.getText().toString();
        } else if (view == 2) {
            RegistrationProcessActivity.NationalIdentityNo = etbinNum.getText().toString();
        } else if (view == 3) {
            RegistrationProcessActivity.NationalIdentityNo = etPassNum.getText().toString();
        }


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, new CameraFragment(), "NewFragmentTag");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage(" inputid " + RegistrationActivity.inputid);

        }
    }
}
