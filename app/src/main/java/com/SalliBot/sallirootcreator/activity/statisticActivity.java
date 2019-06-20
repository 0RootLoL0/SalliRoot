package com.SalliBot.sallirootcreator.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.SalliBot.sallirootcreator.R;
import com.SalliBot.sallirootcreator.adapter.scenAdapter;
import com.SalliBot.sallirootcreator.adapter.statisticAdapter;
import com.SalliBot.sallirootcreator.interfaces.apiStatic;
import com.SalliBot.sallirootcreator.pojo.SalliStatic;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class statisticActivity extends AppCompatActivity {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://sallistatisticserver.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private apiStatic apiStatic = retrofit.create(apiStatic.class);
    private ProgressBar progressBar;
    private ListView statisticListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        statisticListView = (ListView) findViewById(R.id.staticListView);

        apiStatic.SalliStatics().enqueue(new Callback<List<SalliStatic>>() {
            @Override
            public void onResponse(Call<List<SalliStatic>> call, Response<List<SalliStatic>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "404", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (0 != response.body().size()) {
                    List<SalliStatic> mess = response.body();
                    statisticAdapter adapter = new statisticAdapter(getApplicationContext(), mess);
                    statisticListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SalliStatic>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
