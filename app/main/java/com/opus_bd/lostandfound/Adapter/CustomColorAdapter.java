package com.opus_bd.lostandfound.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

public class CustomColorAdapter extends BaseAdapter {
    Context context;
  List<String> ColorList;
  List<String> ColorCode;
    LayoutInflater inflter;

    public CustomColorAdapter(Context context, List<String> iconList, List<String> names) {
        this.context = context;
        this.ColorList = iconList;
        this.ColorCode = names;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return ColorList.size();
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
        view = inflter.inflate(R.layout.custom_color_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        if(i>0){
            icon.setVisibility(View.VISIBLE);
            try{
                icon.setBackgroundColor(Color.parseColor(ColorCode.get(i)));
            }
            catch (Exception e){
                Utilities.showLogcatMessage("setBackgroundColor "+e.toString());
            }
        }
        else {
            icon.setVisibility(View.GONE);
        }

        names.setText(ColorList.get(i));
        return view;
    }
}