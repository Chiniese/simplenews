package com.p2p.dsad.news.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 所以activity的基类
 * Created by dsad on 2017/9/11.
 */

public class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    //跳转activity
    protected void startActivity(Class clzz,boolean flag)
    {
        if (flag)
        {
            startActivity(new Intent(this,clzz));
            finish();
        }
        else
        {
            startActivity(new Intent(this,clzz));
        }
    }
}
