package com.SalliBot.sallirootcreator.tools;

import com.SalliBot.sallirootcreator.pojo.otvetObj;
import com.SalliBot.sallirootcreator.pojo.scena;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ConvertJson {
    private Gson gson = new Gson();
    private List<scena> jsonRoot;

    public ConvertJson(String jsonRoot) {
        this.jsonRoot = gson.fromJson(jsonRoot, new TypeToken<List<scena>>(){}.getType());
    }

    public List<scena> getJsonRoot() {
        return jsonRoot;
    }

    public String getJsonRootToString() {
        return gson.toJson(jsonRoot);
    }

    public int getSizeJsonRoot(){
        return jsonRoot.size();
    }

    public void removeScenInJsonRoot(int position){
        jsonRoot.remove(position);
    }

    public void removeAllScenInJsonRoot(){
        jsonRoot = new ArrayList<scena>();
    }

    public void addScenInJsonRoot(String textOtveta, List<otvetObj> otvetObjs){
        jsonRoot.add(new scena(textOtveta, otvetObjs));
    }

    public void setScenInJsonRoot(int position, String textOtveta, List<otvetObj> otvetObjs){
        jsonRoot.set(position, new scena(textOtveta, otvetObjs));
    }

    public scena getScen(int position){
        return jsonRoot.get(position);
    }
}
