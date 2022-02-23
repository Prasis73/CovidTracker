package com.example.covid_19tracker.ui.home;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19tracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    private TextView tvTotalConfirmed,tvLastUpdated,tvTotalDeaths,tvTotalRecovered;
    private ProgressBar progressBar;
    private int STORAGE_PERMISSION_CODE = 1;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //call view
        tvTotalConfirmed= root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered= root.findViewById(R.id.tvTotalRecovered);
        progressBar=root.findViewById(R.id.progress_circular_home);
        tvLastUpdated=root.findViewById(R.id.tvLastUpdated);
        //call volley
        getData();
        requestStoragePermission();

        return root;
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS,Manifest.permission.CALL_PHONE}, STORAGE_PERMISSION_CODE);



                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS,Manifest.permission.CALL_PHONE}, STORAGE_PERMISSION_CODE);


        }

    }

    private String getDate(long milliSecond){
        //Mon,23 Mar 2020 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE,dd MMM yyy hh:mm:ss aaa");
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
        @Override
        public void onResponse (String response) {
            progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                tvTotalConfirmed.setText(jsonObject.getString("cases"));
                tvTotalDeaths.setText(jsonObject.getString("deaths"));
                tvTotalRecovered.setText(jsonObject.getString("recovered"));
                tvLastUpdated.setText("Last Updated"+"\n"+getDate(jsonObject.getLong("updated")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                progressBar.setVisibility(View.GONE);
                Log.d("error Response",error.toString());

            }
    });
        queue.add(stringRequest);

        }


    }


