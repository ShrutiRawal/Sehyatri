package com.example.codeutsava;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

class BottomNavigationViewHelper {
    public static void enableNavigation(final Context context , BottomNavigationView view){
       view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

               switch (menuItem.getItemId())
               {
                   case R.id.iot:
                       Intent intent1 = new Intent(context,HomeActivity.class);
                       context.startActivity(intent1);
                       break;

                   case R.id.ppump:
                       Intent intent2 = new Intent(context,MapsActivity.class);
                       context.startActivity(intent2);
                       break;

                   case R.id.travel:
                       Intent intent3 = new Intent(context,TravelActivity.class);
                       context.startActivity(intent3);
                       break;

                   case R.id.ratings:
                       Intent intent4 = new Intent(context,ratingActivity.class);
                       context.startActivity(intent4);
                       break;

                   case R.id.graph:
                       Intent intent5 = new Intent(context,GraphActivity.class);
                       context.startActivity(intent5);
                       break;
               }

               return false;
           }
       });
    }

}
