package com.p2p.dsad.news.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;


import com.p2p.dsad.news.R;
import com.p2p.dsad.news.appliction.MyAppliction;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用的ui工具
 * Created by dsad on 2017/8/29.
 */

public class UiUtlis
{
    private UiUtlis()
    {

    }

    private static Context getContext()
    {
        return MyAppliction.getContext();
    }



    public static List<String> getViewPagerTitle()
    {
        String[] rs = getContext().getResources().getStringArray(R.array.TopIndicatorTitles);
        List<String> list = new ArrayList<>();
        for (String s :rs)
        {
            list.add(s);
        }
        return list;
    }

    /**
     * 获取本地版本号
     * @return 版本号
     */
    public static String getVersion()
    {
        String version ="";

        PackageManager manager = getContext().getPackageManager();
        try
        {
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(),0);
            version = info.versionName;
            return version;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 取得颜色
     * @param colorid 颜色id
     * @return
     */
    public static int getColor(int colorid)
    {
        return getContext().getResources().getColor(colorid);
    }

    /**
     * 获取view
     * @param viewid 视图id
     * @return
     */
    public static View getView(int viewid)
    {
        return View.inflate(getContext(),viewid,null);
    }

    //将dp转化为px
    public static int dp2px(int dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }

    public static int px2dp(int px){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//实现四舍五入
    }


}
