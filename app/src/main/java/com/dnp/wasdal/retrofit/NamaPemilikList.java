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

    @SerializedName("id_kelurahan")
    @Expose
    public String id_kelurahan;

    @SerializedName("nama_kelurahan")
    @Expose
    public String nama_kelurahan;

    @SerializedName("id_kecamatan")
    @Expose
    public String id_kecamatan;

    @SerializedName("nama_kecamatan")
    @Expose
    public String nama_kecamatan;

    @SerializedName("id_kawasan")
    @Expose
    public String id_kawasan;

    @SerializedName("nama_kawasan")
    @Expose
    public String nama_kawasan;

    @SerializedName("id_menara")
    @Expose
    public String id_struktur;

    @SerializedName("nama_menara")
    @Expose
    public String nama_struktur;


    public NamaPemilikList(String id_provider, String nama_provider, String id_kelurahan, String nama_kelurahan, String id_kecamatan, String nama_kecamatan, String id_kawasan, String nama_kawasan, String id_struktur, String nama_struktur) {
        this.id_provider = id_provider;
        this.nama_provider = nama_provider;
        this.id_kelurahan = id_kelurahan;
        this.nama_kelurahan = nama_kelurahan;
        this.id_kecamatan = id_kecamatan;
        this.nama_kecamatan = nama_kecamatan;
        this.id_kawasan = id_kawasan;
        this.nama_kawasan = nama_kawasan;
        this.id_struktur = id_struktur;
        this.nama_struktur = nama_struktur;
    }

    public String getId_provider() {
        return id_provider;
    }
    public String getNama_provider() {
        return nama_provider;
    }
    public String getId_kelurahan() {
        return id_kelurahan;
    }
    public String getNama_kelurahan() {
        return nama_kelurahan;
    }
    public String getId_kecamatan() {
        return id_kecamatan;
    }
    public String getNama_kecamatan() {
        return nama_kecamatan;
    }
    public String getId_kawasan() {
        return id_kawasan;
    }
    public String getNama_kawasan() {
        return nama_kawasan;
    }
    public String getId_struktur() {
        return id_struktur;
    }
    public String getNama_struktur() {
        return nama_struktur;
    }
}
