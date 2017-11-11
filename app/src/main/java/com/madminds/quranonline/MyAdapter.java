package com.madminds.quranonline;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HawasMan on 11/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    List<QItem> qItemList;
    public MyAdapter(Context context, List<QItem> list){
        this.context = context;
        this.qItemList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        QItem qItem = qItemList.get(position);
        holder.playBTN.setImageResource(R.drawable.ic_play);
        holder.audioName.setText(qItem.getName());
        holder.playBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,holder.audioName.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return qItemList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView playBTN;
        TextView audioName;
        public MyHolder(View itemView) {
            super(itemView);
            audioName = (TextView) itemView.findViewById(R.id.audio_name);
            playBTN = (ImageView)itemView.findViewById(R.id.audio_play);
        }
    }
}
