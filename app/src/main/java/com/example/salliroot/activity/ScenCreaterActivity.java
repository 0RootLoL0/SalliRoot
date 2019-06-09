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
import com.example.salliroot.tools.enDeJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ScenCreaterActivity extends AppCompatActivity {

    private ListView listView;
    private EditText textOtvet;
    private boolean openE;
    private int elem = 0;
    private enDeJson Rjc;
    private List<otvetObj> otvets = new ArrayList<otvetObj>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        textOtvet = (EditText) findViewById(R.id.textscen);

        Bundle bundle = getIntent().getExtras();
        Rjc = new enDeJson(bundle.getString("rootShare"));
        openE = bundle.getBoolean("openE");
        elem = bundle.getInt("positionS");
        if (openE){
            textOtvet.setText(Rjc.getScena(bundle.getInt("positionS")).getText());
            otvets = Rjc.getScena(elem).getOtvets();
        }
        listView.setAdapter(new otvetAdapter(this, otvets));
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
        if (openE)
            Rjc.addScena(new scena(textOtvet.getText().toString(), otvets));
        else
            Rjc.editScena(new scena(textOtvet.getText().toString(), otvets), elem);
        Intent intent = new Intent(this, RootActivity.class);
        intent.putExtra("rootShare", Rjc.exportRoot());
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
