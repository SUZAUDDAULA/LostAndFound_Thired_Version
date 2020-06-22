package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.opus_bd.lostandfound.R;

import java.util.List;

public class MobileOperatorAdapter extends BaseAdapter {
    Context context;
    int mobileOperatorImage[];
    List<String> mobileOperatorName;
    LayoutInflater inflter;

    public MobileOperatorAdapter(Context applicationContext, int[] mobileOperatorImage, List<String> mobileOperatorName) {
        this.context = applicationContext;
        this.mobileOperatorImage = mobileOperatorImage;
        this.mobileOperatorName = mobileOperatorName;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mobileOperatorImage.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(mobileOperatorImage[i]);
        names.setText(mobileOperatorName.get(i));
        return view;
    }
}