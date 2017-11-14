package com.madminds.quranonline;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    int index = 0;
    TextView audioName;
    ImageView forward, rewind, play;
    RecyclerView recyclerMain;
    List<QItem> qItemList = new ArrayList<>();
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qItemList.add(new QItem("الفاتحة", "http://server12.mp3quran.net/m_krm/001.mp3"));
        qItemList.add(new QItem("البقرة", "http://server12.mp3quran.net/m_krm/002.mp3"));
        qItemList.add(new QItem("ال عمران", "http://server12.mp3quran.net/m_krm/003.mp3"));
        audioName = (TextView) findViewById(R.id.name_text);
        forward = (ImageView) findViewById(R.id.forward);
        rewind = (ImageView) findViewById(R.id.rewind);
        play = (ImageView) findViewById(R.id.play);
        recyclerMain = (RecyclerView) findViewById(R.id.recycler_main);
        assert recyclerMain != null;
        recyclerMain.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(this, qItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMain.setLayoutManager(layoutManager);
        recyclerMain.setAdapter(myAdapter);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play.setImageResource(R.drawable.ic_play);
            }
        });
        myAdapter.setClickListener(this);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePlayPause();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index >= 0 && index < qItemList.size() - 1) ++index;

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                try {
                    audioName.setText(qItemList.get(index).getName());
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    mMediaPlayer.setDataSource(qItemList.get(index).getUrl());
                    Toast.makeText(MainActivity.this, qItemList.get(index).getUrl(), Toast.LENGTH_SHORT).show();
                    mMediaPlayer.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index >= 0 && index < qItemList.size() - 1) --index;

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                try {
                    audioName.setText(qItemList.get(index).getName());
                    play.setImageResource(R.drawable.ic_pause_black_24dp);
                    mMediaPlayer.setDataSource(qItemList.get(index).getUrl());
                    Toast.makeText(MainActivity.this, qItemList.get(index).getUrl(), Toast.LENGTH_SHORT).show();
                    mMediaPlayer.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            play.setImageResource(R.drawable.ic_play);
        } else {
            mMediaPlayer.start();
            play.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }

    @Override
    public void onClick(View view, int position) {
        index = position;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }

        try {
            audioName.setText(qItemList.get(position).getName());
            play.setImageResource(R.drawable.ic_pause_black_24dp);
            mMediaPlayer.setDataSource(qItemList.get(position).getUrl());
            Toast.makeText(MainActivity.this, qItemList.get(position).getUrl(), Toast.LENGTH_SHORT).show();
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
