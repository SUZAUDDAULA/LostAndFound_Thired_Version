package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.opus_bd.lostandfound.Activity.LOGREG.ForgetPasswordActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DocumentSubmitFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();
    int iid,passid;
    public void setIid(int iid) {
        this.iid = iid;
    }
    @BindView(R.id.tvDocType)
    TextView tvDocType;
    @BindView(R.id.etDOB)
    EditText etDOB;

    @BindView(R.id.spnDocumentType)
    Spinner spnDocumentType;
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
        View v= inflater.inflate(R.layout.fragment_document_submit, container, false);
        ButterKnife.bind(this, v);
        ForgetPasswordActivity.Step=1;
        initializeVariables();
        EventBus.getDefault().post(new MessageEvent(true));
        setView(iid);
        return v;
    }

    public void setView(int i) {
     /*   if (i == 1) {
            rlfpd.setVisibility(View.VISIBLE);
            passid=1;

        } else if (i == 2) {
            rlfpd.setVisibility(View.GONE);
            textViewfpd.setText("Select below");
            passid=2;
        }*/
    }

    @OnClick({ R.id.textNext})
    public void button1() {
        FPOTPFragment idFragment = new FPOTPFragment();
        //DocumentSubmitFragment.setInputID(1);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/
        ForgetPasswordActivity.NationalIdentityType="1";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fpFregmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.tvDocType)
    public void tvDocType() {
        tvDocType.setVisibility(View.GONE);
        spnDocumentType.setVisibility(View.VISIBLE);


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
           /// Utilities.showLogcatMessage(" id " + ForgetPasswordActivity.id);
        }
    }

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        etDOB.setText(formatter.format(calendar.getTime()));

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception " + e.toString());
                }

            }
        });
    }

    //TIME AND dATE
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                //.context(this)
                .callback(DocumentSubmitFragment.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etDOB.setText(formatter.format(calendar.getTime()));
    }

}
