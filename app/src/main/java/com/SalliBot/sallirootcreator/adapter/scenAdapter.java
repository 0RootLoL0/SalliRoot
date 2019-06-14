package com.SalliBot.sallirootcreator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.SalliBot.sallirootcreator.R;
import com.SalliBot.sallirootcreator.pojo.scena;

import java.util.List;

public class scenAdapter extends BaseAdapter {

    private List<scena> list;
    private LayoutInflater layoutInflater;

    public scenAdapter(Context context, List<scena> list) {
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
            view = layoutInflater.inflate(R.layout.scen_item, parent, false);
        }

        TextView id = (TextView) view.findViewById(R.id.idscen);
        TextView textOtvet = (TextView) view.findViewById(R.id.textscen);

        scena scenaOdj = (scena) getItem(position);

        id.setText(Integer.toString(position));
        textOtvet.setText(scenaOdj.getText());

        return view;
    }
}
