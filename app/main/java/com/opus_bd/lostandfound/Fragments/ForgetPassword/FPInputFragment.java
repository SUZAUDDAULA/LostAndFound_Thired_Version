package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.opus_bd.lostandfound.Activity.ForgetPasswordActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class FPInputFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_f_p_input, container, false);
        ButterKnife.bind(this, v);
        ForgetPasswordActivity.Step=2;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }
}
