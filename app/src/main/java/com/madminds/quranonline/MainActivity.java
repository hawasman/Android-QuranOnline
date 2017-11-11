package com.madminds.quranonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView audioName;
    ImageView forward,rewind,play;
    RecyclerView recyclerMain;
    List<QItem> qItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qItemList.add(new QItem("الفاتحة","http://server12.mp3quran.net/m_krm/001.mp3"));
        qItemList.add(new QItem("البقرة","http://server12.mp3quran.net/m_krm/002.mp3"));
        qItemList.add(new QItem("ال عمران","http://server12.mp3quran.net/m_krm/003.mp3"));
        audioName = (TextView)findViewById(R.id.audio_name);
        forward = (ImageView) findViewById(R.id.forward);
        rewind = (ImageView)findViewById(R.id.rewind);
        play = (ImageView)findViewById(R.id.play);
        recyclerMain = (RecyclerView)findViewById(R.id.recycler_main);
        recyclerMain.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(this,qItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMain.setLayoutManager(layoutManager);
        recyclerMain.setAdapter(myAdapter);
    }
}
