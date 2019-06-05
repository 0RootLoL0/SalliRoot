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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salliroot.R;
import com.example.salliroot.adapter.scenAdapter;
import com.example.salliroot.pojo.scena;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RootActivity extends AppCompatActivity {

    private ListView rootlist;
    private Gson gson = new Gson();
    private List<scena> rootShare = new ArrayList<scena>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        if(getIntent()!=null && getIntent().getExtras()!=null) {
            Bundle bundle = getIntent().getExtras();
            Toast.makeText(getApplicationContext(), bundle.getString("scenaJson"), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), bundle.getString("rootShare"), Toast.LENGTH_SHORT).show();
            rootShare = gson.fromJson(bundle.getString("rootShare"), new TypeToken<List<scena>>(){}.getType());
            rootShare.add(gson.fromJson(bundle.getString("scenaJson"), scena.class));
        }

        rootlist = (ListView) findViewById(R.id.rootList);
        scenAdapter adapter = new scenAdapter(this, rootShare);
        rootlist.setAdapter(adapter);

        rootlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.root_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
                shareRoot();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareRoot() {
        Gson gson = new Gson();
        String json = gson.toJson(rootShare);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, json);
        startActivity(Intent.createChooser(sharingIntent, "Ого готовый Root ?!"));
    }

    public void addScen(View view){
        Intent intent = new Intent(this, ScenCreaterActivity.class);
        intent.putExtra("rootShare", gson.toJson(rootShare));
        startActivity(intent);
    }

    public void delScen(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.del_otvet, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
        mDialogBuilder.setCancelable(false);
        mDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (rootShare.size() != 0) {
                            if (Integer.parseInt(userInput.getText().toString()) <= rootShare.size()) {
                                rootShare.remove(Integer.parseInt(userInput.getText().toString()));
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
