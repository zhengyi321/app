package com.gototongcheng.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.widget.popupwindow.childwidget.AbstractWheelTextAdapter;
import com.gototongcheng.widget.popupwindow.childwidget.OnWheelChangedListener;
import com.gototongcheng.widget.popupwindow.childwidget.OnWheelScrollListener;
import com.gototongcheng.widget.popupwindow.childwidget.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyan on 16/6/17.
 */
public class OneWheelPopup extends PopupWindow implements View.OnClickListener {
    private View mPopView;
    private  Boolean issetdata = false;
    private String defaultItem;
    private String selectItem;
    private OneWheelWidget oneWheelWidget = new OneWheelWidget();
    private Widget widget = new Widget();
    private List<String > dataList;
    private CalendarTextAdapter adapter;
    private int maxTextSize = 26;
    private int minTextSize = 15;
    private OnPopupListener onPopupListener;
    private String title;

    public OneWheelPopup(Context context,String title,List<String> mdataList)
    {
        super(context);
        this.title = title;

        initList(mdataList);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView= inflater.inflate(R.layout.popupwindow_myinfo_onewheel, null);
        this.setContentView(mPopView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 点击外面的控件也可以使得PopUpWindow dimiss
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);//0xb0000000
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);//半透明颜色
        if (!issetdata) {
            initData();
        }
        initView();

        adapter = new CalendarTextAdapter(context, dataList, setDefaultItem(defaultItem), maxTextSize, minTextSize);
        oneWheelWidget.fwv.setVisibleItems(5);
        oneWheelWidget.fwv.setViewAdapter(adapter);
        oneWheelWidget.fwv.setCurrentItem(setDefaultItem(defaultItem));

        oneWheelWidget.fwv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) adapter.getItemText(wheel.getCurrentItem());
                selectItem = currentText;
                setTextviewSize(currentText, adapter);

            }
        });

        oneWheelWidget.fwv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) adapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, adapter);
            }
        });
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }
    private void initList(List<String> mDataList)
    {
 //       dataList = new ArrayList<String>();
        if(mDataList != null){
            dataList = mDataList;
        }else{
            dataList = new ArrayList<String>();
        }

    }

    /**
     * 设置默认身高情况
     *
     * @param defaultEarn
     */
    public int setDefaultItem(String  defaultItem) {
        int dataIndex = 0;

        for (int i = 0; i < dataList.size(); i++) {
            if(dataList.get(i).equals(defaultItem)) {
                return dataIndex;
            }
            dataIndex++;
        }
        return dataIndex;
    }
    private void initView()
    {
        oneWheelWidget.tv_share_title = (TextView) mPopView.findViewById(R.id.tv_share_title);
        oneWheelWidget.fwv = (WheelView)mPopView.findViewById(R.id.fwv);
        oneWheelWidget.btnSure = (TextView)mPopView.findViewById(R.id.btn_myinfo_sure);
        if(title != null){
            oneWheelWidget.tv_share_title.setText(title);
        }else {
            oneWheelWidget.tv_share_title.setText("职业");
        }
        widget.rlyok = (RelativeLayout)mPopView.findViewById(R.id.rlyok);
        widget.rlyok.setOnClickListener(this);
    }
    private class Widget
    {
        public RelativeLayout rlyok;
    }
    private void initData()
    {
      //  this.defaultItem = "在校学生";
        if(dataList.size() > 0) {
            this.defaultItem = dataList.get(0);
        }
        this.selectItem = defaultItem;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rlyok:
                if(onPopupListener != null)
                {
                    onPopupListener.onClick(selectItem);
                }
                break;
        }
        dismiss();
    }
    public interface OnPopupListener
    {
        public void onClick(String item);
    }
    public void setOnPopupListener(OnPopupListener onPopupListener)
    {
        this.onPopupListener = onPopupListener;
    }


    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        List<String> FList;

        protected CalendarTextAdapter(Context context,List<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.FList = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return FList.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return FList.get(index) + "";
        }
    }
    public class OneWheelWidget {
        public TextView tv_share_title;
        public WheelView fwv;
        public TextView btnSure;
        public TextView btnCancel;

    }
}
