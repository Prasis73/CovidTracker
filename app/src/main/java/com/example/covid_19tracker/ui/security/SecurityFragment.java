package com.example.covid_19tracker.ui.security;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covid_19tracker.R;

public class SecurityFragment extends Fragment {
    Button police;
    Button ambulance;
    Button nhospitals;
    Button alarm;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_security_fragment, container, false);
       // Toast.makeText(getContext(), "Rakalaraba tha", Toast.LENGTH_SHORT).show();
        police= view.findViewById(R.id.police);
        ambulance= view.findViewById(R.id.ambulance);
        nhospitals= view.findViewById(R.id.nhospitals);
        alarm=view.findViewById(R.id.alarm);


        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:100"));
                startActivity(callIntent);


            }

        });


        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:112"));
                startActivity(callIntent);


            }
        });
        nhospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/hospital"));
                startActivity(browserIntent);
            }
        });

        final MediaPlayer mp= MediaPlayer.create(getActivity(),R.raw.coron);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });



        return  view;

    }



}

