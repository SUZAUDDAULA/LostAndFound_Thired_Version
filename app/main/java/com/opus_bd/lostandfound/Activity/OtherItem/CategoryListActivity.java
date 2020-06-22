package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryListActivity extends AppCompatActivity {
    @BindView(R.id.ivItemLogo)
    ImageView ivItemLogo;
    @BindView(R.id.ivItemLogo2)
    ImageView ivItemLogo2;
    @BindView(R.id.ivItemLogo3)
    ImageView ivItemLogo3;
    @BindView(R.id.ivItemLogo4)
    ImageView ivItemLogo4;
    @BindView(R.id.ivItemLogo5)
    ImageView ivItemLogo5;
    @BindView(R.id.ivItemLogo1)
    ImageView ivItemLogo1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.desktop).into(ivItemLogo);
        Glide.with(this).load(R.drawable.laptop).into(ivItemLogo1);
        Glide.with(this).load(R.drawable.hybride).into(ivItemLogo2);
        Glide.with(this).load(R.drawable.computer_exesories).into(ivItemLogo3);
        Glide.with(this).load(R.drawable.notebook).into(ivItemLogo4);
        Glide.with(this).load(R.drawable.omnicomputer).into(ivItemLogo5);
    }

    @OnClick({R.id.llRoot, R.id.llRoot1})
    public void llRoot() {
        Constants.COMPUTER_TYPE_ID=Constants.COMOthers;
        Intent intent = new Intent(this, ComputerActivity.class);
        startActivity(intent);
    }@OnClick(R.id.llRoot3)
    public void llRoot3() {
        Constants.COMPUTER_TYPE_ID=Constants.COMACCESORIES;
        Intent intent = new Intent(this, ComputerActivity.class);
        startActivity(intent);
    }
}
