package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.bean.AlibabaDetail;
import cn.wanghaomiao.seimi.bean.TestUrl;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.http.SeimiCookie;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/18.
 */
@Crawler(name = "alibabacrawlers",httpTimeOut = 25000)
public class AlibabaCrawlers extends BaseSeimiCrawler{
    @Override
    public String[] startUrls() {
        return new String[0];
    }

    @Override
    public void start(Response response) {

    }

    @Override
    public List<Request> startRequests()  {
//        List<SeimiCookie> cookies = new ArrayList<>();
//        SeimiCookie userIdCookie = new SeimiCookie("https://www.1688.com/","D:\\","userID","q4rjhHn0ITMz7zbIRlUP7VWW1B9ijgNBJGT5xVxNjCs6sOlEpJKl9g==");
//        SeimiCookie userIdNumcookie = new SeimiCookie("https://www.1688.com/","D:\\","userIDNum","7pBfq05Sdgm+kczvUn3C/Q==");
//        cookies.add(userIdCookie);
//        cookies.add(userIdNumcookie);
//        Map<String, String> map = new HashMap<>();
//        map.put("Accept-Encoding","tgzip, deflate, br");
//        map.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//        map.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        SeimiCookie seimiCookie1 = new SeimiCookie(".1688.com","/","UM_distinctid","15d63ca39c6e5-073dc583246293-474b0421-1fa400-15d63ca39c7a78");
//        SeimiCookie seimiCookie2 = new SeimiCookie(".1688.com","/","cna","kcD0ESJCoGoCAXuYADXVToxJ");
//        SeimiCookie seimiCookie3 = new SeimiCookie(".1688.com","/","ali_ab","123.152.76.237.1500617692281.4");
//        SeimiCookie seimiCookie4 = new SeimiCookie(".1688.com","/","isg","AnV1INA4QE7L0KT5-1bQgbE7hPHvWimDW9w2C_eaMew7zpXAqUI51INMbqSD");
//        SeimiCookie seimiCookie5 = new SeimiCookie(".1688.com","/","alicnweb","touch_tb_at%3D1501210757233");
//        SeimiCookie seimiCookie6 = new SeimiCookie(".1688.com","/","uss","UtBQpmw5GimtFFLDvGdKqeflHbWfpQIFVhuWe9wjtkB7zThzC6O7w5Ol");
//        SeimiCookie seimiCookie17 = new SeimiCookie(".1688.com","/","ali_apache_track","\"c_ms=1|c_mid=b2b-789811733 |c_lid=%E8%83%A1%E5%B9%BF%E8%BE%8900\"");
//        SeimiCookie seimiCookie16 = new SeimiCookie(".1688.com","/","_cn_slid","\"Hroi+vcPZP\"");
//        SeimiCookie seimiCookie9 = new SeimiCookie(".1688.com","/","last_mid","b2b-789811733");
//        SeimiCookie seimiCookie15 = new SeimiCookie(".1688.com","/"," __last_loginid__","\"%E8%83%A1%E5%B9%BF%E8%BE%8900\"");
//        SeimiCookie seimiCookie11 = new SeimiCookie(".1688.com","/","ad_prefer","\"2017/07/21 14:14:58\"");
//        SeimiCookie seimiCookie12 = new SeimiCookie(".1688.com","/","h_keys","\"%u624b%u8868\"");
//        SeimiCookie seimiCookie13 = new SeimiCookie(".1688.com","/","JSESSIONID","8L78bHuu1-053YMBaSqt0AKvd0FF-rcxadQQ-zLy9");
//        SeimiCookie seimiCookie14 = new SeimiCookie(".1688.com","/","_tmp_ck_0","\"5qZiWZ7kx3yUPYb0LhGwvhmtEXiRRjOOxKa6Y35TmNevOP40i0UEnVHMzwEUFJhpj5bKR8SUWuzPtkpVeLNTKm42RLiPLgBgk63t9P3nVDvF9ABGghT6VKH1utYu1EzA1CUilJ%2BgXm04hCsaAd4oeYoueovb3kWSjhgQCO%2BW1XszwU4wKNL2bOw%2F0mPsBGpOhSoolEopDgO1M7qRdgJ5b8qwd%2BKQ2IXZWiVtvzfKEUhZpyZS6yKboSqnX8kWaE4QNSAaFI3SNgjykUhGTF5xGqA4s1o8BKqzGp%2B10emhafsResMjNMD%2F677jL4zvVBUpP%2FicAsgj1jIi47ZW5%2Fw7gA%3D%3D\"");
//        SeimiCookie seimiCookie8 = new SeimiCookie(".1688.com","/","__sw_newuno_count__","2");
//        SeimiCookie seimiCookie7 = new SeimiCookie(".1688.com","/","__sw_ktsz_count__","1");
//        SeimiCookie seimiCookie10 = new SeimiCookie(".1688.com","/","alisw","swIs1200%3D1%7C");
//        cookies.add(seimiCookie1);
//        cookies.add(seimiCookie2);
//        cookies.add(seimiCookie3);
//        cookies.add(seimiCookie4);
//        cookies.add(seimiCookie5);
//        cookies.add(seimiCookie6);
//        cookies.add(seimiCookie7);
//        cookies.add(seimiCookie8);
//        cookies.add(seimiCookie9);
//        cookies.add(seimiCookie10);
//        cookies.add(seimiCookie11);
//        cookies.add(seimiCookie12);
//        cookies.add(seimiCookie13);
//        cookies.add(seimiCookie14);
//        cookies.add(seimiCookie15);
//        cookies.add(seimiCookie16);
//        cookies.add(seimiCookie17);

//        Map<String,String> map = new HashMap<>();
//        map.put("referer","https://www.1688.com/");
//        map.put("upgrade-insecure-requests","1");
//        map.put("authority","s.1688.com");
//        map.put("scheme","https");
//        map.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        map.put("accept-encoding","gzip, deflate, br");
//        map.put("accept-language","zh-CN,zh;q=0.8");
//        map.put("path","/selloffer/offer_search.htm?keywords=%C2%DE%C3%C9&n=y&spm=a260k.635.1998096057.d1");
//        map.put("cache-control","no-cache");
//        map.put("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36");
        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        request.setUseSeimiAgent(true);
        request.setSeimiAgentRenderTime(5000);
        request.setSeimiAgentUseCookie(true);
        request.setSkipDuplicateFilter(false);
        request.setUrl("https://s.1688.com/selloffer/offer_search.htm?keywords=%CA%D6%BB%FA%BF%C7&n=y&spm=a260k.635.1998096057.d1");
      //  request.setSeimiAgentScript("$(document).ready(function(){$(document).scrollTop($(document).height()-$(window).height());});");
        request.setCallBack("alibaba");

     //   request.setSeimiCookies(cookies);
      //  request.setHttpMethod(HttpMethod.GET);
      //  request.setHeader(map);
        requests.add(request);
        return requests;
    }

    public void alibaba(Response response) throws XpathSyntaxErrorException{

        JXDocument document = response.document();

       /*List<Object> sel = document.sel("//div[@class='s-widget-offershopwindowtitle sm-offer-title sw-dpl-offer-title sm-widget-offershopwindowtitle-onerow']/a[@t-click-item='title']/@href");
        for (Object o:sel) {
            String url = o.toString();
            System.out.println("结果---"+url);
        }*/

        List<Object> sel3 = document.sel("//em[@class='fui-paging-num']/text()");

        if (sel3!=null&& sel3.size()!=0){
            String string = sel3.get(0).toString();
            Integer pageNum = Integer.valueOf(string);
          //  for (int i = 1; i <= pageNum; i++) {
                String url = "https://s.1688.com/selloffer/offer_search.htm?keywords=%CA%D6%B1%ED&n=y&spm=a260k.635.1998096057.d1&beginPage="+1;
                Request request = new Request();
                request.setUseSeimiAgent(true);
                request.setSeimiAgentRenderTime(5000);
                request.setSeimiAgentUseCookie(true);
                request.setSkipDuplicateFilter(false);
                request.setUrl(url);
                request.setCallBack("getUrl");
                push(request);
         //   }

        }


        /*List<Object> sel = document.sel("//div[@class='sm-offer-trigger']/@data-mod-config");
        if (sel!=null&& sel.size()!=0){
            String string = sel.get(0).toString();
            JSONObject jsonObject = JSON.parseObject(string);
            System.out.println(jsonObject.get("url"));
            String url =
        }
*/


     /*   List<Object> sel1 = document.sel("//div[@class='sm-offer-trigger']/@data-mod-config");

        String string = sel1.get(0).toString();
        int https = string.indexOf("https");
        int market = string.indexOf("market\",");
        String url = string.substring(https,market)+"market";
        String replace = url.replace("asyncCount=20", "asyncCount=40");
        Request request= new Request();
        request.useSeimiAgent();
        request.setUrl(replace);
        request.setSeimiAgentUseCookie(true);
        request.setCallBack("abc");
        push(request);*/


     /*  List<Object> sel2 = document.sel("//div[@class='s-widget-offershopwindowtitle sm-offer-title sw-dpl-offer-title sm-widget-offershopwindowtitle-onerow']/a[@t-click-item='title']/@title");

        for (Object o:sel2) {

            System.out.println("名称---"+o.toString());
        }

         List<Object> sel1 = document.sel("//div[@class='s-widget-offershopwindowcompanyinfo sm-offer-company sw-dpl-offer-company']/a[@t-click-item='com']/text()");
        for (Object o:sel1) {

            System.out.println("店铺---"+o.toString());
        }
*/

    }

    public void getUrl(Response response) throws XpathSyntaxErrorException{

        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='sm-offer-trigger']/@data-mod-config");
        if (sel!=null&& sel.size()!=0){
            String string = sel.get(0).toString();
            JSONObject jsonObject = JSON.parseObject(string);
            String url = (String)jsonObject.get("url");
            url = url.replace("asyncCount=20", "asyncCount=60").replace("startIndex=20", "startIndex=0");
            Request request = new Request();
          /*  request.useSeimiAgent();
            request.setSeimiAgentRenderTime(10000);
            request.setSeimiAgentUseCookie(true);
            request.setSkipDuplicateFilter(false);*/
            request.setUrl(url);
            request.setCallBack("getShop");
            push(request);
        }
    }


    public void getShop(Response response)throws XpathSyntaxErrorException{

        ////div[@class='s-widget-offershopwindowtitle sm-offer-title sw-dpl-offer-title sm-widget-offershopwindowtitle-onerow']/a[@t-click-item='title']/@href
     //   JXDocument document = response.document();
      //  List<Object> sel = document.sel("//body");
        String responseContent = response.getContent();
       // String content = sel.get(0).toString();
        Pattern pattern = Pattern.compile("t-click-item=\\\\\"title\\\\\".*?target=\\\\\"_blank\\\\\"");
        Matcher match = pattern.matcher (responseContent);
        while (match.find()){
            String group = match.group();
            String substring = group.substring(group.indexOf("https"), group.indexOf("target")).replace("\\","");
            String url = substring.substring(0, substring.indexOf("\""));
            System.out.println(url);
            if (url.contains("amp;")){
                url = url.replace("amp;","");
            }
            Request request = new Request();
            request.setUrl(url);
            request.setCallBack("getData");
            request.setUseSeimiAgent(true);
            request.setSeimiAgentRenderTime(5000);
            request.setSeimiAgentUseCookie(true);
            push(request);
            break;

        }
    }

    public void getData(Response response) throws  Exception{
        JXDocument document = response.document();
        Request request = response.getRequest();
        String realUrl = response.getRealUrl();
        AlibabaDetail detail = response.render(AlibabaDetail.class);
        System.out.println(detail.getTitle());
        System.out.println(detail.getShop());
        System.out.println(detail.getDetail());


    }

    @Override
    public String seimiAgentHost() {
        return "192.168.111.128";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }


}
