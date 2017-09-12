package com.p2p.dsad.news.utils;

import android.widget.Toast;

import com.p2p.dsad.news.appliction.MyAppliction;

/**
 * 弹窗工具
 * Created by dsad on 2017/8/28.
 */

public class ToastUtils
{
    private static Toast toast;

    private ToastUtils()
    {

    }

    /**
     *
     * 展示吐司
     * @param msg 内容
     */
    public static void showToast(String  msg)
    {
        if (toast==null)
        {
            toast = Toast.makeText(MyAppliction.getContext(),msg,Toast.LENGTH_SHORT);
        }
        else
        {
            toast.setText(msg);
        }
            toast.show();
    }

}
