package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomSpinnerAdapter extends RecyclerView.Adapter<CustomSpinnerAdapter.TransactionViewHolder> {
        Context context;
        LayoutInflater inflter;
    private List<VehicleModel> items;
    public CustomSpinnerAdapter(List<VehicleModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CustomSpinnerAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_spinner, parent, false);
        return new CustomSpinnerAdapter.TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomSpinnerAdapter.TransactionViewHolder holder, int position) {
        VehicleModel item = items.get(position);
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

        @BindView(R.id.imageView)
        ImageView imageView;
       @BindView(R.id.textView)
        TextView textView;



        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final VehicleModel item) {
            //UI setting code

            textView.setText(item.getModelName());
            try{
                Glide.with(context).load("http://103.134.88.13:1022/"+item.getImagePath()).into(imageView);
            }
            catch (Exception e){}

        }


        }


    
    }
