package com.example.me07.course;

import android.util.Log;

import com.example.me07.AnalysisData;
import com.example.me07.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AnalysisCourse extends AnalysisData {

	@Override
	public List<Course> data2(StringBuffer buffer) {
		String html = buffer.toString();
		Log.i("cou1", html);
		List<String> list1 = new ArrayList<>();
		List<Course> list2 = new ArrayList<>();

		Document doc = Jsoup.parse(html);
//		Elements nodes = doc.select("td");
//		int i = 0;
//		for(Element n:nodes) {
//			if (i > 13) {
//
//                if (i == 21 || i == 29 || i == 30 || i == 38 || i == 46 || i == 55) {
//                } else {
//                    Course t = new Course();
//                    String cou = n.text();
//                    if(cou.equals("注1：")){
//					}else{
//						t.setCou(cou);
//						list2.add(t);
//					}
//				}
//            }
//			i++;
//		}
		//第二种解析方法
		Elements nodes = doc.select("table table tr td");
		Elements nodes2 = doc.select("script");
		//判断验证码是否错误
		if(!nodes2.isEmpty()){

			Log.i("cou10","验证码错误");
		}
		//判断教师是否有课
		if (nodes.isEmpty()){
			Log.i("cou3","--------没有cou3-----------");
			for (int i = 0;i < 35; i++){
				Course t = new Course();
				t.setCou("");
				list2.add(t);
			}
		}else{
			for (Element n:nodes){
				list1.add(n.text());
			}
			String s;
			for (int i = 0;i < list1.size(); i++){
				s = list1.get(i);
				if (s.equals("一") || s.equals("二") || s.equals("三") || s.equals("四") || s.equals("五") || s.equals("六")){
					for (int j = i + 1;j < i + 8;j++){
						Course t = new Course();
						String s1 = list1.get(j);
						t.setCou(s1);
						list2.add(t);
					}
					i = i+7;
				}
			}
		}

		return list2;
	}
}
