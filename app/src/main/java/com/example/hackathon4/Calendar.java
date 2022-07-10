package com.example.hackathon4;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {
    public String readDay = null;
    public String str = null;
    public CalendarView calendarView;
    public Button cha_Btn, del_Btn, save_Btn;
    public TextView diaryTextView, textView2, textView3;
    public EditText contextEditText;
    public ImageView calImg;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        diaryTextView = findViewById(R.id.diaryTextView);
        save_Btn = findViewById(R.id.save_Btn);
        del_Btn = findViewById(R.id.del_Btn);
        cha_Btn = findViewById(R.id.cha_Btn);
        textView2 = findViewById(R.id.textView2);
        contextEditText = findViewById(R.id.contextEditText);
        calImg = findViewById(R.id.calImg);
        image = findViewById(R.id.image);

        Button home, calendar, setting;

        home = (Button) findViewById(R.id.home);
        calendar = (Button) findViewById(R.id.calendar);
        setting = (Button) findViewById(R.id.setting);

        Intent intent = getIntent();
        String daliyMission = intent.getStringExtra("mission");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
        String saveName = sdf.format (System.currentTimeMillis());
        String time = sdf.format (System.currentTimeMillis());

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Calendar.this, Setting.class);
                startActivity(mIntent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Calendar.this, Home.class);
                startActivity(mIntent);
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                diaryTextView.setVisibility(View.INVISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                calImg.setVisibility(View.INVISIBLE);
                image.setVisibility(View.INVISIBLE);

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                Date date1 = null, date2 = null;
                try {
                    date1 = sdf.parse(strDate); // 클릭 날짜
                    date2 = sdf.parse(time); // 현재 날짜
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int value = date1.compareTo(date2);
                if (value > 0) { //미래
                    diaryTextView.setVisibility(View.INVISIBLE);
                    save_Btn.setVisibility(View.INVISIBLE);
                    contextEditText.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    calImg.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.INVISIBLE);
                } else if(value == 0){ //현재
                    diaryTextView.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    calImg.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.INVISIBLE);
                }

                diaryTextView.setText(daliyMission);
                Log.d("TEST", "Cal_mission: " + daliyMission);
                contextEditText.setText("");
                checkDay(year, month, dayOfMonth);
            }
        });

        save_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveDiary(readDay);
                str = contextEditText.getText().toString();
                textView2.setText(str);
                diaryTextView.setText(daliyMission);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);
                calImg.setVisibility(View.VISIBLE);
                image.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void checkDay(int cYear, int cMonth, int cDay)
    {
        readDay = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fis;

        try
        {
            fis = openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);
            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);
            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);
            calImg.setVisibility(View.VISIBLE);
            image.setVisibility(View.INVISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                    calImg.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.INVISIBLE);
                }
            });

            del_Btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    calImg.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.INVISIBLE);
                    removeDiary(readDay);
                }
            });

            if (textView2.getText() == null)
            {
                textView2.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                calImg.setVisibility(View.INVISIBLE);
                image.setVisibility(View.INVISIBLE);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay)
    {
        FileOutputStream fos;
        try
        {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay)
    {
        FileOutputStream fos;
        try
        {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = contextEditText.getText().toString();
            if (content.trim().length() < 30) {
                fos.write((content).getBytes());
                fos.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
