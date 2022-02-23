package com.example.covid_19tracker.ui.country;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19tracker.R;

public class CovidCountryDetail extends AppCompatActivity {
    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases,tvDetailTotalDeaths,tvDetailTodayDeaths,
            tvDetailTotalRecovered,tvDetailTotalActive,tvDetailTotalCritical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);
        //Call View
        tvDetailCountryName=findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCases=findViewById(R.id.tvDetailTotalCases);
        tvDetailTodayCases=findViewById(R.id.tvDetailTodayCases);
        tvDetailTodayDeaths=findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalRecovered=findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTotalActive=findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical=findViewById(R.id.tvDetailTotalCritical);
        tvDetailTotalDeaths=findViewById(R.id.tvDetailTotalDeaths);

        //Call Covid Country
        CovidCountry covidCountry=getIntent().getParcelableExtra("EXTRA COVID");

        //Set Text View
        tvDetailCountryName.setText(covidCountry.getmCovidCountry());
        tvDetailTotalCases.setText(covidCountry.getmCases());
        tvDetailTodayCases.setText(covidCountry.getmTodayCases());
        tvDetailTodayDeaths.setText(covidCountry.getmDeaths());
        tvDetailTotalRecovered.setText(covidCountry.getmTodayDeaths());
        tvDetailTotalActive.setText(covidCountry.getmRecovered());
        tvDetailTotalCritical.setText(covidCountry.getmActive());
        tvDetailTotalDeaths.setText(covidCountry.getmCritical());


    }
}
