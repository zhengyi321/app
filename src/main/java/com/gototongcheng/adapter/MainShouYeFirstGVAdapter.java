package com.gototongcheng.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gototongcheng.Presenter.MainActivityPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;
import com.gototongcheng.view.fragment.FoodsFragment;
import com.gototongcheng.view.fragment.HoursShopFragment;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.view.fragment.WaterBeerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyan on 16/6/11.
 */
public class MainShouYeFirstGVAdapter extends BaseAdapter {

    private MainShouYeFirstGridViewModel mainShouYeFirstGridViewModel;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Fragment> fragmentList;
    private Widget widget;
    private MainActivityPresenter mainPresenter;
    public MainShouYeFirstGVAdapter(Activity activity, MainShouYeFirstGridViewModel model){
        widget = new Widget();
        this.activity = activity;
        this.mainShouYeFirstGridViewModel = model;
        inflater = LayoutInflater.from(activity);
        initFragmentList();
        initViews();
    }
    private void initFragmentList(){
        fragmentList = new ArrayList<Fragment>();
        Fragment foodsFragment = new FoodsFragment(activity);
        Fragment hoursShopFragment = new HoursShopFragment(activity);
        Fragment tongChengFragment = new TongChengFragment(activity);
        Fragment waterBeerFragment = new WaterBeerFragment(activity);
        fragmentList.add(foodsFragment);
        fragmentList.add(hoursShopFragment);
        fragmentList.add(tongChengFragment);
        fragmentList.add(waterBeerFragment);
    }
    private void initViews(){
        widget.flyContent = (FrameLayout) activity.findViewById(R.id.fly_content);
        mainPresenter = new MainActivityPresenter(activity, R.id.fly_content);
    }
    @Override
    public int getCount() {
        return mainShouYeFirstGridViewModel.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return mainShouYeFirstGridViewModel.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_parts_main_shouye_first_gridview_lly,null);
            widget.ivGVItem = (ImageView)convertView.findViewById(R.id.iv_gv_item);
            widget.tvGVItem = (TextView)convertView.findViewById(R.id.tv_gv_item);
            widget.llyGVItem = (LinearLayout)convertView.findViewById(R.id.lly_gv_item);
            widget.ivCheapGVItem = (ImageView)convertView.findViewById(R.id.iv_cheap_gv_item);
            convertView.setTag(widget);
        }else {
            widget = (Widget)convertView.getTag();
        }
        String pic = mainShouYeFirstGridViewModel.getData().get(position).pic;
        String name = mainShouYeFirstGridViewModel.getData().get(position).name;
        boolean isCheap = mainShouYeFirstGridViewModel.getData().get(position).isCheap;
        if(!pic.isEmpty() && (pic != null)) {
            Glide.with(activity).load(pic).into(widget.ivGVItem);
        }
        if(!name.isEmpty() && (name != null)){
            widget.tvGVItem.setText(name);
        }
        if(isCheap == true) {
            widget.ivCheapGVItem.setVisibility(View.VISIBLE);
        }else{
            widget.ivCheapGVItem.setVisibility(View.INVISIBLE);
        }
        widget.llyGVItem.setOnClickListener(new ItemClickListener(position) {
        });
  //      CommonGVItem newItem = (CommonGVItem)getItem(position);
        return convertView;
    }
    private class  ItemClickListener implements View.OnClickListener{
        private int id;
        public ItemClickListener(int position){
            this.id = mainShouYeFirstGridViewModel.getData().get(position).id;
        }
        @Override
        public void onClick(View v) {
            switch (id){
                case 0:
                    mainPresenter.showFragment(fragmentList.get(0));
              //      Toast.makeText(activity,"this is 0",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    mainPresenter.showFragment(fragmentList.get(1));
            //        Toast.makeText(activity,"this is 1",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    mainPresenter.showFragment(fragmentList.get(2));
           //         Toast.makeText(activity,"this is 2",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    mainPresenter.showFragment(fragmentList.get(3));
                    //         Toast.makeText(activity,"this is 2",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    private class Widget{
        public ImageView ivGVItem;
        public TextView tvGVItem;
        public ImageView ivCheapGVItem;
        public LinearLayout llyGVItem;
        public FrameLayout flyContent;
    }
}
