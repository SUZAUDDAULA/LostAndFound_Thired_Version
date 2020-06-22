package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Activity.DASHBOARD.GDInformationDetailsActivity;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GDInformationAdapter extends RecyclerView.Adapter<GDInformationAdapter.TransactionViewHolder> {
    private final Context context;
    public String Language, english, bangla;
    private List<GDInformation> items;

    public GDInformationAdapter(List<GDInformation> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_gd_information_list, parent, false);
//        Boolean languageStatus = getSharedPrefValue();
//        english = "en";
//        bangla = "bn";
//        if (languageStatus) {
//            Language = english;
//        } else {
//            Language = bangla;
//        }
        return new TransactionViewHolder(v);
    }

//    private boolean getSharedPrefValue() {
//        SharedPreferences tprefs = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        return tprefs.getBoolean(KEY_State, true);
//    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        GDInformation item = items.get(position);
        holder.set(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvtime)
        TextView tvtime;
        @BindView(R.id.profile_Name)
        TextView profile_Name;
        @BindView(R.id.user_prifile_pic)
        ImageView user_prifile_pic;
        @BindView(R.id.iv_Sub_pic)
        ImageView iv_Sub_pic;


        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final GDInformation item) {
            //UI setting code
            try {
                profile_Name.setText(item.getGdFor());
                tvtime.setText(item.getDate());
                tvStatus.setText(item.getStatus());
                Glide.with(context).load("http://103.134.88.13:1022/"+item.getDocumentDescription()).into(iv_Sub_pic);
                Glide.with(context).load("http://103.134.88.13:1022/"+item.getGdNumber()).into(user_prifile_pic);
                //Utilities.showLogcatMessage("ImageInfo"+item.getDocumentDescription());

            } catch (Exception e) {
                Utilities.showLogcatMessage(" Ex date" + e.toString());
            }


        }


    }

}


