package com.example.byteel3ela;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextClock;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.byteel3ela.pojo.DataBaseHelper;
import com.example.byteel3ela.ui.IOnBackPressed;
import com.example.byteel3ela.ui.notification.Notifications;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.byteel3ela.ui.notification.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myDataBaseResult";
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("pageNotificaions");
    DataBaseHelper myDb;
    TextClock textClock;
    String title;
    String body;
    String time;
    String result;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("EEEE , dd-MMM-yyyy , hh:mm:ss  a");
    private AppBarConfiguration mAppBarConfiguration;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title = snapshot.child("title").getValue().toString();
                body = snapshot.child("body").getValue().toString();
                result = snapshot.child("switch").getValue().toString();
                insertOnDataBase();

                //notificationsــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ
                switch (result) {
                    case "off":
                        break;
                    case "on":
                        notificationManager = NotificationManagerCompat.from(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, Notifications.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                        @SuppressLint("WrongConstant") Notification notification = new Notification.Builder(MainActivity.this, CHANNEL_1_ID)
                                .setSmallIcon(R.drawable.icone)
                                .setContentTitle(title)
                                .setContentIntent(contentIntent)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .setStyle(new Notification.BigTextStyle().bigText(body))
                                .setContentText(body).build();
                        notificationManager.notify(1, notification);
                        //notificationsــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ
                        break;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void insertOnDataBase() {
        switch (result) {
            case "off":
                break;
            case "on":
                myDb = new DataBaseHelper(getApplicationContext());
                time = format.format(calendar.getTime());
//        currentTime.getHours();
                Log.d(TAG, "Message Notification Body: " + time);
                boolean result = myDb.insertData(title, body, time);
                if (result) {
                    Log.d(TAG, " result : true");
                } else {
                    Log.d(TAG, " result : false");
                    break;
                }


        }
    }
}