package com.p2p.dsad.news.bean;

import java.util.List;

/**
 * 团体新闻实体
 * Created by dsad on 2017/9/11.
 */

public class News
{

    /**
     * status : 200
     * error :
     * count : 2
     * data : [{"news_id":"68883","title":"广西边防查获夜鹭132只：眼睛被戳瞎 嘴巴被封住","top_image":"http://cms-bucket.nosdn.127.net/d88f03b799ca4772ae9127fc0833a63b20170911100938.png","text_image0":"http://cms-bucket.nosdn.127.net/71a0aad8a81f440fa89654852e776a1420170911100938.png","text_image1":"http://cms-bucket.nosdn.127.net/fbc37cce6efc423a85b3237c1339615320170911100938.png","source":"中国新闻网","content":"\n \n \n \n  \"图为被戳瞎眼睛的夜鹭，双眼灰白有血晕。李昱颖摄\"\n 图为被戳瞎眼睛的夜鹭，双眼灰白有血晕。李昱颖摄\n ","digest":"中新网防城港9月11日电(张振国李昱颖)广西公安边防总队防城港边防支队横江边境检查站11日披露，该站日前查获活体夜鹭132只、中华鲎120只，死体夜鹭90公斤。","reply_count":"756","edit_time":"1505094840"},{"news_id":"68882","title":"拉萨队主场海拔3658米 客队首发6人被抬出场","top_image":"http://cms-bucket.nosdn.127.net/9821b1f4eefb49a1881f3c1977e2be1120170911025510.png","text_image0":"http://cms-bucket.nosdn.127.net/08ca9807aab24ecdb279d043361ebd2220170911025510.png","text_image1":"http://cms-bucket.nosdn.127.net/032a3912fe5f4f7aaeaabc13db79810120170911025510.png","source":"网易体育","content":"\n \n 网易体育9月11日报道：9月10日16点30分，2017中国业余联赛（俗称中丙）总决赛一场1/8决赛首回合比赛，在拉萨城与深圳鹏城协和辉之间展开。一场普通的业余联赛并没有什么吸引人眼球的地方，不过这场比赛是在海拔3658米的拉萨群众文化体育中心进行，客队首发11人中有6人被担架抬出了场外。<\/b>\n \n  \"\"\n \n ","digest":"网易体育9月11日报道：9月10日16点30分，2017中国业余联赛（俗称中丙）总决赛一场1/8决赛首回合比赛，在拉萨城与深圳鹏城协和辉之间展开。一场普通的业余","reply_count":"1416","edit_time":"1505085789"}]
     */

    private int status;
    private String error;
    private int count;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
