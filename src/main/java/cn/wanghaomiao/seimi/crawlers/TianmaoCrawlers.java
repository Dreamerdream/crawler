package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.seimi.util.VallidIPThread;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/7/18.
 */
@Crawler(name = "tianmaocrawlers",httpTimeOut = 30000)
public class TianmaoCrawlers extends BaseSeimiCrawler{
    private static final String URL = "https://list.tmall.com/search_product.htm?q=%CA%D6%B1%ED";
    private static final String PAGE_PARAMETER = "&s=";

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String[] proxy;
    private static final int NUM = 5;
    private static final int THREAD_NUM = 100;
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
            request.setUseSeimiAgent(true);
            request.setSeimiAgentRenderTime(5000);
            request.setSeimiAgentUseCookie(true);
            request.setUrl(URL);
            request.setCallBack("tianmao");
            requests.add(request);
            return requests;

    }


    public void tianmao(Response response) throws XpathSyntaxErrorException,InterruptedException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//form[@name='filterPageForm']/input[@name='totalPage']/@value");
        if (sel!=null && sel.size()!=0){
            String page = sel.get(0).toString();
            Integer pageNum = Integer.valueOf(page);
            for (int i = 0; i < pageNum; i++) {
                String url = URL + PAGE_PARAMETER + (i*60);
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

    public void getUrl(Response response) throws XpathSyntaxErrorException,InterruptedException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='productImg-wrap']/a/@href");
        if (sel!=null && sel.size()!=0){
            logger.info("总共---------" + sel.size());
            for (Object o:sel) {
                String url = o.toString();
                url = "https:"+url;
                logger.info("结果---"+url);
                Request request = new Request();
                request.setUseSeimiAgent(true);
                request.setSeimiAgentRenderTime(10000);
                request.setSeimiAgentUseCookie(true);
                request.setUrl(url);
                request.setCallBack("getData");
                push(request);
            }
        }

    }

    public void getData(Response response) throws XpathSyntaxErrorException{

        JXDocument document = response.document();
        List<Object> sel = document.sel("//ul[@id='J_AttrUL']/li");
        if (sel!=null&&sel.size()!=0){
            for (int i=0;i<sel.size();i++) {
                logger.info(i+"----------"+"信息---"+sel.get(i).toString());
            }
        }
    }

    @Override
    public String seimiAgentHost() {
        return "192.168.111.128";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }

    public String[] getProxy(){
        HttpGet httpGet = new HttpGet("http://www.xsdaili.com/get?orderid=185981378376299&num=2000");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null){
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200){

                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    String content = IOUtils.toString(inputStream);
                    System.out.println(content);
                    String[] IPs = content.split("\\r?\\n");
                    return IPs;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String proxy() {
       // Vector vector = null;


       /* for (int i=0;i<proxies.length;i++){
           boolean flag = validIp(proxies[i]);
           if (flag){
               return proxies[i];
           }else {
               continue;
           }
        }*/
      // proxy.length
        String[] proxy = new String[]{
                "183.222.102.101:80",
                "183.222.102.97:8080",
                "219.76.4.72:88",
                "210.38.1.140:8080",
                "120.83.13.53:8080",
                "183.222.102.105",
                "120.199.224.78:8080",
                "111.1.3.38:8000",
                "122.13.15.65:8080",
                "106.119.0.244:8080",
                "171.10.31.41:8080",
                "120.77.203.202:8888",
                "183.222.102.97:8080",
                "219.76.4.12:88",
                "219.76.4.72:88",
                "183.222.102.95:80",
                "183.222.102.96:80",
                "182.92.81.200:8080",
                "121.30.197.38:8080",
                "183.222.102.99:80",
                "120.77.182.203:8888",
                "183.222.102.100:80",
                "106.119.0.245:80",
                "183.222.102.100:80",
                "183.222.102.108:8080",
                "183.222.102.98:8080",
                "111.62.251.24:80",

        };
        return proxy[RandomUtils.nextInt(0,proxy.length)];

    }


    public boolean validIp(String IP){
        boolean flag = false;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String[] split = IP.split(":");
        HttpHost host = new HttpHost(split[0],Integer.valueOf(split[1]));
        RequestConfig config = RequestConfig.custom().setProxy(host).build();
        HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        httpGet.setConfig(config);
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response != null){
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == 200){
                        HttpEntity entity = response.getEntity();
                        String content = IOUtils.toString(entity.getContent(),"GBK");
                        if (content.contains(IP)){
                            flag = true;
                        }else {
                            flag = false;
                        }
                    }else {
                        flag = false;
                    }
                }else {
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }

        return flag;
    }


    @Override
    public void handleErrorRequest(Request request) {

        System.out.println(request.getUrl());
        System.out.println(request.getSeimiAgentRenderTime());
        System.out.println(request.getSeimiAgentContentType());
        System.out.println(request.getHttpMethod());
        System.out.println(request.getMaxReqCount());
        System.out.println(request.getCallBack());
    }

}
