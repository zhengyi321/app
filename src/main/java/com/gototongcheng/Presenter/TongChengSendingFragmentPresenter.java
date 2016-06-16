package com.gototongcheng.Presenter;

import android.app.Activity;
import android.widget.LinearLayout;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingFragmentPresenter extends BasePresenter{


    public   TongChengSendingWidget widget ;
    public TongChengSendingFragmentPresenter(){

    }
    public TongChengSendingFragmentPresenter(Activity activity){

        initViews(activity);
    }

    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(widget == null) {
            widget = new TongChengSendingWidget();
        }
        widget.llyTongchengSendingSender = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_sender);
        widget.llyTongchengSendingReceiver = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_receiver);
    }



    public void back() {
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    public  class TongChengSendingWidget{
        public LinearLayout llyTongchengSendingSender;
        public LinearLayout llyTongchengSendingReceiver;

    }

}
