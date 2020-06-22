package com.opus_bd.lostandfound.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.opus_bd.lostandfound.GeneralPeople.RegistrationActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdFragment extends Fragment {
    int iid,passid;
@BindView(R.id.rl)
    RelativeLayout rl;@BindView(R.id.textView2)
    TextView textView2;
    public void setIid(int iid) {
        this.iid = iid;
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

        View v = inflater.inflate(R.layout.fragment_id, container, false);
        ButterKnife.bind(this, v);
        Utilities.showLogcatMessage(" id " + iid);
        RegistrationProcessActivity.Step = 2;

        EventBus.getDefault().post(new MessageEvent(true));
        setView(iid);
        return v;

    }

    public void setView(int i) {
        if (i == 1) {
            rl.setVisibility(View.VISIBLE);
            passid=1;

        } else if (i == 2) {
            rl.setVisibility(View.GONE);
            textView2.setText("Select below");
            passid=2;
        }
    }

    @OnClick({R.id.cvNID, R.id.textNext})
    public void button1() {
        InputFragment idFragment = new InputFragment();
        idFragment.setInputID(1);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/
RegistrationProcessActivity.NationalIdentityType="1";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvBID)
    public void button2() {
        InputFragment idFragment = new InputFragment();
        idFragment.setInputID(2);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/
        RegistrationProcessActivity.NationalIdentityType="2";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvpassport)
    public void button3() {
        InputFragment idFragment = new InputFragment();
        idFragment.setInputID(3);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        RegistrationProcessActivity.NationalIdentityType="3";
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage(" id " + RegistrationActivity.id);
        }
    }
}
