package com.cskaoyan.week3.networkdemo1app.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhao on 2017/3/27.
 */

public class HttpUtils {

    public static String getStringFromInpustStream(InputStream inputStream) throws IOException {

        String response = "";
        //ByteArrayOutputStream 内存的输出流  输出到内存的一个buffer（字节数组）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
            baos.write(buffer, 0, len);
        }

        //把一个buffer的数据，生成一个字符串
        //没有说明默认用utf-8
        //方法1
        // response = baos.toString("GBK");

        //方法2
        byte[] bytes = baos.toByteArray();
        response = new String(bytes, "GBK");

        inputStream.close();
        baos.close();

        return response;
    }
}
