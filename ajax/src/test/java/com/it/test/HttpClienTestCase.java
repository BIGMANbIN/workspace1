package com.it.test;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpClienTestCase {

    @Test
    public void getDownloadImage() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://ww1.sinaimg.cn/mw690/48527d45jw1ek77nz2e3uj215o0rsqry.jpg");//("http://www.kaishengit.com");
        HttpResponse httpResponse = httpClient.execute(httpGet);

        //获取HTTP请求的相应码

        int httpCode = httpResponse.getStatusLine().getStatusCode();

        if(httpCode == 200){
            //获取图片字节流
            InputStream inputStream = httpResponse.getEntity().getContent();
            String html = IOUtils.toString(inputStream);
            System.out.println(html);
            FileOutputStream fileOutputStream = new FileOutputStream("E:/fengguang.jpg");

            IOUtils.copy(inputStream,fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }else{
            System.out.println("请求异常"+httpCode);
        }
        httpClient.close();
    }


    @Test
    public void getRequest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://blog.sina.com.cn/rss/1220218113.xml");//("http://www.kaishengit.com");
        HttpResponse httpResponse = httpClient.execute(httpGet);

        //获取HTTP请求的相应码

        int httpCode = httpResponse.getStatusLine().getStatusCode();

        if(httpCode == 200){
            //获取图片字节流
            InputStream inputStream = httpResponse.getEntity().getContent();
            String html = IOUtils.toString(inputStream);
            System.out.println(html);
        }else{
            System.out.println("请求异常" + httpCode);
        }
        httpClient.close();
    }
}
