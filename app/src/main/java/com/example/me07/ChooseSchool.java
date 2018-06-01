package com.example.me07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 胥尹辉 on 2018/1/11.
 */

public class ChooseSchool extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_school);

        Button btn = (Button) findViewById(R.id.btn_sch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSchool.this, ChooseSchedule.class);
                startActivity(intent);
            }
        });
    }
}
