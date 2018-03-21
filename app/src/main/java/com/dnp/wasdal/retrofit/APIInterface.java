package com.dnp.wasdal.retrofit;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIInterface {


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginList> doGetLoginList(@Field("email") String email, @Field("password") String password, @Field("android_id") String android_id);

    @FormUrlEncoded
    @POST("user.php")
    Call<List<UserList>> doGetUserList(@Field("email") String email);

    @GET("kategori.php")
    Call<List<KategoriList>> doGetKategoriList();

    @FormUrlEncoded
    @POST("getoption.php")
    Call<List<NamaPemilikList>> doGetNamaPemilikList(@Field("type") String type);

    @Multipart
    @POST("upload.php")
    Call<SiteRespond> uploadgambars(@Part MultipartBody.Part filesatu, @Part MultipartBody.Part filedua,@Part MultipartBody.Part filetiga,@Part MultipartBody.Part fileempat,@Part MultipartBody.Part filelima,@Part MultipartBody.Part filenam,@Part MultipartBody.Part filetujuh,@Part MultipartBody.Part filedelapan,@Part MultipartBody.Part filesembilan,@Part("id") RequestBody id);

    @Multipart
    @POST("upload.php")
    Call<SiteRespond> uploadfoto(@Part MultipartBody.Part file,@Part("id") RequestBody id,@Part("type") RequestBody type);


    @Multipart
    @POST("upload.php")
    Call<SiteRespond> uploaddata(@Part("tahun") RequestBody tahun,@Part("nama_site") RequestBody nama_site,@Part("nama_pemilik") RequestBody nama_pemilik,@Part("kategori") RequestBody kategori,@Part("alamat") RequestBody alamat,@Part("kelurahan") RequestBody kelurahan,@Part("kecamatan") RequestBody kecamatan,@Part("kabupaten") RequestBody kabupaten,@Part("papan_nama") RequestBody papan_nama,@Part("kawasan") RequestBody kawasan,
            @Part("lattitude") RequestBody lattitude,@Part("longitude") RequestBody longitude,
            @Part("tipe_site") RequestBody tipe_site,@Part("struktur") RequestBody struktur,@Part("tinggi_menara") RequestBody tinggi_menara,@Part("tinggi_gedung") RequestBody tinggi_gedung,@Part("panjang_lahan") RequestBody panjang_lahan,@Part("lebar_lahan") RequestBody lebar_lahan,@Part("luas_lahan") RequestBody luas_lahan,@Part("kondisi") RequestBody kondisi,
            @Part("pertanahan") RequestBody pertanahan,@Part("penangkal_petir") RequestBody penangkal_petir,@Part("catu_daya") RequestBody catu_daya,@Part("lampu_penerbangan") RequestBody lampu_penerbangan,@Part("marka_penerbangan") RequestBody marka_penerbangan,@Part("kelistrikan") RequestBody kelistrikan,@Part("username") RequestBody username);


    @Multipart
    @POST("bantuan.php")
    Call<SiteRespond> bantuan();

    @Multipart
    @POST("poststory.php?drafts")
    Call<SiteRespond> upload(@Part("title") RequestBody title, @Part("description") RequestBody description, @Part("content") RequestBody content, @Part("email") RequestBody email, @Part("kategori") RequestBody kategori);

    @Multipart
    @POST("poststory.php?published")
    Call<SiteRespond> upload2(@Part("title") RequestBody title, @Part("description") RequestBody description, @Part("content") RequestBody content, @Part("email") RequestBody email, @Part("kategori") RequestBody kategori);

}
