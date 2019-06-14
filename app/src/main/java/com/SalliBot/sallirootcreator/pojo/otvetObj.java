package com.SalliBot.sallirootcreator.pojo;

public class otvetObj {
    private String text;
    private int scena;
    private int root;

    public otvetObj(String text, int scena, int root) {
        this.text = text;
        this.scena = scena;
        this.root = root;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScena() {
        return scena;
    }

    public void setScena(int scena) {
        this.scena = scena;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
}
