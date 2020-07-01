package com.opus_bd.lostandfound.Activity.DASHBOARD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.image_description)
    TextView image_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            String imageUrl = "";//getIntent().getStringExtra("image_url");
            //Bitmap imageUrl = (Bitmap) getIntent().getParcelableExtra("image_url");
//            Uri myUri = Uri.parse(getIntent().getStringExtra("imageUri"));
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
    }


    private void setImage(String imageUrl, String imageName){
        image_description.setText(imageName);
//        try {
//            ByteArrayOutputStream baoss = new ByteArrayOutputStream();
//            byte[] imageBytess = baoss.toByteArray();
//            imageBytess = Base64.decode(imageUrl, Base64.DEFAULT);
//            Bitmap di = BitmapFactory.decodeByteArray(imageBytess, 0, imageBytess.length);
//            image.setImageBitmap(di);
////        Glide.with(this)
////                .asBitmap()
////                .load(imageUrl)
////                .into(image);
//        }catch (Exception e){
//            Utilities.showLogcatMessage("ImageException " + e.toString());
//        }

    }

}