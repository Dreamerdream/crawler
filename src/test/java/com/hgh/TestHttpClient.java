package com.hgh;

import cn.wanghaomiao.seimi.util.ValidIP;
import cn.wanghaomiao.seimi.util.VallidIPThread;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHttpClient {

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String[] proxy;

    public static void main(String[] args) {
/*
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("https://s.1688.com/selloffer/offer_search.htm?keywords=手表");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


        ExecutorService service = Executors.newFixedThreadPool(200);
        TestHttpClient httpClient = new TestHttpClient();
        String[] proxy = httpClient.getProxy();
        System.out.println(proxy.length);
        for (int i=0;i<proxy.length;i++){
            service.execute(new ValidIP(proxy[i]));
        }
     //   System.out.println("Game Over");

    }
/* public  void text(){
        ExecutorService service = Executors.newFixedThreadPool(5);
        String[] a = new String[]{};
        for (int i=0;i<400;i++){
            a[i]=proxy[i];
        }
        String[] b = new String[]{};
        for (int i=400;i<800;i++){
            a[i]=proxy[i];
        }
        String[] c = new String[]{};
        for (int i=800;i<1200;i++){
            a[i]=proxy[i];
        }
        String[] d = new String[]{};
        for (int i=1200;i<1600;i++){
            a[i]=proxy[i];
        }
        String[] e = new String[]{};
        for (int i=1600;i<proxy.length;i++){
            a[i]=proxy[i];
        }
        service.submit(new VallidIPThread(a));
        service.submit(new VallidIPThread(b));
        service.submit(new VallidIPThread(c));
        service.submit(new VallidIPThread(d));
        service.submit(new VallidIPThread(e));
    }*/


    public String[] getProxy(){
        HttpGet httpGet = new HttpGet("http://ip.baizhongsou.com/?u=bsbsbs1&p=60827015e5d605ed&sl=1000");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null){
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    String content = IOUtils.toString(inputStream);
                    String[] IPs = content.split("<br>");
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


}
