package com.dnp.wasdal.retrofit;

/**
 * Created by dimasnurpanca on 10/16/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NamaPemilikList {


    @SerializedName("id_provider")
    @Expose
    public String id_provider;

    @SerializedName("nama_provider")
    @Expose
    public String nama_provider;


    public NamaPemilikList(String id_provider, String nama_provider) {
        this.id_provider = id_provider;
        this.nama_provider = nama_provider;
    }

    public String getId_provider() {
        return id_provider;
    }
    public String getNama_provider() {
        return nama_provider;
    }

}
