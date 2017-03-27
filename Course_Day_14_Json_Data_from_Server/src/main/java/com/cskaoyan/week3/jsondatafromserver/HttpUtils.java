package com.cskaoyan.week3.jsondatafromserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhao on 2017/3/28.
 */

public class HttpUtils {

    public static String getStringFromInputstream(InputStream is) {

        String ret = "";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer, 0, 1024)) != -1) {
                baos.write(buffer, 0, len);
            }

            byte[] bytes = baos.toByteArray();
            ret = new String(bytes, "utf-8");

            baos.close();
            //is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
