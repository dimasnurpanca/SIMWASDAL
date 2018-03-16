package com.dnp.wasdal.retrofit;

import java.util.List;

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
    @POST("poststory.php?drafts")
    Call<SiteRespond> upload(@Part("title") RequestBody title, @Part("description") RequestBody description, @Part("content") RequestBody content, @Part("email") RequestBody email, @Part("kategori") RequestBody kategori);

    @Multipart
    @POST("poststory.php?published")
    Call<SiteRespond> upload2(@Part("title") RequestBody title, @Part("description") RequestBody description, @Part("content") RequestBody content, @Part("email") RequestBody email, @Part("kategori") RequestBody kategori);

}
