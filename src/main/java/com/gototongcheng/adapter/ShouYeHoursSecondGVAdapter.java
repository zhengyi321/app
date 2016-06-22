package com.gototongcheng.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gototongcheng.application.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 16/6/22.
 */
public class ShouYeHoursSecondGVAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> dataList;
    private LayoutInflater inflater;
    private Widget widget;
    public ShouYeHoursSecondGVAdapter(Activity activity,List<String> dataList){
        this.activity = activity;
        this.dataList = dataList;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_parts_shouye_hours_second_gv_lly,null);
            widget = new Widget(convertView);
            convertView.setTag(widget);
        }else {
            widget = (Widget)convertView.getTag();
        }
   //     widget.ivCheapGvItem.setVisibility(View.GONE);
        return convertView;
    }
    public class Widget{
        public Widget(View view){
            ButterKnife.bind(this,view);
        }

    }


}
