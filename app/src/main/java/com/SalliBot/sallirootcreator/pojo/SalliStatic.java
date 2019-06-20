
package com.SalliBot.sallirootcreator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalliStatic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("root")
    @Expose
    private Integer root;
    @SerializedName("scen")
    @Expose
    private Integer scen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    public Integer getScen() {
        return scen;
    }

    public void setScen(Integer scen) {
        this.scen = scen;
    }

}
