package com.gototongcheng.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.gototongcheng.application.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 16/6/10.
 */
public class LuanchActivity extends Activity{
    @Bind(R.id.iv_luanch)
    ImageView mLuanchImage;

    @Bind(R.id.tv_form)
    TextView mFormText;

    private static final String RESOLUTION = "1080*1776";

    private static final int ANIMATION_DURATION = 2000;

    private static final float SCALE_END = 1.13F;

    private Handler mHandler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {

            super.handleMessage(msg);
            if (msg.what == 0)
            {
                animateImage();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luanch);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private void animateImage()
    {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mLuanchImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mLuanchImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {

                startActivity(new Intent(LuanchActivity.this, MainActivity.class));
                LuanchActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
