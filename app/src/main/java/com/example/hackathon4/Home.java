package com.example.hackathon4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button home, calendar, setting;
        TextView mission, textView2;

        home = (Button) findViewById(R.id.home);
        calendar = (Button) findViewById(R.id.calendar);
        setting = (Button) findViewById(R.id.setting);

        ImageButton arrow1;
        arrow1 = (ImageButton)findViewById(R.id.arrow1);
        mission = (TextView) findViewById(R.id.mission);
        textView2 = (TextView) findViewById(R.id.textView2);
        String daliyMission = null;

        String missionList[] = { "하늘 보기", "길에 있는 쓰레기 하나 줍기",
                "명상 5분", "스트레칭 5분", "한끼 만들어 먹기", "기지개 펴기", "주변 사람들에게 칭찬 한마디 하기"
                , "책 10 페이지 읽기", "돈 저금하기", "음료(차, 커피) 한 잔의 여유 가지기", "운동하기 10분"
                , "달달한 디저트 먹기", "가장 좋아하는 곡 듣기", "부모님께 사랑한다 하기", "부모님께 전화하기"
                , "스트레스 풀기 (자신만의 방법으로)", "15분 일찍 자보기", "책 한줄 적기", "오늘의 일기 쓰기"
                , "오늘 목표 정하기", "주변 정리(청소)하기", "친구에게 노래 추천하기", "친구에게 책 추천하기"
                , "친구에게 영화 추천하기", "친구와 전화해서 수다떨기", "부모님께 취미 말하기", "눈뜨지마자 물 마시기"
                , "물 3컵 이상 마시기", "스스로에게 잘하고 있다고 말하기", "산책 10분", "주변 사진찍기"
                , "셀카 찍기", "감사한일 3가지 적기", "자신의 좌우명 적기", "영어 단어 3개 외우기"
                , "영어 속담 한개 적기", "속담 하나 적기", "사자성어 알아보기", "뉴스 기사 하나 읽기"
                , "한붓그리기 해보기", "타자 연습하기", "3분 크로키 해보기", "한 문장 작문하기"
                , "코드한줄 따라 적어보기", "자기가 좋아하는 향 찾아보기", "좋아하는 단어 정성스럽게 적어보기"
                , "옷장 정리하기", "여행 다큐 찾아보기", "새로운 음식 도전하기", "역사적 사건 조사하기"
                , "역사적 인물 조사하기", "드라마나 영화 연기 따라 해보기", "노래 한곡 부르기"
                , "근의 공식 찾아보기", "원소 주기율표 13족 3주기에 해당하는 원소 찾아보기"
                , "오늘 먹은 음식 재료 분석하기", "오늘 걸음 수 확인하기", "사자성어 청출어람(한자) 뜻 찾아보기"
                , "오늘 지출 내역 적어보기", "지금 생각나는 사람 적어보기", "가고싶은 여행지 적어보기"
                , "좋아하는 음식과 그 이유를 적어보기", "오늘 한 착한일 적어보기"};

        SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd", Locale.KOREAN);
        String saveName = sdf2.format (System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREAN);
        String time = sdf2.format (System.currentTimeMillis());

        SharedPreferences pref = getSharedPreferences("time", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String originalDate = pref.getString("time", "first"); // 앱이 처음 시작됐을 때도 적용될 수 있게

        SharedPreferences mission_pref = getSharedPreferences("mission", Activity.MODE_PRIVATE);
        SharedPreferences.Editor mission_editor = mission_pref.edit();

        if(time.equals(originalDate)){ //날짜가 바뀌지 않았을때
            mission.setText(mission_pref.getString("mission", "default"));
        }else{ //날짜가 바뀌었을때
            int randomNum = 0;
            randomNum = (int) (Math.random() * 69);
            mission_editor.putString("mission", missionList[randomNum]);
            mission_editor.apply();
            mission.setText(mission_pref.getString("mission", "default"));
            editor.putString("time", time);
            editor.apply();
        }

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mIntent = new Intent(Home.this, Calendar.class);
                        mIntent.putExtra("mission", mission_pref.getString("mission", "default"));
                        startActivity(mIntent);
                        finish();
                    }
                },1000);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(Home.this, Setting.class);
                startActivity(mIntent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(Home.this, Calendar.class);
                startActivity(mIntent);
                finish();
            }
        });
    }
}
