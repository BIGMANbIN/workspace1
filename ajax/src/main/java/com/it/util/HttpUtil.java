package com.it.util;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpUtil {

    public static String getRequestText(String url) {

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpget);

            //获取HTTP请求的相应码

            int httpCode = httpResponse.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                String html = IOUtils.toString(inputStream);
                return html;
            } else {
                throw new RuntimeException("请求异常：" + httpCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
        }
    }




    public static void getRequestStream(String url,String savePath) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(url);//("http://www.kaishengit.com");
            HttpResponse httpResponse = httpClient.execute(httpGet);

            //获取HTTP请求的相应码

            int httpCode = httpResponse.getStatusLine().getStatusCode();

            if(httpCode == 200){
                //获取图片字节流
                InputStream inputStream = httpResponse.getEntity().getContent();
                FileOutputStream fileOutputStream = new FileOutputStream(savePath);
                IOUtils.copy(inputStream,fileOutputStream);

                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }else{
                throw new RuntimeException("请求服务器异常" + httpCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
} finally {
        try {
        httpClient.close();
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
        }
        }


        }