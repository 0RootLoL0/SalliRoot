package com.SalliBot.sallirootcreator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.SalliBot.sallirootcreator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonCreate(View v){
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        intent.putExtra("ItImport", false);
        startActivity(intent);
    }

    public void ButtonImport(View v){
        Intent intent = new Intent(getApplicationContext(), ImportActivity.class);
        startActivity(intent);
    }
}
