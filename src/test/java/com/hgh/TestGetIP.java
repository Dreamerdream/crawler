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

public class TestGetIP {

    public static void main(String[] args) {
        HttpGet httpGet = new HttpGet("http://www.xsdaili.com/get?orderid=185981378376299&num=2000");
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
                    String[] split = content.split("\\r?\\n");
                }else {

                }
            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
