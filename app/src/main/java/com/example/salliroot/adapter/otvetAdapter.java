package com.example.salliroot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.salliroot.R;
import com.example.salliroot.pojo.otvetObj;

import java.util.List;

public class otvetAdapter extends BaseAdapter {

    private List<otvetObj> list;
    private LayoutInflater layoutInflater;

    public otvetAdapter(Context context, List<otvetObj> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = layoutInflater.inflate(R.layout.otvet_item, parent, false);
        }

        TextView id = (TextView) view.findViewById(R.id.idotvet);
        TextView scena = (TextView) view.findViewById(R.id.scenaotvet);
        TextView root = (TextView) view.findViewById(R.id.rootovet);
        TextView textOtvet = (TextView) view.findViewById(R.id.textscen);

        otvetObj otvetObj = (otvetObj) getItem(position);

        id.setText(Integer.toString(position));
        scena.setText(Integer.toString(otvetObj.getScena()));
        root.setText(Integer.toString(otvetObj.getRoot()));
        textOtvet.setText(otvetObj.getText());

        return view;
    }
}
