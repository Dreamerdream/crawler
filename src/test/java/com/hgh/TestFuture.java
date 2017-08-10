package com.hgh;

import cn.wanghaomiao.seimi.util.ValidIPFuture;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFuture {


    private static final int NUM = 20;
  //  private static String[] ips ;
    public static final AtomicInteger inter = new AtomicInteger(0);
    private static ExecutorService service = Executors.newFixedThreadPool(100);
    public static void main(String[] args) {


        String[] IPs = TestFuture.getIP();
        System.out.println(IPs.length);
        int count = IPs.length / NUM;

        if ((IPs.length%NUM) == 0){
            for (int i=0;i<NUM;i++){
                String[] ips = new String[count];
                for (int j = 0; j < count; j++) {
                    ips[j]=IPs[(count*i)+j];
                }
                System.out.println(ips.length);
                TestFuture.handleFuture(ips);
            }
        }else {
            for (int i=0;i<(NUM+1);i++){
                String[] ips = new String[count];
                for (int j = 0; j < count; j++) {
                    if (((count*i)+j)>=IPs.length){
                        break;
                    }
                    ips[j]=IPs[(count*i)+j];
                }
                TestFuture.handleFuture(ips);

            }
        }


       /* for (int i = 0; i <10 ; i++) {

            ips[i] = IPs[i];
        }
        ExecutorService service = Executors.newFixedThreadPool(5);
        ValidIPFuture future = new ValidIPFuture(ips);
        Future submit = service.submit(future);
        try {
            Vector o = (Vector)submit.get();
            System.out.println(o.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/
    }

    public static String[] getIP(){
        HttpGet httpGet = new HttpGet("http://ip.baizhongsou.com/?u=bsbsbs1&p=60827015e5d605ed&sl=1000");
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
                    return IPs;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void handleFuture(String[] ips){
        service.submit(new ValidIPFuture(ips));
    }
}
