package com.mzx.pptserver.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zison on 2016/1/17.
 */
public class FileToByte {

    public static byte[] FileToByte(String path) throws Exception{
        FileInputStream inputStream = null;
        inputStream = new FileInputStream(path);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(byffer)) != -1) {
            byteArrayOutputStream.write(byffer, 0, length);
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();

    }
}
