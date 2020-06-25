package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Activity.DASHBOARD.ItemWiseNewsFeedActivity;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.TransactionViewHolder> {
    private final Context context;
    public String Language, english, bangla;
    private List<NewsFeedViewModel> items;

    @BindView(R.id.totalLike)
    TextView totalLike;

    public NewsFeedAdapter(List<NewsFeedViewModel> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @OnClick({R.id.llLike,R.id.likeimg, R.id.liketext})
    public void likeCount() {

        Toast.makeText(new ItemWiseNewsFeedActivity(), "Clicked", Toast.LENGTH_LONG).show();
        int likes = Integer.parseInt(totalLike.getText().toString());
        Toast.makeText(new ItemWiseNewsFeedActivity(), likes, Toast.LENGTH_LONG).show();
        totalLike.setText(likes + 1);
    }

    @Override
    public NewsFeedAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent,
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
        return new NewsFeedAdapter.TransactionViewHolder(v);
    }

//    private boolean getSharedPrefValue() {
//        SharedPreferences tprefs = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        return tprefs.getBoolean(KEY_State, true);
//    }

    @Override
    public void onBindViewHolder(NewsFeedAdapter.TransactionViewHolder holder, int position) {
        NewsFeedViewModel item = items.get(position);
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

        public void set(final NewsFeedViewModel item) {
            //UI setting code


            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                //decode base64 string to image
                imageBytes = Base64.decode(item.getEncodedImage(), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                profile_Name.setText(item.getFullName());
                tvtime.setText(item.getGdDate());
                tvStatus.setText(item.getVehicleDescription());
                iv_Sub_pic.setImageBitmap(decodedImage);
                //Glide.with(context).load("http://103.134.88.13:1022/"+item.getAttachImage()).into(iv_Sub_pic);
                Glide.with(context).load("http://103.134.88.13:1022/"+item.getProfilePic()).into(user_prifile_pic);
                //Utilities.showLogcatMessage("ImageInfo"+item.getDocumentDescription());

            } catch (Exception e) {
                Utilities.showLogcatMessage(" Ex date" + e.toString());
            }


        }


    }

}
