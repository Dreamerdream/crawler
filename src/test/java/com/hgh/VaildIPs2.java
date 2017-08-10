package com.hgh;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.Callable;


public class VaildIPs2 implements Runnable {
   /* @Override
    public String call() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String ip = TestVaildIP.queue.poll();
        if (!StringUtils.isBlank(ip)) {
            System.out.println("integernext----"+TestVaildIP.integernext.incrementAndGet());
            String[] split = ip.split(":");
            HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
            RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setProxy(host).build();
            HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
            httpGet.setConfig(config);
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                if (response != null) {
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine != null){
                        if (statusLine.getStatusCode() == 200) {
                            HttpEntity entity = response.getEntity();
                            String content = IOUtils.toString(entity.getContent(), "GBK");
                      *//*  if (content.contains(split[0])) {*//*
                            System.out.println(TestVaildIP.integer.incrementAndGet());
                            return ip;
                        *//*}*//*
                        }else {
                            System.out.println(TestVaildIP.integer.incrementAndGet());
                            return null;
                        }
                    }else {
                        System.out.println(TestVaildIP.integer.incrementAndGet());
                        return null;
                    }

                }else {
                    System.out.println(TestVaildIP.integer.incrementAndGet());
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(TestVaildIP.integer.incrementAndGet());
                return null;
            }
        }
        return null;
    }*/



    @Override
    public void run() {

        while (true){
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String ip = null;
            try {
                ip = TestVaildIP.queue.take();
            } catch (InterruptedException e) {
                System.out.println(TestVaildIP.integer.incrementAndGet());
                e.printStackTrace();
            }
            long id = Thread.currentThread().getId();
            if (!StringUtils.isBlank(ip)) {
                System.out.println("integernext----"+TestVaildIP.integernext.incrementAndGet());
                //验证代理IP
                String[] split = ip.split(":");
                HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setProxy(host).build();
                HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                httpGet.setConfig(config);
                CloseableHttpResponse response = null;
                try {
                    response = httpClient.execute(httpGet);
                    if (response != null) {
                        StatusLine statusLine = response.getStatusLine();
                        if (statusLine != null){
                            if (statusLine.getStatusCode() == 200) {
                                HttpEntity entity = response.getEntity();
                                String content = IOUtils.toString(entity.getContent(), "GBK");
                      /*  if (content.contains(split[0])) {*/
                                System.out.println(TestVaildIP.integer.incrementAndGet());
                        /*}*/
                            }else {
                                System.out.println(TestVaildIP.integer.incrementAndGet());
                            }
                        }else {
                            System.out.println(TestVaildIP.integer.incrementAndGet());
                        }

                    }else {
                        System.out.println(TestVaildIP.integer.incrementAndGet());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(TestVaildIP.integer.incrementAndGet());
                }
            }
        }

    }
}
