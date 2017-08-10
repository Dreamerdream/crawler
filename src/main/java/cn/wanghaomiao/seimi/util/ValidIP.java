package cn.wanghaomiao.seimi.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ValidIP implements Runnable {
    private String ip;
    private static AtomicInteger fail = new AtomicInteger(0);
    private static AtomicInteger success = new AtomicInteger(0);
    Vector<String> vector = new Vector<>();

    public ValidIP() {

    }

    public ValidIP(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String[] split = ip.split(":");
        HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
        RequestConfig config = RequestConfig.custom().setProxy(host).build();
        HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
        httpGet.setConfig(config);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    String content = IOUtils.toString(entity.getContent(), "GBK");
                    if (content.contains(split[0])){
                        System.out.println(split[0]);
                        int i = success.incrementAndGet();
                     //   System.out.println("成功----"+i);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //int andIncrement = fail.incrementAndGet();
           //System.out.println("失败----"+andIncrement);
        }

    }
}
