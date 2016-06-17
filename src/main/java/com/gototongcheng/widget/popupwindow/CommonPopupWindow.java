package com.gototongcheng.widget.popupwindow;

import android.app.Activity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 16/6/17.
 */
public class CommonPopupWindow {


    public  void DateTimeMinuteSecondPopup(final int rid, final Activity activity, LinearLayout layout,String title)
    {
        DateMinuteSecondTimePopup dateTimeMinuteSecondPopup = new DateMinuteSecondTimePopup(activity,title);
        dateTimeMinuteSecondPopup.setOnDateTimePopupListener(new DateMinuteSecondTimePopup.OnDateTimePopupListener() {
            @Override
            public void onClick(String year, String month, String day, String hour, String minute) {
                String birth = year + "-" + month + "-" + day+" "+hour+":"+minute;
                ((TextView) activity.findViewById(rid)).setText(birth);
            }


        });
        dateTimeMinuteSecondPopup.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

    }

    public  void OneItemPopup(final int rid, final Activity activity, LinearLayout layout,String title,List<String> dataList)
    {
        OneWheelPopup oneWheelPopup = new OneWheelPopup(activity,title,dataList);
        oneWheelPopup.setOnPopupListener(new OneWheelPopup.OnPopupListener() {
            @Override
            public void onClick(String item) {
                ((TextView) activity.findViewById(rid)).setText(item);
            }
        });
        oneWheelPopup.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

    }
}
