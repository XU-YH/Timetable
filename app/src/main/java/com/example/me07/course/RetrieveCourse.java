package com.example.me07.course;

import com.example.me07.RetrieveData;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveCourse extends RetrieveData {
	public RetrieveCourse(String path, String cookie, String referer, String params) {
		super(path, cookie, referer, params);
	}

	public StringBuffer data() throws Exception{
		URL url=new URL(path);
		HttpURLConnection con=(HttpURLConnection)url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Cookie", cookie);
		con.setRequestProperty("Referer", referer);
		OutputStream out=con.getOutputStream();
		out.write(params.getBytes());
		out.flush();
		InputStream in=con.getInputStream();
		StringBuffer buffer=new StringBuffer();
		byte[] buf=new byte[1024];
		int len=0;
		while((len = in.read(buf)) != -1){
			 String html = new String(buf, 0, len, "gbk");
			 buffer.append(html);
		}		
		in.close();
		out.close();
		con.disconnect();
		
		return buffer;
		
	}
}
