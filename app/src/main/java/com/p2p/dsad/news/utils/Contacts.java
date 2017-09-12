package com.p2p.dsad.news.utils;

/**
 * 所有链接的总类
 * Created by dsad on 2017/9/11.
 */

public class Contacts
{
    /***
     *        1 =>  头条
     *        2 =>  娱乐
     *        3 =>  军事
     *        4 =>  汽车
     *        5 =>  财经
     *        6 =>  笑话
     *        7 =>  体育
     *        8 =>  科技
     */
    //多条
    public static final String BASE_URL_NEWS ="http://api.dagoogle.cn/news/get-news";
    //单条
    public static final String BASE_URL_SINGLENEW="http://api.dagoogle.cn/news/single-news";
    //参数
    public static final String TABLENUM = "tableNum";
    public static final String PAGESIZE = "pagesize";
    public static final String NESID="news_id";
    //默认都是第一页(多条)
    //获得头条
    public static final String TOP_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=1"+"&"+PAGESIZE+"=1";
    //获得娱乐
    public static final String FUN_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=2"+"&"+PAGESIZE+"=1";
    //获得军事
    public static final String ARMY_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=3"+"&"+PAGESIZE+"=1";
    //获得汽车
    public static final String CAR_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=4"+"&"+PAGESIZE+"=1";
    //获得财经
    public static final String MONEY_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=5"+"&"+PAGESIZE+"=1";
    //获得笑话
    public static final String HEHE_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=6"+"&"+PAGESIZE+"=1";
    //获得体育
    public static final String SPORT_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=7"+"&"+PAGESIZE+"=1";
    //获得科技
    public static final String FANTASTIC_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=8"+"&"+PAGESIZE+"=1";
    //可配置的url
    //获得头条
    public static final String SET_TOP_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=1"+"&"+PAGESIZE+"=";
    //获得娱乐
    public static final String SET_FUN_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=2"+"&"+PAGESIZE+"=";
    //获得军事
    public static final String SET_ARMY_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=3"+"&"+PAGESIZE+"=";
    //获得汽车
    public static final String SET_CAR_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=4"+"&"+PAGESIZE+"=";
    //获得财经
    public static final String SET_MONEY_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=5"+"&"+PAGESIZE+"=";
    //获得笑话
    public static final String SET_HEHE_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=6"+"&"+PAGESIZE+"=";
    //获得体育
    public static final String SET_SPORT_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=7"+"&"+PAGESIZE+"=";
    //获得科技
    public static final String SET_FANTASTIC_NEWS=BASE_URL_NEWS+"?"+TABLENUM+"=8"+"&"+PAGESIZE+"=";
    //获取新闻实体单条
    //获得头条
    public static final String TOP_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=1"+"&"+NESID+"=1";
    //获得娱乐
    public static final String FUN_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=2"+"&"+NESID+"=1";
    //获得军事
    public static final String ARMY_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=3"+"&"+NESID+"=1";
    //获得汽车
    public static final String CAR_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=4"+"&"+NESID+"=1";
    //获得财经
    public static final String MONEY_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=5"+"&"+NESID+"=1";
    //获得笑话
    public static final String HEHE_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=6"+"&"+NESID+"=1";
    //获得体育
    public static final String SPORT_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=7"+"&"+NESID+"=1";
    //获得科技
    public static final String FANTASTIC_NEW=BASE_URL_SINGLENEW+"?"+TABLENUM+"=8"+"&"+PAGESIZE+"=1";
    //标识
    //头条
    public static final String TOP = "1";
    //娱乐
    public static final String FUN ="2";
    //军事
    public static final String ARMY="3";
    //汽车
    public static final String CAR ="4";
    //财经
    public static final String MONEY="5";
    //笑话
    public static final String HEHE="6";
    //体育
    public static final String SPORT="7";
    //科技
    public static final String FANTASTIC="8";
    //所有类别的数组,因为是按顺序放的
    public static final String[] TYPES={TOP,FUN,ARMY,CAR,MONEY,HEHE,SPORT,FANTASTIC};

}
