package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NationalIdentityListAdapter extends RecyclerView.Adapter<NationalIdentityListAdapter.TransactionViewHolder> {
    private final Context context;
    private List<NationalIdentityTypesModel> items;

    public NationalIdentityListAdapter(List<NationalIdentityTypesModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_national_identity, parent, false);
        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        NationalIdentityTypesModel item = items.get(position);
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

        @BindView(R.id.tvIdentityName)
        TextView tvIdentityName;



        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final NationalIdentityTypesModel item) {
            //UI setting code

            tvIdentityName.setText(item.getNationalIdentityName());


            final int id = item.getId();

            Utilities.showLogcatMessage("id" + id);
           /* rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  *//*  Intent intent = new Intent(context, CurrentFebricDetailsActivity.class);
                    intent.putExtra("Item getId", id);
                    Utilities.showLogcatMessage(" Item Id " + id);
                    context.startActivity(intent);*//*
                }
            });*/


        }


    }


}
