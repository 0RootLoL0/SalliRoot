package com.example.salliroot.activity;

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
import android.widget.EditText;
import android.widget.ListView;

import com.example.salliroot.R;
import com.example.salliroot.adapter.otvetAdapter;
import com.example.salliroot.pojo.otvetObj;
import com.example.salliroot.pojo.scena;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ScenCreaterActivity extends AppCompatActivity {

    private ListView listView;
    private EditText textOtvet;
    private String rootShare;
    private List<otvetObj> otvets = new ArrayList<otvetObj>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        textOtvet = (EditText) findViewById(R.id.textscen);
        otvetAdapter adapter = new otvetAdapter(this, otvets);
        listView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        rootShare = bundle.getString("rootShare");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.scen_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.ok:
                backRoot();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backRoot() {
        Gson gson = new Gson();
        scena scena1 = new scena(textOtvet.getText().toString(), otvets);
        String json = gson.toJson(scena1);
        Intent intent = new Intent(this, RootActivity.class);
        intent.putExtra("scenaJson", json);
        intent.putExtra("rootShare", rootShare);
        startActivity(intent);
    }

    public void addOtvetItem(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.add_otvet, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        final EditText userInput1 = (EditText) promptsView.findViewById(R.id.input_text1);
        final EditText userInput2 = (EditText) promptsView.findViewById(R.id.input_text2);
        mDialogBuilder.setCancelable(false);
        mDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        otvets.add( new otvetObj(userInput.getText().toString(),
                                Integer.parseInt(userInput1.getText().toString()),
                                Integer.parseInt(userInput2.getText().toString())));
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

    public void delOtvetItem(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.del_otvet, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        mDialogBuilder.setCancelable(false);
        mDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (otvets.size() != 0) {
                            if (Integer.parseInt(userInput.getText().toString()) <= otvets.size()) {
                                otvets.remove(Integer.parseInt(userInput.getText().toString()));
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
}
