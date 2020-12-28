package com.example.byteel3ela.ui.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byteel3ela.R;

import java.util.ArrayList;

//todo body and title == null
public class MyRecyclerNViewAdapter extends RecyclerView.Adapter<MyRecyclerNViewAdapter.MyViewHolder> {
    private static Context context;
    ArrayList<NotificationsO> list;
    private LayoutInflater factory;

    public MyRecyclerNViewAdapter(ArrayList<NotificationsO> list, Context context) {
        this.list = list;
        MyRecyclerNViewAdapter.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_notify, null);
        factory = LayoutInflater.from(context);
        return new MyViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.body.setText(list.get(position).getBody());
        holder.iconApp.setImageResource(R.drawable.icone);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;
        public ImageView iconApp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            body = itemView.findViewById(R.id.tvBodyN);
            iconApp = itemView.findViewById(R.id.appCompatImageView);

//            mydb = new DataBaseHelper(context);

        }
    }

}
