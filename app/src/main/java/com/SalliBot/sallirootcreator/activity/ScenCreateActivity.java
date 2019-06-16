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
import android.widget.EditText;
import android.widget.ListView;

import com.SalliBot.sallirootcreator.R;
import com.SalliBot.sallirootcreator.adapter.otvetAdapter;
import com.SalliBot.sallirootcreator.pojo.otvetObj;
import com.SalliBot.sallirootcreator.pojo.scena;
import com.SalliBot.sallirootcreator.tools.ConvertJson;

import java.util.ArrayList;
import java.util.List;

public class ScenCreateActivity extends AppCompatActivity {

    private ListView listView;
    private EditText textOtvet;
    private ConvertJson CJ;
    private int editPosition;
    private List<otvetObj> otvets = new ArrayList<otvetObj>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scen_create);
        listView = (ListView) findViewById(R.id.listView);
        textOtvet = (EditText) findViewById(R.id.textscen);

        Bundle bundle = getIntent().getExtras();
        CJ = new ConvertJson(bundle.getString("rootJson"));
        editPosition = bundle.getInt("editPosition");

        if (editPosition > 0){
            scena editScen = CJ.getScen(editPosition);
            textOtvet.setText(editScen.getText());
            otvets = editScen.getOtvets();
        }

        otvetAdapter adapter = new otvetAdapter(this, otvets);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.scencreate_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.ok:
                if (editPosition > 0){
                    CJ.setScenInJsonRoot(editPosition, textOtvet.getText().toString(), otvets);
                    Intent intent = new Intent(getApplicationContext(), wwwActivity.class);
                    intent.putExtra("ItImport", true);
                    intent.putExtra("rootJson", CJ.getJsonRootToString());
                    startActivity(intent);
                } else {
                    CJ.addScenInJsonRoot(textOtvet.getText().toString(), otvets);
                    Intent intent = new Intent(getApplicationContext(), wwwActivity.class);
                    intent.putExtra("ItImport", true);
                    intent.putExtra("rootJson", CJ.getJsonRootToString());
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        View promptsView = li.inflate(R.layout.del_item, null);
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
