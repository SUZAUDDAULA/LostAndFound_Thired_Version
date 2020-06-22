package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.Activity.ForgetPasswordActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FPOTPFragment extends Fragment {
    int inputID, passId, view;
    public void setInputID(int inputID) {
        this.inputID = inputID;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_o_t_p2, container, false);
        ButterKnife.bind(this, v);
        ForgetPasswordActivity.Step=2;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }

    @OnClick(R.id.textNext)
    public void button1() {
        PasswordFragment idFragment = new PasswordFragment();
        //DocumentSubmitFragment.setInputID(1);
      /*  idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);*/
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
}
