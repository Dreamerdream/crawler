package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.http.SeimiAgentContentType;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.apache.commons.lang3.RandomUtils;

import javax.transaction.xa.XAException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
@Crawler(name = "jdcrawlers",httpTimeOut = 30000,queue = DefaultRedisQueue.class)
public class JDCrawlers extends BaseSeimiCrawler{

    private static final String URL = "https://search.jd.com/Search?keyword=%e6%89%8b%e8%a1%a8&enc=utf-8";
    private static final String PAGE_PARAMETER = "&s=";
    private static final String HTTPS = "https:";
    @Override
    public String[] startUrls() {
        return null;
    }

    @Override
    public void start(Response response) {

    }

    @Override
    public List<Request> startRequests() {
        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        request.setUrl(URL);
        request.setUseSeimiAgent(true);
        request.setSeimiAgentRenderTime(5000);
        request.setSeimiAgentUseCookie(false);
        request.setSeimiAgentScript("$(function(){$(document).scrollTop($(document).height()-$(window).height());});");
        request.setCallBack("jingdong");
        requests.add(request);
        return requests;
    }

    public void jingdong(Response response) throws XpathSyntaxErrorException{
        String content = response.getContent();
        JXDocument document = response.document();
      //  List<Object> sel1 = document.sel("//li[@class='gl-item']/div/div[3]/a/@href | //li[@class='gl-item']/div[1]/div[1]/a/@href");
        List<Object> sel = document.sel("//span[@class='p-skip']/em/b/text()");
        if (sel!=null&&sel.size()!=0){
            String page = sel.get(0).toString();
            System.out.println("page-------" + page);
            Integer pageNum = Integer.valueOf(page);
            for (int i = 0; i < pageNum; i++) {
                String url = URL + PAGE_PARAMETER + (2*i+1);
                Request request = new Request();
                request.setUseSeimiAgent(true);
                request.setSeimiAgentRenderTime(5000);
                request.setSeimiAgentUseCookie(true);
                request.setUrl(url);
                request.setCallBack("getUrl");
                push(request);
            }
        }
    }

    public void getUrl(Response response) throws XpathSyntaxErrorException{


        JXDocument document = response.document();
        List<Object> sel = document.sel("//li[@class='gl-item']/div/div[3]/a/@href | //li[@class='gl-item']/div[1]/div[1]/a/@href");
        if (sel != null && sel.size() != 0){
            for (int i = 0; i < sel.size() ; i++) {
                String url = sel.get(i).toString();
                if (!url.contains(HTTPS)){
                    url = HTTPS + url;
                }
                logger.info("结果---"+url);
                Request request = new Request();
                request.setUrl(url);
                request.setUseSeimiAgent(true);
                request.setSeimiAgentRenderTime(5000);
                request.setSeimiAgentUseCookie(true);
                request.setCallBack("getData");
                push(request);
            }
        }

     //   }
/*
        List<Object> sel1 = document.sel("//span[@class='J_im_icon']/a/@title");
        for (Object o:sel1) {
            String url = o.toString();
            logger.info("店铺---"+url);
        }

        List<Object> sel2 = document.sel("//div[@id='J_goodsList']/ul/li");
        logger.info("数量---" + sel2.size());*/

    }

    public void getData(Response response) throws XpathSyntaxErrorException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='p-parameter']/ul[2]/li | //div[@class='Ptable-item']/dl");
        if (sel!=null&&sel.size()!=0){
            for (int i=0;i<sel.size();i++) {
                logger.info(i+"----------"+"信息---"+sel.get(i).toString());
            }
        }
    }

    @Override
    public String seimiAgentHost() {
        return "192.168.111.130";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }

  /*  @Override
    public String proxy() {
        String[] proxies = new String[]{"27.14.93.230:9999",
                "171.38.65.245:8123",
                "106.46.136.14:808",
                "31.173.1.92:8081",
                "117.168.98.105:9999",
                "177.136.252.7:3128",
                "87.249.4.22:8081",
                "124.192.7.228:3128",
                "94.177.243.200:8080",
                "92.63.194.17:3128",
                "47.89.41.164:80",
                "218.29.134.246:3128",
                "89.40.114.149:8080",
                "118.172.128.8:8080",
                "112.230.152.109:9999",
                "125.40.26.104:9999",
                "103.24.212.53:8080",
                "218.191.247.51:80",
                "5.160.33.92:3128",
                "42.203.132.115:9999",
                "185.58.224.141:80",
                "122.243.208.214:8998"};
        return proxies[RandomUtils.nextInt(0,22)];
    }*/

    @Override
    public String proxy() {
        System.out.println("代理-----------");
        return super.proxy();
    }
}
