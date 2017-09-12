package com.p2p.dsad.news;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.p2p.dsad.news.base.BaseActivity;
import com.p2p.dsad.news.fragment.NewsFragment;
import com.p2p.dsad.news.utils.Contacts;
import com.p2p.dsad.news.utils.UiUtlis;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.indicator_home_news)
    MagicIndicator indicatorHomeNews;
    @Bind(R.id.viewpagers_home_news)
    ViewPager viewpagersHomeNews;
    @Bind(R.id.top_home_bar)
    Toolbar topHomeBar;
    private List<String> indcator_titles = new ArrayList<>();
    private List<Fragment> allfragments = new ArrayList<>();
    private MyviewPager pager_adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    private void inintdata()
    {
        setSupportActionBar(topHomeBar);
        //不显示标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void inintui()
    {
        //获取标题
        inittitle();
        //初始化fragments
        inintfragments();
        //设置viewpager
        configviewpagers();
        //设置indicator
        configIndicator();
    }

    private void inintfragments() {
        //以防万一
        if (indcator_titles != null && indcator_titles.size() > 0) {

            for (int i = 0; i < indcator_titles.size(); i++) {
                Bundle bun = new Bundle();
                //这里最好完成每个fragment的标识
                NewsFragment news = new NewsFragment();
                bun.putString("type", Contacts.TYPES[i]);
                news.setArguments(bun);
                allfragments.add(news);
            }
        }
    }

    private void inittitle() {
        indcator_titles = UiUtlis.getViewPagerTitle();
    }

    private void configviewpagers() {
        pager_adpter = new MyviewPager(getSupportFragmentManager());
        viewpagersHomeNews.setAdapter(pager_adpter);
        //设置页面缓存范围
        viewpagersHomeNews.setOffscreenPageLimit(pager_adpter.getCount() - 1);
    }

    private void configIndicator() {
        CommonNavigator nav = new CommonNavigator(getApplicationContext());
        //设置默认状态
        nav.setAdjustMode(false);
        nav.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indcator_titles == null ? 0 : indcator_titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                ClipPagerTitleView tv = new ClipPagerTitleView(getApplicationContext());
                tv.setTextColor(Color.WHITE);
                tv.setClipColor(Color.WHITE);
                tv.setText(indcator_titles.get(i));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewpagersHomeNews.setCurrentItem(i);
                    }
                });
                return tv;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator inda = new LinePagerIndicator(getApplicationContext());
                inda.setColors(Color.WHITE);
                inda.setLineHeight(UIUtil.dip2px(context, 1));
                inda.setLineWidth(UIUtil.dip2px(context, 4));
                return inda;
            }
        });
        //设置指示器
        indicatorHomeNews.setNavigator(nav);

    }

    private void inintsession() {
        //互相绑定,以示友好
        viewpagersHomeNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorHomeNews.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorHomeNews.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorHomeNews.onPageScrollStateChanged(state);
            }
        });
    }

    /**
     * viewpager的适配器
     */
    class MyviewPager extends FragmentPagerAdapter {

        public MyviewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return allfragments.get(position);
        }

        @Override
        public int getCount() {
            return allfragments.size();
        }
    }
}
