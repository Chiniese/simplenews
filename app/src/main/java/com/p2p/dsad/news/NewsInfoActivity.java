package com.p2p.dsad.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.p2p.dsad.news.base.BaseActivity;
import com.p2p.dsad.news.bean.SingleNews;
import com.p2p.dsad.news.utils.ConnectionUtils;
import com.p2p.dsad.news.utils.Contacts;
import com.p2p.dsad.news.utils.StringUtils;
import com.p2p.dsad.news.utils.Validator;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewsInfoActivity extends BaseActivity {

    @Bind(R.id.top_newsinfo_bar)
    Toolbar topNewsinfoBar;
    @Bind(R.id.app_newsinfo_barlayout)
    AppBarLayout appNewsinfoBarlayout;
    @Bind(R.id.tv_newsinfo_title)
    TextView tvNewsinfoTitle;
    @Bind(R.id.img_newsinfo_img1)
    ImageView imgNewsinfoImg1;
    @Bind(R.id.tv_newsinfo_newsinfo)
    TextView tvNewsinfoNewsinfo;
    @Bind(R.id.tv_newsinfo_edittime)
    TextView tvNewsinfoEdittime;
    @Bind(R.id.img_newsinfo_img0)
    ImageView imgNewsinfoImg0;
    @Bind(R.id.tv_newsinfo_source)
    TextView tvNewsinfoSource;
    private String news_id;
    private String tabnum_id;
    private SingleNews dataBean;
    private String url;
    private Map<String, String> urlparams = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        ButterKnife.bind(this);
        inintdata();
        inintui();
        inintsession();
    }

    private void inintdata() {
        //获取news_id
        getNews_id();
        //获取新闻本体
        getDataFromSeer();

    }

    private void configToolbar() {
        setSupportActionBar(topNewsinfoBar);
        getSupportActionBar().setTitle("新闻详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inintui() {
        //设置toolbar
        configToolbar();
    }

    private void inintsession() {

    }

    public void getNews_id() {
        Intent intent = getIntent();
        tabnum_id = intent.getStringExtra("table_id");
        news_id = intent.getStringExtra("news_id");
    }

    public void getDataFromSeer() {
        urlparams.put(Contacts.TABLENUM, tabnum_id);
        urlparams.put(Contacts.NESID, news_id);
        url = ConnectionUtils.getUrls(Contacts.BASE_URL_SINGLENEW, urlparams);
        ConnectionUtils.getData(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                parseData(response);
            }
        });
    }

    private void parseData(String response) {
        if (!TextUtils.isEmpty(response)) {
            dataBean = JSON.parseObject(response, SingleNews.class);
            //设置图片
            configtopimags();
            //设置文本
            configcontent();


        }

    }

    private void configcontent() {
        tvNewsinfoTitle.setText(dataBean.getData().getTitle());
        //这个才是简介
        tvNewsinfoNewsinfo.setText("\t\t\t\t" + dataBean.getData().getDigest());
        //设置其他
        tvNewsinfoEdittime.setText(StringUtils.getDateString(new Date(Long.valueOf(dataBean.getData().getEdit_time()))));
        tvNewsinfoSource.setText("来自:"+dataBean.getData().getSource());
    }

    private void configtopimags() {
        String img0 = dataBean.getData().getText_image0();
        String img1 = dataBean.getData().getText_image1();
        if (!TextUtils.isEmpty(img0))
        {
            Glide.with(getApplicationContext()).load(dataBean.getData().getText_image0()).into(imgNewsinfoImg0);
            imgNewsinfoImg0.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(img1))
        {
            Glide.with(getApplicationContext()).load(dataBean.getData().getText_image1()).into(imgNewsinfoImg1);
            imgNewsinfoImg1.setVisibility(View.VISIBLE);
        }


    }


}
