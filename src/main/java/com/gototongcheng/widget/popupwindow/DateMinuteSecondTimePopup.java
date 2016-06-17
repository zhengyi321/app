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
import java.util.Calendar;

/**
 * Created by admin on 16/6/17.
 */
public class DateMinuteSecondTimePopup extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private  Boolean issetdata = false;
    private int currentYear = getYear();
    private int currentMonth = 1;
    private int currentDay = 1;
    private String selectYear;
    private String selectMonth;
    private String selectDay;
    private String selectHour;
    private String selectMinute;
    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;
    private CalendarTextAdapter mHourdapter;
    private CalendarTextAdapter mMinutedapter;
    private ArrayList<String> arry_years = new ArrayList<String>();
    private ArrayList<String> arry_months = new ArrayList<String>();
    private ArrayList<String> arry_days = new ArrayList<String>();
    private ArrayList<String> arry_hour = new ArrayList<String>();
    private ArrayList<String> arry_minute = new ArrayList<String>();
    private int month;
    private int day;
    private Widget widget = new Widget();
    private Context context;
    private int maxTextSize = 24;
    private int minTextSize = 14;
    private OnDateTimePopupListener onDateTimePopupListener;
    private String title;
    public DateMinuteSecondTimePopup(final Context context,String title)
    {
        super(context);
        this.title = title;
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView= inflater.inflate(R.layout.popupwindow_myinfo_fivewheel_lly, null);
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
        initYears();
        mYearAdapter = new CalendarTextAdapter(context, arry_years, setYear(currentYear), maxTextSize, minTextSize);
        widget.fwv.setVisibleItems(5);
        widget.fwv.setViewAdapter(mYearAdapter);
        widget.fwv.setCurrentItem(setYear(currentYear));

        initMonths(month);
        mMonthAdapter = new CalendarTextAdapter(context, arry_months, setMonth(currentMonth), maxTextSize, minTextSize);
        widget.swv.setVisibleItems(5);
        widget.swv.setViewAdapter(mMonthAdapter);
        widget.swv.setCurrentItem(setMonth(currentMonth));

        initDays(day);
        mDaydapter = new CalendarTextAdapter(context, arry_days, currentDay - 1, maxTextSize, minTextSize);
        widget.twv.setVisibleItems(5);
        widget.twv.setViewAdapter(mDaydapter);
        widget.twv.setCurrentItem(currentDay - 1);
        initHour();
        mHourdapter = new CalendarTextAdapter(context, arry_hour, 0, maxTextSize, minTextSize);
        widget.fowv.setVisibleItems(5);
        widget.fowv.setViewAdapter(mHourdapter);
        widget.fowv.setCurrentItem(0);
        initMinute();
        mMinutedapter = new CalendarTextAdapter(context, arry_minute, 0, maxTextSize, minTextSize);
        widget.fiwv.setVisibleItems(5);
        widget.fiwv.setViewAdapter(mMinutedapter);
        widget.fiwv.setCurrentItem(0);
        widget.fwv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                selectYear = currentText;
                setTextviewSize(currentText, mYearAdapter);
                currentYear = Integer.parseInt(currentText);
                setYear(currentYear);
                initMonths(month);
                mMonthAdapter = new CalendarTextAdapter(context, arry_months, 0, maxTextSize, minTextSize);
                widget.swv.setVisibleItems(5);
                widget.swv.setViewAdapter(mMonthAdapter);
                widget.swv.setCurrentItem(0);
            }
        });

        widget.fwv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        widget.swv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);
                setMonth(Integer.parseInt(currentText));
                initDays(day);
                mDaydapter = new CalendarTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
                widget.twv.setVisibleItems(5);
                widget.twv.setViewAdapter(mDaydapter);
                widget.twv.setCurrentItem(0);
            }
        });

        widget.swv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        widget.twv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
            }
        });

        widget.twv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });


        widget.fowv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mHourdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mHourdapter);
                selectHour = currentText;
            }
        });

        widget.fowv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mHourdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mHourdapter);
            }
        });




        widget.fiwv.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMinutedapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMinutedapter);
                selectMinute = currentText;
            }
        });

        widget.fiwv.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMinutedapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMinutedapter);
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
    public void initYears() {
        /*
                for (int i = getYear()+100; i > 1950; i--) {
            arry_years.add(i + "");
        }
         */

        for (int i = 2100; i > 1950; i--) {
            arry_years.add(i + "");
        }
    }
    public void initMonths(int months) {
        arry_months.clear();
        for (int i = 1; i <= months; i++) {
            arry_months.add(i + "");
        }
    }

    public void initDays(int days) {
        arry_days.clear();
        for (int i = 1; i <= days; i++) {
            arry_days.add(i + "");
        }
    }

    public void initHour()
    {
        this.selectHour = "00";
        arry_hour.clear();
        for(int i=0;i<24;i++)
        {
            if(i <10)
            {
                arry_hour.add("0"+i);
            }else {
                arry_hour.add("" + i);
            }
        }
    }
    public void initMinute()
    {
        this.selectMinute = "00";
        arry_minute.clear();
        for(int i=0;i<60;i++)
        {
            if(i <10)
            {
                arry_minute.add("0"+i);
            }else {
                arry_minute.add("" + i);
            }
        }
    }
    private void initView()
    {
        widget.fwv = (WheelView)mPopView.findViewById(R.id.fwv);
        widget.swv = (WheelView)mPopView.findViewById(R.id.swv);
        widget.twv = (WheelView)mPopView.findViewById(R.id.twv);
        widget.fowv = (WheelView)mPopView.findViewById(R.id.fowv);
        widget.fiwv = (WheelView)mPopView.findViewById(R.id.fiwv);
        widget.tv_share_title = (TextView)mPopView.findViewById(R.id.tv_share_title);
        if(title != null) {
            widget.tv_share_title.setText(title);
        }
        widget.rlyok = (RelativeLayout)mPopView.findViewById(R.id.rlyok);
        widget.rlyok.setOnClickListener(this);
    }

    private void initData()
    {
        setDate(getYear(), getMonth(), getDay());
        //       this.currentDay = 1;
        //       this.currentMonth = 1;
    }

    /**
     * 设置年月日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day) {
        selectYear = year + "";
        selectMonth = month + "";
        selectDay = day + "";
        issetdata = true;
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        if (year == getYear()) {
            this.month = getMonth();
        } else {
            this.month = 12;
        }
        calDays(year, month);
    }

    /**
     * 设置年份
     *
     * @param year
     */
    public int setYear(int year) {
        int yearIndex = 0;
        if (year != getYear()) {
            this.month = 12;
        } else {
            this.month = getMonth();
        }
/*
        for (int i = getYear()+100; i > 1950; i--) {
            if (i == year) {
                return yearIndex;
            }
            yearIndex++;
        }*/
        for (int i = 2100; i > 1950; i--) {
            if (i == year) {
                return yearIndex;
            }
            yearIndex++;
        }
        return yearIndex;
    }

    /**
     * 设置月份
     *
     * @param year
     * @param month
     * @return
     */
    public int setMonth(int month) {
        int monthIndex = 0;
        calDays(currentYear, month);
        for (int i = 1; i < this.month; i++) {
            if (month == i) {
                return monthIndex;
            } else {
                monthIndex++;
            }
        }
        return monthIndex;
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param leayyear
     */
    public void calDays(int year, int month) {
        boolean leayyear = false;
        if (year % 4 == 0 && year % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = 31;
                    break;
                case 2:
                    if (leayyear) {
                        this.day = 29;
                    } else {
                        this.day = 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    this.day = 30;
                    break;
            }
        }
        if (year == getYear() && month == getMonth()) {
            this.day = getDay();
        }
    }

    public int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rlyok:
                if(onDateTimePopupListener != null)
                {
                    onDateTimePopupListener.onClick(selectYear,selectMonth,selectDay,selectHour,selectMinute);
                }
                break;
        }
        dismiss();
    }
    public interface OnDateTimePopupListener
    {
        public void onClick(String year, String month, String day, String hour, String minute);
    }
    public void setOnDateTimePopupListener(OnDateTimePopupListener onDateTimePopupListener)
    {
        this.onDateTimePopupListener = onDateTimePopupListener;
    }
    public class Widget{
        public WheelView fwv;
        public WheelView swv;
        public WheelView twv;
        public WheelView fowv;
        public WheelView fiwv;
        public RelativeLayout rlyok;
        public TextView tv_share_title;
    }
}
