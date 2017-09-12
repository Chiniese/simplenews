package com.p2p.dsad.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.p2p.dsad.news.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.img_welcome_bg)
    ImageView imgWelcomeBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initani();
    }

    private void initani()
    {
        AlphaAnimation a = new AlphaAnimation(0,1);
        a.setDuration(2000);
        a.start();
        imgWelcomeBg.setAnimation(a);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              //结束当前页面
                startActivity(HomeActivity.class,true);
            }
        },2000);
    }
}
