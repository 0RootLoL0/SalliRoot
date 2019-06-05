package com.example.salliroot.pojo;

import java.util.List;

public class scena {
    private String text;
    private List<otvetObj> otvet;

    public scena(String text, List<otvetObj> otvet) {
        this.text = text;
        this.otvet = otvet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<otvetObj> getOtvets() {
        return otvet;
    }

    public void setOtvets(List<otvetObj> otvets) {
        this.otvet = otvets;
    }
}
