package com.example.codeutsava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class IOT_Fragment extends Fragment {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;


    Button sendNotif;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iot,container,false);
        Button sendNotif = (Button)view.findViewById(R.id.sendNotif);
        return view;
    }
}
