package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Activity.DASHBOARD.ImageViewActivity;
import com.opus_bd.lostandfound.Activity.DASHBOARD.ItemWiseNewsFeedActivity;
import com.opus_bd.lostandfound.Activity.DASHBOARD.UserProfileActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.BagActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.CardsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.CategoryListActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.CosmeticsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.DocumentActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.ElectronicsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.GarmentsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.GlassActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.JewelryActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.KeysInformationActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.MobilePhoneActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.MoneyActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.OtherItemDetailsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.PetActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.ShoesActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.UmbrellaActivity;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.Model.Vehichel.Likes;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.*;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.TransactionViewHolder> {
    private final Context context;
    private List<NewsFeedViewModel> items;
    String token;
    String UserName;

   /* public interface OnItemClickListener {
        void onItemClick( int position);

    }

    private OnItemClickListener mListener;*/

    public NewsFeedAdapter(List<NewsFeedViewModel> items, String token, String UserName, Context context) {
        this.items = items;
        this.token = token;
        this.UserName = UserName;
        this.context = context;
    }

    @Override
    public NewsFeedAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_gd_information_list, parent, false);

        return new NewsFeedAdapter.TransactionViewHolder(v);
    }


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

    public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        @BindView(R.id.totalLike)
        TextView totalLike;
        @BindView(R.id.liketext)
        TextView liketext;
        @BindView(R.id.likeimg)
        ImageView likeimg;

        @BindView(R.id.llLike)
        LinearLayout llLike;


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
                totalLike.setText(String.valueOf(item.getTotalLikes()));
                llLike.setId(item.getVehicleId());
                //llLike.setClickable(true);
                llLike.setOnClickListener(this);
/*                if (item.getStatusId() == 1){
                    likeimg.setImageResource(R.drawable.like_done);
                }*/

                //Glide.with(context).load("http://103.134.88.13:1022/"+item.getAttachImage()).into(iv_Sub_pic);
                try {
                    Glide.with(context).load("http://103.134.88.13:1022/"+item.getProfilePic()).into(user_prifile_pic);
                }catch (Exception e){}

                iv_Sub_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ImageViewActivity.class);
                        intent.putExtra("image_url",item.getEncodedImage() );
                        intent.putExtra("image_name", item.getVehicleDescription());
                        context.startActivity(intent);
                    }
                });
                //Utilities.showLogcatMessage("ImageInfo"+item.getDocumentDescription());
                //Utilities.showLogcatMessage("TotalLike " + item.getTotalLikes());

            } catch (Exception e) {
                Utilities.showLogcatMessage(" Ex date" + e.toString());
            }


        }


        Integer vehicleId;

        @Override
        public void onClick(View v) {
            Utilities.showLogcatMessage("Clicked ID : " + v.getId() + " Username : " + UserName);
            vehicleId = v.getId();

            likesSubmitToServer();
        }

        public void likesSubmitToServer() {

            final Likes model = new Likes ();

            model.setUserName(UserName);
            model.setVehicleId(vehicleId);

            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<String> registrationRequest = retrofitService.SaveLikes(SharedPrefManager.BEARER + token, model);
            registrationRequest.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        if (response.body() == "1") {
                            Utilities.showLogcatMessage("like response : " + response.body());
                            Integer currentTotalLike = Integer.valueOf(liketext.getText().toString());
                            Integer updateTotalLike = (currentTotalLike + 1);
                            liketext.setText(updateTotalLike.toString());

                        } else {
                            Utilities.showLogcatMessage("like response" + response.body());
                            Integer currentTotalLike = Integer.valueOf(liketext.getText().toString());
                            Integer updateTotalLike = (currentTotalLike - 1);
                            liketext.setText(updateTotalLike.toString());
                        }
                    } catch (Exception e) {
                        Utilities.showLogcatMessage("Exception 2" + e.toString());
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Utilities.showLogcatMessage("Fail to connect " + t.toString());
                    //progress.dismiss();
                    //Toast.makeText(ItemWiseNewsFeedActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

                }
            });




        }
    }


}
