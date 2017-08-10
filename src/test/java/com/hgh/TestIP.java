package com.hgh;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TestIP {

    private static final String ADDRESS = "94.177.218.210";
    private static final int PROT = 3128;

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHost host = new HttpHost(ADDRESS, PROT);
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
                    if (content.contains(ADDRESS)){
                        System.out.println(content);
                    }
                    System.out.println(content);
                }else {
                    System.out.println("GG");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }

    }



}
