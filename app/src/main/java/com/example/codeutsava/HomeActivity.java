package com.example.codeutsava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Double;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity {


    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    private static final int ActivityNum = 0;
    TextView display_fuel , data_fuel;
    DatabaseReference mreff;
    double a=1.00000;
    private ImageView imgView;
    private FirebaseAnalytics mFirebaseAnalaytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpBottomNavigationView();
        imgView = (ImageView)findViewById(R.id.imageView);
        TextView display_fuel = (TextView)findViewById(R.id.display_fuel1);
        final TextView data_fuel = (TextView)findViewById(R.id.data_fuel1);
        mFirebaseAnalaytics = FirebaseAnalytics.getInstance(HomeActivity.this);
        mreff = FirebaseDatabase.getInstance().getReference();
        mreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fuel = dataSnapshot.child("volume").getValue().toString();
                data_fuel.setText(fuel);
               sendNotificationToDevice(fuel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

    //    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
      //      NotificationChannel channel =
        //            new NotificationChannel("notif","notif", NotificationManager.IMPORTANCE_DEFAULT);
          //  NotificationManager manager = getSystemService(NotificationManager.class);
           // manager.createNotificationChannel(channel);
        //}
//
  //      FirebaseMessaging.getInstance().subscribeToTopic("general")
    //            .addOnCompleteListener(new OnCompleteListener<Void>() {
      //              @Override
        //            public void onComplete(@NonNull Task<Void> task) {
          //              String msg = "Loading fuel levels";
            //            if (!task.isSuccessful()) {
              //              msg = "Failed to receive notification";
                //        }
                  //      Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    //}
                //});

    }

    public void sendNotificationToDevice(String string)
    {
        if(Double.compare(Double.parseDouble(string),a)<0)
        {
            //final TextView data_fuel = (TextView)findViewById(R.id.data_fuel1);
            //data_fuel.setText(R.string.tank);
            button();
        }
    }

    public void button() {
        notification.setSmallIcon(R.drawable.ic_petrol_pump);
        notification.setTicker("Petrol Alert");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Petrol level low");
        notification.setContentText("Petrol prices in your city = Rs. 75.71 per litre");

        Intent intent = new Intent(this,MapsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }

    private void setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
    }

    private void createGraph(View v){
        Bundle params = new Bundle();
        params.putInt("Fuel Amount",v.getId());
        String distance;

        switch (v.getId())
        {
            case R.id.data_distance:
                distance = data_fuel.getText().toString();
                break;

        }
    }

}
