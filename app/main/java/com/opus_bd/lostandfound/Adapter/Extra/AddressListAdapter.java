package com.opus_bd.lostandfound.Adapter.Extra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.opus_bd.lostandfound.Model.ExtraModel.AdreessList;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.TransactionViewHolder> {
    private final Context context;
    private List<AdreessList> items;

    public AddressListAdapter(List<AdreessList> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address_list, parent, false);
        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        AdreessList item = items.get(position);
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

        @BindView(R.id.tvRPersonAddress)
        TextView tvRPersonAddress;
        @BindView(R.id.tvccp)
        TextView tvccp;
        @BindView(R.id.tvSPDistrict)
        TextView tvSPDistrict;
        @BindView(R.id.tvSPThana)
        TextView tvSPThana;
        @BindView(R.id.tvVillage)
        TextView tvVillage;
        @BindView(R.id.tvAddressDetails)
        TextView tvAddressDetails;
        @BindView(R.id.tvAddressType)
        TextView tvAddressType;
        @BindView(R.id.tvOneLineAddress)
        TextView tvOneLineAddress;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final AdreessList item) {
            //UI setting code

           tvRPersonAddress.setText(String.valueOf(item.getItemId()));
            tvccp.setText(item.getCountryName());
            tvSPDistrict.setText(item.getDistrictName());
            tvSPThana.setText(item.getThanaName());
            tvVillage.setText(item.getVillageName());
            tvAddressDetails.setText(item.getAddressName());
            tvAddressType.setText(item.getAddressType());
            tvOneLineAddress.setText(item.getOneLineAddress());


        }


    }


}
