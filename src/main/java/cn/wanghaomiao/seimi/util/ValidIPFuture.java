package cn.wanghaomiao.seimi.util;

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

public class ValidIPFuture implements Runnable {

    private String[] IPs;

    public ValidIPFuture() {

    }

    public ValidIPFuture(String[] IPs) {
        this.IPs = IPs;
    }

    @Override
    public void run() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        for (int i = 0; i < IPs.length; i++) {
            String ip = IPs[i];
            if (!StringUtils.isBlank(ip)) {
                String[] split = ip.split(":");
                HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                RequestConfig config = RequestConfig.custom().setProxy(host).build();
                HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                httpGet.setConfig(config);
                CloseableHttpResponse response = null;
                try {
                    response = httpClient.execute(httpGet);

                    if (response != null) {
                        StatusLine statusLine = response.getStatusLine();
                        if (statusLine.getStatusCode() == 200) {
                            HttpEntity entity = response.getEntity();
                            String content = null;
                            try {
                                content = IOUtils.toString(entity.getContent(), "GBK");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (content.contains(split[0])) {
                                System.out.println(ip);

                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*@Override
    public Object call() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        for (int i = 0; i < IPs.length; i++) {
            String ip = IPs[i];
            if (!StringUtils.isBlank(ip)) {
                String[] split = ip.split(":");
                HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                RequestConfig config = RequestConfig.custom().setProxy(host).build();
                HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                httpGet.setConfig(config);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response != null) {
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        String content = IOUtils.toString(entity.getContent(), "GBK");
                        if (content.contains(split[0])) {
                            System.out.println(ip);
                            vector.add(ip);
                            System.out.println(vector.size());
                        }
                    }
                }
            }
        }
        return vector;
    }*/
}
