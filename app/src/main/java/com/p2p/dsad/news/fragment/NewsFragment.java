package com.p2p.dsad.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.p2p.dsad.news.NewsInfoActivity;
import com.p2p.dsad.news.R;
import com.p2p.dsad.news.base.BaseFragment;
import com.p2p.dsad.news.bean.DataBean;
import com.p2p.dsad.news.bean.News;
import com.p2p.dsad.news.utils.ConnectionUtils;
import com.p2p.dsad.news.utils.Contacts;
import com.p2p.dsad.news.utils.RecyclerViewItemSpace;
import com.p2p.dsad.news.utils.ToastUtils;
import com.p2p.dsad.news.utils.UiUtlis;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 所有页面的fragment都是这一个统一管理(布局一样,结构一样)
 * Created by dsad on 2017/9/11.
 */

public class NewsFragment extends BaseFragment
{
    String Tag = "news";
    @Bind(R.id.recycler_news)
    RecyclerView recyclerNews;
    @Bind(R.id.topbtn_home)
    FloatingActionButton topbtnHome;
    @Bind(R.id.re_refrsh)
    SwipeRefreshLayout reRefrsh;
    private Bundle bundle;
    private String type;
    private Map<String, String> urlparm = new HashMap<>();
    private String url;
    //默认页面20条
    private int page = 20;
    private MyrecyclerAdpter re_adpter;
    private News news;
    private String result;
    @Override
    protected int inintview() {
        return R.layout.mianrecycler_news;
    }

    /***
     * 看这里(方法顺序)
     */
    @Override
    protected void inintdata() {
        //初始化recycler
        inintrecycler();
        //取得type(请求的关键)
        getType();
        //取得数据
        getDataFromSever();
        //设置事件
        inintsession();

    }

    private void inintsession()
    {
        //监听recyclerview是否滑动到底部
        recyclerNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState)
                {
                    //直接根据状态判断是否到底部
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //执行(延时下看下效果)
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page=page+20;
                            }
                        },2000);
                        getDataFromSever();
                        break;
                    default:

                        break;
                }
            }
        });
    }

    private void inintrecycler() {
        re_adpter = new MyrecyclerAdpter();
        recyclerNews.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerNews.setAdapter(re_adpter);
        recyclerNews.addItemDecoration(new RecyclerViewItemSpace(UiUtlis.dp2px(8)));
    }

    public void getType() {
        bundle = getArguments();
        type = bundle.getString("type");
    }

    public void getDataFromSever()
    {
        //请求json数据
        urlparm.put(Contacts.TABLENUM, type);
        urlparm.put(Contacts.PAGESIZE, String.valueOf(page));
        url = ConnectionUtils.getUrls(Contacts.BASE_URL_NEWS, urlparm);
        ConnectionUtils.getData(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id)
            {
                result=response;
                paresData();

            }
        });
        //清除参数
        urlparm.clear();
    }

    //处理结果
    private void paresData() {
        if (result == null || result.equals(""))
        {
            //没有新闻数据
            ToastUtils.showToast("没有任何数据");
        }
        else
            {

            //解析数据
            news = JSON.parseObject(result,News.class);
                //初始化适配器(默认)
                firstsetrecycler();
            //设置floatactionbar
            configfloatbtn();
            //设置刷新操作
            configrefresh();
        }
    }

    private void firstsetrecycler()
    {
        re_adpter.setData(news);
        re_adpter.notifyDataSetChanged();
    }

    private void configrefresh()
    {
        //设置刷新
        reRefrsh.setColorSchemeColors(UiUtlis.getColor(R.color.round_red_common),
                UiUtlis.getColor(R.color.more_text),
                UiUtlis.getColor(R.color.text_progress));
        reRefrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                //下拉刷新(多出20条)(由于api的限制,模拟下拉刷新只有这样了)
                page=page+20;
                //执行获取数据
                getDataFromSever();
                re_adpter.setData(news);
                re_adpter.notifyDataSetChanged();
                //跳往最新的地方
                recyclerNews.scrollToPosition(page);
                ToastUtils.showToast("当前已更新:"+page+"条");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //结束刷新(至少2秒)
                        reRefrsh.setRefreshing(false);
                    }
                },2000);

            }
        });
    }

    private void configfloatbtn() {
        topbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerNews.scrollToPosition(0);
            }
        });
    }



    class MyrecyclerAdpter extends RecyclerView.Adapter
    {
        //底部的视图
        public final int NORMAL_TYPE=0;
        public final int FOOT_TYPE=1;
        public int nowtype=0;
        private News i_news;
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v = null;
            if (viewType==NORMAL_TYPE)
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
                return new MyViewHolder(v);
            }
            else if (viewType==FOOT_TYPE)
            {
               v = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmore,parent,false);
                return new LoadMoreHolder(v);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position)
        {
            if (position==getItemCount()-1)
            {
                //减一才是最后一位(替换了一条新闻)
                //这样就一直少了一条新闻
                nowtype = FOOT_TYPE;
                return nowtype;
            }
                nowtype = NORMAL_TYPE;
            return NORMAL_TYPE;

        }

        public void setData(News news)
        {
            this.i_news = news;
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
        {
            if (getItemViewType(position)==NORMAL_TYPE)
            {
                MyViewHolder viewHolder = (MyViewHolder) holder;
                DataBean ben = i_news.getData().get(position);
                String imgurl = ben.getTop_image();
                if (!TextUtils.isEmpty(imgurl)) {
                    viewHolder.img_newstopimg.setVisibility(View.VISIBLE);
                    Glide.with(mActivity).load(ben.getTop_image()).into(viewHolder.img_newstopimg);
                }
                else
                {
                    viewHolder.img_newstopimg.setVisibility(View.GONE);
                }
                viewHolder.tv_title.setText(ben.getTitle());
                viewHolder.tv_source.setText("来自:" + ben.getSource());
                long news_time = Long.parseLong(ben.getEdit_time());
                viewHolder.tv_edittime.setText(toStringTime(news_time));//这是个long
                viewHolder.tv_readcount.setText(ben.getReply_count());
            }
            else if (getItemViewType(position)==FOOT_TYPE)
            {
                LoadMoreHolder load_holder = (LoadMoreHolder) holder;
                load_holder.tv_load.setText("脸上笑嘻嘻,心里mmp");

            }

        }

        public String toStringTime(long news_time)
        {
            SimpleDateFormat format = new SimpleDateFormat("mm-dd hh:mm");
            return format.format(news_time);
        }


        @Override
        public int getItemCount() {
            //这里加一的位置是给底部加载更多用的
            return news==null?0:news.getData().size()+1;
        }

        /***
         * 正常新闻视图的viewholder
         */
        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView tv_title;
            private TextView tv_readcount;
            private TextView tv_source;
            private TextView tv_edittime;
            private ImageView img_newstopimg;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_item_newstitle);
                tv_readcount = itemView.findViewById(R.id.tv_item_readcount);
                tv_edittime = itemView.findViewById(R.id.tv_item_edittime);
                tv_source = itemView.findViewById(R.id.tv_item_source);
                img_newstopimg = itemView.findViewById(R.id.img_item_newstopimage);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mContext, NewsInfoActivity.class);
                intent.putExtra("news_id",news.getData().get(getLayoutPosition()).getNews_id());
                intent.putExtra("table_id",type);
                startActivity(intent);
            }
        }

        /***
         * 底部加载更多的viewholder
         */
        class LoadMoreHolder extends RecyclerView.ViewHolder
        {
            private TextView tv_load;
            public LoadMoreHolder(View itemView) {
                super(itemView);
                tv_load = itemView.findViewById(R.id.tv_item_load);
            }
        }

    }

}
