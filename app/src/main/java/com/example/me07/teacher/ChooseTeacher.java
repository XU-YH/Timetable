package com.example.me07.teacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.me07.AnalysisData;
import com.example.me07.Course;
import com.example.me07.R;
import com.example.me07.RetrieveData;
import com.example.me07.course.Curriculum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胥尹辉 on 2018/1/11.
 */

public class ChooseTeacher extends AppCompatActivity {

    List<Course> teacherlist;
    private Spinner spinnerteacher;
    private ArrayAdapter<String> adapter;
    Handler handler1;
    Handler handler2;
    List<String> teachernamelist = new ArrayList<String>();
    List<String> teacheridlist = new ArrayList<String>();
    private EditText ver_code;
    public static String cookie;
    public static String teachername;
    public static String teacherid;
    public static String vercode;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_teacher);

        //教师姓名
        teaname();
        spinnerteacher = (Spinner)findViewById(R.id.spinner_teacher);
        handler1 = new Handler(){
            public void handleMessage(Message msg) {
                if (adapter!=null) {
                    spinnerteacher.setAdapter(adapter);
                }
            }
        };
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,teachernamelist);
        spinnerteacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                teachername = adapter.getItem(pos);
                teacherid = teacheridlist.get(pos).toString();
                Log.i("teachername", teachername);
                Log.i("teacherid", teacherid);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //验证码和cookie
        imgcookie();
        handler2 = new Handler(){
            public void handleMessage(Message msg) {
                ImageView imageView = (ImageView) findViewById(R.id.ver_img);
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
        };
        ver_code = (EditText)findViewById(R.id.ver_code);


        Button btn =  (Button)findViewById(R.id.btn_cou);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Intent intent = new Intent(ChooseTeacher.this, Curriculum.class);
                startActivity(intent);
                vercode= ver_code.getText().toString();
                Log.i("teavercode", vercode);
            }
        });
    }

    //得到教师姓名
    private void teaname() {
        StringBuffer teacherdata;

        new Thread(){
            @Override
            public void run() {
                try {
                    //获取老师数据
                    RetrieveData teacher;
                    teacher = new RetrieveTeacher("http://jw.hnpolice.com:90/ZNPK/Private/List_JS.aspx?x", null, "http://jw.hnpolice.com:90/ZNPK/TeacherKBFB.aspx", "xnxq=20170&t=466");
                    StringBuffer teacherdata = teacher.data();
                    //解析老师数据
                    AnalysisData teacher2;
                    teacher2 = new AnalysisTeacher();
                    teacherlist = teacher2.data2(teacherdata);
                    int i = 0;
                    for (Course t:teacherlist){
                        teachernamelist.add(t.getName());
                        teacheridlist.add(t.getId());
                        i++;
                    }
                    Message message1 = Message.obtain();
                    handler1.sendMessage(message1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //验证码和cookie
    private void imgcookie() {
        StringBuffer teacherdata;
        new Thread(){
            @Override
            public void run() {
                try {
                    RetrieveData imgcoo;
                    imgcoo = new ImgCookie("http://jw.hnpolice.com:90/sys/ValidateCode.aspx?t=340", null, "http://jw.hnpolice.com:90/ZNPK/TeacherKBFB.aspx", "t=340");
                    StringBuffer coo = imgcoo.data();
                    cookie = coo.toString();
                    Log.i("teacookie", cookie);

                    String  verimg_address = Environment.getExternalStorageDirectory()+ "/YzmImage/verimg.png";
                    Message msg2 = handler2.obtainMessage();
                    Bitmap bm = BitmapFactory.decodeFile(verimg_address);
                    msg2.obj = bm;
                    handler2.sendMessage(msg2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
