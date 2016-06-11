package com.gototongcheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gototongcheng.application.R;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;

import java.util.List;

/**
 * Created by zhyan on 16/6/11.
 */
public class MainShouYeFirstGVAdapter extends BaseAdapter{

    private List<MainShouYeFirstGridViewModel> mainShouYeFirstGridViewModelList;
    private Context mContext;
    private LayoutInflater inflater;
    public MainShouYeFirstGVAdapter(Context context, List<MainShouYeFirstGridViewModel> modelList){
        this.mContext = context;
        this.mainShouYeFirstGridViewModelList = modelList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mainShouYeFirstGridViewModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mainShouYeFirstGridViewModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Widget widget = new Widget();
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_parts_main_shouye_first_gridview_lly,null);
            widget.ivGVItem = (ImageView)convertView.findViewById(R.id.iv_gv_item);
            widget.tvGVItem = (TextView)convertView.findViewById(R.id.tv_gv_item);
            convertView.setTag(widget);
        }else {
            widget = (Widget)convertView.getTag();
        }
        MainShouYeFirstGridViewModel model = mainShouYeFirstGridViewModelList.get(position);
        Glide.with(mContext).load(model.getPic()).centerCrop().into(widget.ivGVItem);
        widget.tvGVItem.setText(model.getName());
  //      CommonGVItem newItem = (CommonGVItem)getItem(position);
        return convertView;
    }
    private class Widget{
        public ImageView ivGVItem;
        public TextView tvGVItem;
    }
}
