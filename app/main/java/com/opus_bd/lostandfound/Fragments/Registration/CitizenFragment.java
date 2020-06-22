package com.opus_bd.lostandfound.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CitizenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_citizen, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step=1;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }
      @OnClick({R.id.cvCitizen,R.id.textNext})
    public void btnLogIn() {
        IdFragment idFragment=new IdFragment();
        idFragment.setIid(1);
        RegistrationProcessActivity.citizen="Bangladeshi";
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

          FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                  R.animator.fragment_slide_left_exit);
          ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
          ft.commit();
    }
    @OnClick(R.id.cvFore)
    public void button2() {

        IdFragment idFragment=new IdFragment();
        idFragment.setIid(2);
        RegistrationProcessActivity.citizen="Foreigner";

        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    /*FragmentTransaction ft = getFragmentManager().beginTransaction();
ft.replace(R.id.details, new NewFragmentToReplace(), "NewFragmentTag");
ft.commit();*/
}
