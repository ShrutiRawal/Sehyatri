package com.example.codeutsava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class ratingActivity extends AppCompatActivity {
    private TextView display_title;
    private Button submit;
    private EditText input_place,input_quality,input_sanitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        display_title = (TextView)findViewById(R.id.display_title);
        submit = (Button)findViewById(R.id.submit);
        input_place = (EditText)findViewById(R.id.input_place);
        input_quality = (EditText)findViewById(R.id.input_quality);
        input_sanitation = (EditText)findViewById(R.id.input_sanitation);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = input_place.getText().toString();
                String quality = input_quality.getText().toString();
                String sanitation = input_sanitation.getText().toString();
                double res = (Double.parseDouble(quality)+Double.parseDouble(sanitation));
                RatingHelper helper = new RatingHelper(quality,sanitation);
                FirebaseDatabase.getInstance().getReference("rating").child("bharat").setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ratingActivity.this,"Rating sent",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ratingActivity.this,"Rating not sent",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}
