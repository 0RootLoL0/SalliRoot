package com.example.salliroot.tools;

import com.example.salliroot.pojo.scena;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class enDeJson {
    private Gson gson = new Gson();
    private List<scena> rootJson;

    public enDeJson(String rootJson) {
        this.rootJson = gson.fromJson(rootJson, new TypeToken<List<scena>>(){}.getType());
    }

    public List<scena> getRootJson(int positionS) {
        return rootJson;
    }

    public void setRootJson(List<scena> rootJson) {
        this.rootJson = rootJson;
    }
    public scena getScena(int position){
        return rootJson.get(position);
    }
    public void addScena(scena scen) {rootJson.add(scen);}
    public void editScena(scena scen, int position) {rootJson.set(position, scen);}
    public String exportRoot(){return gson.toJson(rootJson);}
}
