package com.SalliBot.sallirootcreator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.SalliBot.sallirootcreator.R;
import com.SalliBot.sallirootcreator.pojo.SalliStatic;

import java.util.List;

public class statisticAdapter extends BaseAdapter {

    private List<SalliStatic> list;
    private LayoutInflater layoutInflater;

    public statisticAdapter(Context context, List<SalliStatic> list) {
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
            view = layoutInflater.inflate(R.layout.user_item, parent, false);
        }

        TextView id = (TextView) view.findViewById(R.id.idUser);
        TextView login = (TextView) view.findViewById(R.id.loginUser);
        TextView root = (TextView) view.findViewById(R.id.rootUser);
        TextView scen = (TextView) view.findViewById(R.id.scenUser);

        SalliStatic salliStatic = (SalliStatic) getItem(position);

        id.setText(Integer.toString(salliStatic.getId()));
        login.setText(salliStatic.getLogin());
        root.setText(Integer.toString(salliStatic.getRoot()));
        scen.setText(Integer.toString(salliStatic.getScen()));

        return view;
    }

}
