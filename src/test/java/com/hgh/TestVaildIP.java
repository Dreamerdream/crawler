package com.hgh;

import org.apache.commons.io.IOUtils;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TestVaildIP {
    private static final int BASE_THREAD_NUM = 2;
    public static AtomicInteger integer = new AtomicInteger(0);
    public static AtomicInteger integernext = new AtomicInteger(0);
    public static AtomicInteger integerfuture = new AtomicInteger(0);
    public static Vector<String> vector = new Vector<>();
    public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);
    private static ExecutorService service = Executors.newFixedThreadPool(200);
    public static void main(String[] args) {

        for (int i = 1; i <= 200; i++) {
            service.submit(new VaildIPs2());
        }
        getIPs();
    }

    public static void getIPs(){
        HttpGet httpGet = new HttpGet("http://ip.baizhongsou.com/?u=bsbsbs1&p=60827015e5d605ed&sl=200");
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null){
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    String content = IOUtils.toString(inputStream);
                    System.out.println(content);
                    String[] IPs = content.split("<br>");
                    System.out.println("IP个数-----"+IPs.length);
                    for (int i = 0; i <IPs.length; i++) {
                        //将IP放队列
                        queue.put(IPs[i]);
                    }
                    System.out.println("队列----------"+queue.size());
                }
            }else {
                System.out.println("队列----------"+queue.size());
            }
        } catch (Exception e) {
            System.out.println("队列----------"+queue.size());
            e.printStackTrace();
        }
    }


}
