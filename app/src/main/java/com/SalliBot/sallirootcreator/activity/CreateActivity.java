package com.SalliBot.sallirootcreator.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.SalliBot.sallirootcreator.R;
import com.SalliBot.sallirootcreator.adapter.scenAdapter;
import com.SalliBot.sallirootcreator.pojo.scena;
import com.SalliBot.sallirootcreator.tools.ConvertJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private ListView rootlist;
    private ConvertJson CJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        if(getIntent()!=null && getIntent().getExtras()!=null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle.getBoolean("ItImport")){
                CJ = new ConvertJson(bundle.getString("rootJson"));
            } else {
                CJ = new ConvertJson("[]");
            }
        } // создать или наполнить ConvertJson

        rootlist = (ListView) findViewById(R.id.rootList);
        scenAdapter adapter = new scenAdapter(this, CJ.getJsonRoot());
        rootlist.setAdapter(adapter);

        rootlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ScenCreateActivity.class);
                intent.putExtra("rootJson", CJ.getJsonRootToString());
                intent.putExtra("editPosition", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.shareButton:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, CJ.getJsonRootToString());
                startActivity(Intent.createChooser(sharingIntent, "Ого готовый Root ?!"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void delScen(View v) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.del_item, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        mDialogBuilder.setCancelable(false);
        mDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (CJ.getSizeJsonRoot() != 0) {
                            if (Integer.parseInt(userInput.getText().toString()) <= CJ.getSizeJsonRoot()) {
                                CJ.removeScenInJsonRoot(Integer.parseInt(userInput.getText().toString()));
                            }
                        }
                    }
                });
        mDialogBuilder.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();

    }

    public void addScen(View v) {
        Intent intent = new Intent(getApplicationContext(), ScenCreateActivity.class);
        intent.putExtra("rootJson", CJ.getJsonRootToString());
        intent.putExtra("editPosition", -1);
        startActivity(intent);
    }
}
