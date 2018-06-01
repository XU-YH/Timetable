package com.example.me07.teacher;

import android.os.Environment;
import android.util.Log;

import com.example.me07.RetrieveData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 胥尹辉 on 2018/1/13.
 */

public class ImgCookie extends RetrieveData {
    public ImgCookie(String path, String cookie, String referer, String params) {
        super(path, cookie, referer, params);
    }

    public StringBuffer data() throws Exception{
        String  sdCardDir = Environment.getExternalStorageDirectory()+ "/YzmImage/";
        File dirFile  = new File(sdCardDir);  //目录转化成文件夹
        if (!dirFile .exists()) {              //如果不存在，那就建立这个文件夹
            dirFile .mkdirs();
        }                          //文件夹有啦，就可以保存图片啦
        File file = new File(sdCardDir, "verimg.png");
        URL url=new URL(path);
        HttpURLConnection con=(HttpURLConnection)url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Referer", referer);
        OutputStream out=con.getOutputStream();
        out.write(params.getBytes());
        out.flush();

        //得到cookie
        String s = con.getHeaderField("Set-Cookie");
        int index = s.indexOf(";");
        StringBuffer coo = new StringBuffer(s.substring(0, index));
        Log.i("cookie", coo.toString());

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.connect();
        if(con.getResponseCode() == 200) {
            //获取服务器响应头中的流
            InputStream in=con.getInputStream();
            //读取服务器返回流里的数据，把数据写入到本地，缓冲起来
            FileOutputStream fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            in.close();
        }
        out.close();
        con.disconnect();

        return coo;
    }
}
