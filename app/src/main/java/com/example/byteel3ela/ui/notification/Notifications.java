package com.example.byteel3ela.ui.notification;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byteel3ela.R;
import com.example.byteel3ela.pojo.DataBaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("pageNotificaions");
    ArrayList<NotificationsO> listN;
    MyRecyclerNViewAdapter myRecyclerNViewAdapter;
    RecyclerView notify;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        listN = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DataBaseHelper(this);
        getDateFromSql();
    }


    public void getDateFromSql() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                NotificationsO notificationsO = new NotificationsO(res.getString(1), res.getString(2), res.getString(3));
                listN.add(notificationsO);
                myRecyclerNViewAdapter = new MyRecyclerNViewAdapter(listN, Notifications.this);
                notify = findViewById(R.id.rvNotifications);
                notify.setNestedScrollingEnabled(false);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Notifications.this);
                notify.setLayoutManager(linearLayoutManager);
                notify.setAdapter(myRecyclerNViewAdapter);
                notify.setItemAnimator(new DefaultItemAnimator());
            }
        }
    }
}
