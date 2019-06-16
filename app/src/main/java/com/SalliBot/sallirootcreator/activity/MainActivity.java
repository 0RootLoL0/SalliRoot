package com.SalliBot.sallirootcreator.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.SalliBot.sallirootcreator.R;

public class MainActivity extends AppCompatActivity {

    final String setting = "salliScript";
    private String jsonRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLoad = (Button) findViewById(R.id.loadButton);

        jsonRoot = getSharedPreferences("MyPref", MODE_PRIVATE).getString(setting, "");
        if (jsonRoot.equals("") || jsonRoot.equals("[]")){
            buttonLoad.setVisibility(View.GONE);
        }
    }

    public void ButtonLoad(View v){
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        intent.putExtra("ItImport", true);
        intent.putExtra("rootJson", jsonRoot);
        startActivity(intent);
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
