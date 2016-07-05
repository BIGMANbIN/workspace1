package com.it.test;


import com.it.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JSoupTestCase {

    @Test
    public void testGetImage() throws Exception {


            Document document = Jsoup.connect("http://www.topit.me/tag/风景？p=5").cookie("is_click","1").get();
            //找元素

            Elements elements = document.select("#content .catalog .e>a");
            //拿到对应大图超链接
            for (Element element : elements) {
                String href = element.attr("href");//href属性
                //System.out.println("href:" + href);
                //请求大图片链接地址
                Document bigImageDoc = Jsoup.connect(href).cookie("is_click","1").get();
                Element imgElement = bigImageDoc.select("#content>a").first();
                String imgSrc = imgElement.attr("href");

                System.out.println(imgSrc);

                String fileName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
                HttpUtil.getRequestStream(imgSrc, "E:/upload/" + fileName);

                Thread.sleep(3000);
            }

    }
}
