package com.madminds.quranonline;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<QItem> qItemList;
    private ItemClickListener itemClickListener;

    MyAdapter(Context context, List<QItem> list) {
        this.context = context;
        this.qItemList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final QItem qItem = qItemList.get(position);
        holder.audioName.setText(qItem.getName());
    }

    @Override
    public int getItemCount() {
        return qItemList.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView audioName;

        MyHolder(View itemView) {
            super(itemView);

            audioName = (TextView) itemView.findViewById(R.id.audio_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
