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
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 验证IP的线程*/
public class VallidIPThread implements Runnable {
    private String[] IPs;
    Vector<String> vector = new Vector<>();

    public VallidIPThread(){

    }


    public VallidIPThread(String[] IPs){
        this.IPs = IPs;
    }

   /* @Override
    public Vector call() throws Exception {

        return vector;
    }*/


    public Vector<String> getVector() {
        return vector;
    }

    public void setVector(Vector<String> vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        for (int i = 0;i<IPs.length;i++){
            String ip = IPs[i];
            String[] split = ip.split(":");
            HttpHost host = new HttpHost(split[0],Integer.valueOf(split[1]));
            RequestConfig config = RequestConfig.custom().setProxy(host).build();
            HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
            httpGet.setConfig(config);
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response != null){
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == 200){
                        HttpEntity entity = response.getEntity();
                        String content = IOUtils.toString(entity.getContent(),"GBK");
                        if (content.contains(ip)){
                            vector.add(ip);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
