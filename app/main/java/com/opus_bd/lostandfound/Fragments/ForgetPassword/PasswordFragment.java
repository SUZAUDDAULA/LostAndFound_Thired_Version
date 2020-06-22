package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.ForgetPasswordActivity;
import com.opus_bd.lostandfound.Activity.LoginActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_password, container, false);
        ButterKnife.bind(this, v);    ForgetPasswordActivity.Step=3;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }

    @OnClick(R.id.textNext)
    public void btnLogIn() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        // submitToServer();
    }
}
