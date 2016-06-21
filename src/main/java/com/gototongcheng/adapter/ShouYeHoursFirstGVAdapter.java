package com.gototongcheng.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 16/6/21.
 */
public class ShouYeHoursFirstGVAdapter extends BaseAdapter{

    private List<String> dataList;
    private Activity activity ;
    private LayoutInflater inflater;
    private Widget widget;
    public ShouYeHoursFirstGVAdapter(Activity activity,List<String> dataList){
        this.activity = activity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(activity);
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
        widget = new Widget(activity);
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_parts_main_shouye_first_gridview_lly,null);
            convertView.setTag(widget);
        }else {
            widget = (Widget)convertView.getTag();
        }

        return convertView;
    }
    private class Widget{
        public Widget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.iv_gv_item)
        ImageView ivGvItem;
        @Bind(R.id.tv_gv_item)
        TextView tvGvItem;
        @Bind(R.id.iv_cheap_gv_item)
        ImageView ivCheapGvItem;
    }

}
