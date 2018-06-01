package com.example.me07.course;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.example.me07.AnalysisData;
import com.example.me07.Course;
import com.example.me07.R;
import com.example.me07.RetrieveData;
import com.example.me07.teacher.ChooseTeacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胥尹辉 on 2018/1/11.
 */

public class Curriculum extends AppCompatActivity {
    private String cookie2 = ChooseTeacher.cookie;
    private String teacherid2 = ChooseTeacher.teacherid;
    private String vercode2 = ChooseTeacher.vercode;
    private String teachername2 = ChooseTeacher.teachername;

    List<Course> courselist;
    List<String> courselist2 = new ArrayList<String>();
    Handler handler;
    private GridView gridView;
    private TextView teaname;
    GridviewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curriculum);
        Log.i("course1", cookie2);
        Log.i("course2", teacherid2);
        Log.i("course3", vercode2);
        Log.i("course3", vercode2);

        getcourse();
        teaname = findViewById(R.id.teacher_name);
        teaname.setText(teachername2);
        gridView = (GridView) findViewById(R.id.grid_course);
        handler = new Handler(){
            public void handleMessage(Message msg) {
                gridView.setAdapter(adapter);
                Log.i("course6", courselist.get(4).getCou());
                Log.i("course7", courselist.get(3).getCou());
            }
        };
        adapter = new GridviewAdapter(Curriculum.this, courselist2);
    }

    private void getcourse() {
        new Thread(){
            @Override
            public void run() {
                try {
                    //获取课程数据
                    RetrieveData course1;
                    course1 = new RetrieveCourse("http://jw.hnpolice.com:90/ZNPK/TeacherKBFB_rpt.aspx", cookie2, "http://jw.hnpolice.com:90/ZNPK/TeacherKBFB.aspx", "Sel_XNXQ=20170&Sel_JS="+teacherid2+"&type=1&txt_yzm="+vercode2);
                    StringBuffer coursedata = course1.data();
                    //解析课程数据
                    AnalysisData course2;
                    course2 = new AnalysisCourse();
                    courselist = course2.data2(coursedata);
                    int i = 0;
                    for (Course t:courselist){
                        courselist2.add(t.getCou());
                        i++;
                    }
                    Log.i("course4", "----------------");
                    Log.i("course5", courselist2.get(4));
                    Message message = Message.obtain();
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}