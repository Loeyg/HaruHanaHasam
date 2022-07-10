package com.example.hackathon4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button home, calendar, setting1;

        home = (Button) findViewById(R.id.home);
        calendar = (Button) findViewById(R.id.calendar);
        setting1 = (Button) findViewById(R.id.setting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Setting.this, Home.class);
                startActivity(mIntent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Setting.this, Calendar.class);
                startActivity(mIntent);
                finish();
            }
        });

        final String[] setting = {"계정", "화면", "알림", "공지사항", "문의하기", "버전", "로그아웃"};

        ListView list = (ListView) findViewById(R.id.listView1);
        Typeface font = ResourcesCompat.getFont(this, R.font.font1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, setting);
        list.setAdapter(adapter);
    }
}
