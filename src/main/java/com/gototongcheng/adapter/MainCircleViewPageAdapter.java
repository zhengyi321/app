package com.gototongcheng.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gototongcheng.application.R;
import com.gototongcheng.model.MainShouYeCircleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/6/10.
 */
public class MainCircleViewPageAdapter extends PagerAdapter {

    private MainShouYeCircleModel mainShouYeCircleModel ;
    private Context mContext;


    public MainCircleViewPageAdapter(Context context, MainShouYeCircleModel mainShouYeCircleModel)
    {

        this.mainShouYeCircleModel = mainShouYeCircleModel;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mainShouYeCircleModel.getData().size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_parts_main_shouye_viewpager_fly, container, false);
        ImageView mImg = (ImageView) view.findViewById(R.id.im_vp_item);
    //    MainShouYeCircleModel circleModel = mainShouYeCircleModelList.get(position);
    //    mImg.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.goto_tongcheng_slide_ads));
        String pic = mainShouYeCircleModel.getData().get(position).pic;
        if((pic != null) && (!pic.isEmpty())) {
            Glide.with(mContext).load(pic).into(mImg);
        }
    //    mTitle.setText(mTopDaily.getTitle());
        final int id = mainShouYeCircleModel.getData().get(position).id;
        view.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

   //             DailyDetailActivity.lanuch(mContext, id);
            }
        });
        container.addView(view);

        return view;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public void destroyItem(ViewGroup container, int position, Object object)
    {

        container.removeView((View) object);
    }
}
