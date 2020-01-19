package com.example.codeutsava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TravelActivity extends AppCompatActivity {
    private static final int ActivityNum = 2;
    private EditText input_mileage , input_distance;
    private Button search_mileage , search_distance , btnGo;
    private TextView display_distance , data_distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        input_mileage = (EditText)findViewById(R.id.input_mileage);
        input_distance = (EditText)findViewById(R.id.input_distance);
        search_mileage = (Button)findViewById(R.id.search_mileage);
        search_distance = (Button)findViewById(R.id.search_distance);
        btnGo = (Button)findViewById(R.id.btnGo);
        display_distance = (TextView)findViewById(R.id.display_distance);
        data_distance = (TextView)findViewById(R.id.data_distance);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mil = input_mileage.getText().toString();
                String dis = input_distance.getText().toString();
                double d = Double.parseDouble(dis)/Double.parseDouble(mil);
                data_distance.setText(String.valueOf(d));
            }
        });
        search_mileage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cartrade.com/mileage"));
                startActivity(intent);
            }
        });
        search_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.distancecalculator.net/"));
                startActivity(intent1);
            }
        });
    }
}
