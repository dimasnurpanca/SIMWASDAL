package com.dnp.wasdal.retrofit;

/**
 * Created by dimasnurpanca on 10/16/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SiteList {


    @SerializedName("id_site")
    @Expose
    public String id_site;
    @SerializedName("nama_site")
    @Expose
    public String nama_site;
    @SerializedName("kategori")
    @Expose
    public String kategori;
    @SerializedName("alamat")
    @Expose
    public String alamat;
    @SerializedName("nama_kelurahan")
    @Expose
    public String nama_kelurahan;
    @SerializedName("nama_kecamatan")
    @Expose
    public String nama_kecamatan;
    @SerializedName("kabupaten")
    @Expose
    public String kabupaten;
    @SerializedName("plat")
    @Expose
    public String plat;
    @SerializedName("lahan")
    @Expose
    public String lahan;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("tipe_menara")
    @Expose
    public String tipe_menara;
    @SerializedName("struktur_menara")
    @Expose
    public String struktur_menara;
    @SerializedName("tinggi_menara")
    @Expose
    public String tinggi_menara;
    @SerializedName("tinggi_gedung")
    @Expose
    public String tinggi_gedung;
    @SerializedName("luas_lahan")
    @Expose
    public String luas_lahan;
    @SerializedName("panjang_lahan")
    @Expose
    public String panjang_lahan;
    @SerializedName("lebar_lahan")
    @Expose
    public String lebar_lahan;
    @SerializedName("kondisi")
    @Expose
    public String kondisi;
    @SerializedName("pertanahan")
    @Expose
    public String pertanahan;
    @SerializedName("penangkal_petir")
    @Expose
    public String penangkal_petir;
    @SerializedName("catu_daya")
    @Expose
    public String catu_daya;
    @SerializedName("lampu_halangan_penerbangan")
    @Expose
    public String lampu_halangan_penerbangan;
    @SerializedName("marka_halangan_penerbangan")
    @Expose
    public String marka_halangan_penerbangan;
    @SerializedName("kelistrikan")
    @Expose
    public String kelistrikan;
    @SerializedName("akses_jalan_satu")
    @Expose
    public String akses_jalan_satu;
    @SerializedName("akses_jalan_dua")
    @Expose
    public String akses_jalan_dua;
    @SerializedName("papan_identitas")
    @Expose
    public String papan_identitas;
    @SerializedName("site_area_luar")
    @Expose
    public String site_area_luar;
    @SerializedName("site_area_dalam")
    @Expose
    public String site_area_dalam;
    @SerializedName("kondisi_utara")
    @Expose
    public String kondisi_utara;
    @SerializedName("kondisi_timur")
    @Expose
    public String kondisi_timur;
    @SerializedName("kondisi_selatan")
    @Expose
    public String kondisi_selatan;
    @SerializedName("kondisi_barat")
    @Expose
    public String kondisi_barat;



    public SiteList(String id_site, String nama_site, String kategori, String alamat, String nama_kelurahan, String nama_kecamatan, String kabupaten, String plat, String lahan, String longitude, String latitude, String tipe_menara, String struktur_menara, String tinggi_menara, String tinggi_gedung, String luas_lahan, String panjang_lahan, String lebar_lahan, String kondisi, String pertanahan, String penangkal_petir, String catu_daya, String lampu_halangan_penerbangan, String marka_halangan_penerbangan, String kelistrikan, String akses_jalan_satu, String akses_jalan_dua, String papan_identitas, String site_area_luar, String site_area_dalam, String kondisi_utara, String kondisi_timur, String kondisi_selatan, String kondisi_barat) {
        this.id_site = id_site;
        this.nama_site = nama_site;
        this.kategori = kategori;
        this.alamat = alamat;
        this.nama_kelurahan = nama_kelurahan;
        this.nama_kecamatan = nama_kecamatan;
        this.kabupaten = kabupaten;
        this.plat = plat;
        this.lahan = lahan;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tipe_menara = tipe_menara;
        this.struktur_menara = struktur_menara;
        this.tinggi_menara = tinggi_menara;
        this.tinggi_gedung = tinggi_gedung;
        this.luas_lahan = luas_lahan;
        this.panjang_lahan = panjang_lahan;
        this.lebar_lahan = lebar_lahan;
        this.kondisi = kondisi;
        this.pertanahan = pertanahan;
        this.penangkal_petir = penangkal_petir;
        this.catu_daya = catu_daya;
        this.lampu_halangan_penerbangan = lampu_halangan_penerbangan;
        this.marka_halangan_penerbangan = marka_halangan_penerbangan;
        this.kelistrikan = kelistrikan;
        this.akses_jalan_satu = akses_jalan_satu;
        this.akses_jalan_dua = akses_jalan_dua;
        this.papan_identitas = papan_identitas;
        this.site_area_luar = site_area_luar;
        this.site_area_dalam = site_area_dalam;
        this.kondisi_utara = kondisi_utara;
        this.kondisi_timur = kondisi_timur;
        this.kondisi_selatan = kondisi_selatan;
        this.kondisi_barat = kondisi_barat;
    }

    public String getId_site() {
        return id_site;
    }
    public String getNama_site() {
        return nama_site;
    }
    public String getKategori() {
        return kategori;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNama_kelurahan() {
        return nama_kelurahan;
    }
    public String getNama_kecamatan() {
        return nama_kecamatan;
    }
    public String getKabupaten() {
        return kabupaten;
    }
    public String getPlat() {
        return plat;
    }
    public String getLahan() {
        return lahan;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getTipe_menara() {
        return tipe_menara;
    }
    public String getStruktur_menara() {
        return struktur_menara;
    }
    public String getTinggi_menara() {
        return tinggi_menara;
    }
    public String getTinggi_gedung() {
        return tinggi_gedung;
    }
    public String getLuas_lahan() {
        return luas_lahan;
    }
    public String getPanjang_lahan() {
        return panjang_lahan;
    }
    public String getLebar_lahan() {
        return lebar_lahan;
    }
    public String getKondisi() {
        return kondisi;
    }
    public String getPertanahan() {
        return pertanahan;
    }
    public String getPenangkal_petir() {
        return penangkal_petir;
    }
    public String getCatu_daya() {
        return catu_daya;
    }
    public String getLampu_halangan_penerbangan() {
        return lampu_halangan_penerbangan;
    }
    public String getMarka_halangan_penerbangan() {
        return marka_halangan_penerbangan;
    }
    public String getKelistrikan() {
        return kelistrikan;
    }
    public String getAkses_jalan_satu() {
        return akses_jalan_satu;
    }
    public String getAkses_jalan_dua() {
        return akses_jalan_dua;
    }
    public String getPapan_identitas() {
        return papan_identitas;
    }
    public String getSite_area_luar() {
        return site_area_luar;
    }
    public String getSite_area_dalam() {
        return site_area_dalam;
    }
    public String getKondisi_utara() {
        return kondisi_utara;
    }
    public String getKondisi_timur() {
        return kondisi_timur;
    }
    public String getKondisi_selatan() {
        return kondisi_selatan;
    }
    public String getKondisi_barat() {
        return kondisi_barat;
    }





}
