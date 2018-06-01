package com.example.me07.teacher;

import android.util.Log;

import com.example.me07.AnalysisData;
import com.example.me07.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AnalysisTeacher extends AnalysisData {

	
	@Override
	public List<Course> data2(StringBuffer buffer){

		int start=buffer.indexOf("<select");
		int end=buffer.lastIndexOf("select>");
//		System.out.println(start+ " " +end);
		String html = buffer.substring(start, end+8);
//		System.out.println(html);	

		List<Course> list = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements nodes = doc.select("option");
		for(Element n:nodes) {
			Course t = new Course();
			t.setId(n.attr("value"));
			t.setName(n.text());
			list.add(t);
		}
		Log.i("name8",list.get(10).getName());
		return list;
	}
	
	
	
}
