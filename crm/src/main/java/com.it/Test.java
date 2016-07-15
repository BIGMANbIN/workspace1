package com.it;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;


public class Test {

    /*public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("123456"));
    }*/

    //二维码
    public static void main(String[] args) throws WriterException, IOException {

        String mecard = "MECARD:N:牛牛;ORG:山西国瑞投资有限公司;TEL:15188499999;EMAIL:410522431;ADR:山西太原;;";

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(mecard, BarcodeFormat.QR_CODE,300,300,hints);


        MatrixToImageWriter.writeToStream(bitMatrix,"png",new FileOutputStream("E:/qr.png"));


    }

}
