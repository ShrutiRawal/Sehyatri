package com.example.codeutsava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivity extends AppCompatActivity {
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int ActivityNum = 1;
    private Button button , sendLoc;
    TextView display_price , data_price;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private  LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpBottomNavigationView();
        TextView display_price1 = (TextView)findViewById(R.id.display_price1);
        TextView data_price1 = (TextView)findViewById(R.id.data_price1);
        sendLoc = (Button)findViewById(R.id.sendLoc);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=petrol pumps");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        getLocationPermission();
        isServicesOK();


    }

    private void setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(MapsActivity.this,bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ActivityNum);
        menuItem.setChecked(true);
    }

    public boolean isServicesOK()
    {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);

        if(available== ConnectionResult.SUCCESS)
        {

            return true;
        }
        else
        {
            if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
            {
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsActivity.this,available,ERROR_DIALOG_REQUEST);
                dialog.show();
            }
            else
            {
                Toast.makeText(this,"Map Requests can not be processed",Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private void getLocationPermission()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

    }

    private void getDeviceLocation()
    {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if (mLocationPermissionsGranted){
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation = (Location)task.getResult();
                            latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                            LocationHelper locationHelper = new LocationHelper(
                                    currentLocation.getLatitude(),currentLocation.getLongitude()
                            );
                            FirebaseDatabase.getInstance().getReference("location").setValue(locationHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(MapsActivity.this,"Location sent",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(MapsActivity.this,"Location not sent",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            //moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                              //      DEFAULT_ZOOM);

                        }else{
                            Toast.makeText(MapsActivity.this,"Unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }catch(SecurityException e){

        }
    }

    public void sendLocation(View view) {
        getDeviceLocation();
    }
}
