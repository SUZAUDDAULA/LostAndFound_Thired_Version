package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.opus_bd.lostandfound.Activity.SEARCH.VehicleDetailsViewActivity;
import com.opus_bd.lostandfound.Model.VehicleSearch.VehicleSearchListModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleSearchInformationAdapter extends RecyclerView.Adapter<VehicleSearchInformationAdapter.TransactionViewHolder> {
    private final Context context;
    public String Language, english, bangla;
    private List<VehicleSearchListModel> items;

    public VehicleSearchInformationAdapter(List<VehicleSearchListModel> items, Context context) {
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
        VehicleSearchListModel item = items.get(position);
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

        @BindView(R.id.tvGDNumber)
        TextView tvGDNumber;
        @BindView(R.id.tvGDDate)
        TextView tvGDDate;
        @BindView(R.id.tvGDStatus)
        TextView tvGDStatus;
//        @BindView(R.id.ivDetails)
//        ImageView ivDetails;
        @BindView(R.id.v1)
        View v1;


        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final VehicleSearchListModel item) {
            //UI setting code
            tvGDStatus.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            tvGDNumber.setText(item.getGDInformation().getGdNumber());

            try {
                String date = item.getGDInformation().getGdDate();
                String[] date1 = date.split("T");
                Utilities.showLogcatMessage(" date " + date1[0]);
                tvGDDate.setText(Utilities.getDateFormate(date1[0]));
            } catch (Exception e) {
                Utilities.showLogcatMessage(" Ex date" + e.toString());
            }
          /*  try {
                if (item.getStatusId() == 1) {
                    tvGDStatus.setText(R.string.allegation);
                } else if (item.getStatusId() == 2) {
                    tvGDStatus.setText(R.string.investigation);
                } else if (item.getStatusId() == 3) {
                    tvGDStatus.setText(R.string.disposal);
                } else if (item.getStatusId() == 4) {
                    tvGDStatus.setText(R.string.Cancel);
                }
            } catch (Exception e) {
                Utilities.showLogcatMessage(" Ex" + e.toString());
            }*/

//            ivDetails.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String date = item.getMfcDate();
//                    String vdate = date.substring(0, Math.min(date.length(), 10));
//                    String lafvdate = "";
//                    Utilities.showLogcatMessage("spacetime= " + item.getSpaceAndTime());
//                    if (item.getSpaceAndTime().equals(null)) {
//                        lafvdate = "";
//                    } else {
//                        String date2 = item.getSpaceAndTime().getLafDate();
//                        lafvdate = date2.substring(0, Math.min(date2.length(), 10));
//                    }
//
//                    //String[] date3 = date.split("T");
//
//                    //VehicleSearchListModel vehicleSearchListModel  = item.get(i);
//
//                    Intent intent = new Intent(context, VehicleDetailsViewActivity.class);
//                    intent.putExtra("id", item.getGDInformation().getId());
//                    intent.putExtra("vehicleType", item.getVehicleType().getVehicleTypeName());
//                    intent.putExtra("vehicleBarnd", item.getMadeBy());
//                    /*  intent.putExtra("vehicleModel",item.getModelNo());*/
//                    intent.putExtra("vehicleRegNoName", item.getVehicleRegNo());
//                    intent.putExtra("vehicleEngineNo", item.getEngineNo());
//                    intent.putExtra("vehicleChesisNo", item.getChasisNo());
//                    intent.putExtra("vehicleCcNo", item.getCcNo());
//                    intent.putExtra("vehicleColor", item.getIndentifyInfo().getColors().getColorName());
//                    intent.putExtra("MadeIn", item.getMadeIn());
//                    intent.putExtra("tvMadeDate", Utilities.getDateFormate(vdate));
//                    intent.putExtra("tvIdentitySign", item.getIndentifyInfo().getIdentifySign());
//                    /*intent.putExtra("tvSPDistrict",item.gd);*/
//                    intent.putExtra("tvAddressDetails", item.getSpaceAndTime().getPlaceDetails());
//                    intent.putExtra("tvVehicleDate", Utilities.getDateFormate(lafvdate));
//                    intent.putExtra("tvVehicleTime", item.getSpaceAndTime().getLafTime());
//                    context.startActivity(intent);
//
//                }
//            });


        }


    }

}


