package com.dnp.wasdal.ui;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dnp.wasdal.R;
import com.dnp.wasdal.helper.BottomNavigationViewHelper;
import com.dnp.wasdal.retrofit.APIClient;
import com.dnp.wasdal.retrofit.APIInterface;
import com.dnp.wasdal.retrofit.NamaPemilikList;
import com.dnp.wasdal.retrofit.SiteRespond;
import com.dnp.wasdal.util.GPSTracker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity implements LocationListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.var_content_tambah_satu)
    View var_content_tambah_satu;
    @BindView(R.id.var_content_tambah_dua)
    View var_content_tambah_dua;
    @BindView(R.id.var_content_tambah_tiga)
    View var_content_tambah_tiga;
    @BindView(R.id.var_content_tambah_empat)
    View var_content_tambah_empat;
    @BindView(R.id.var_content_tambah_lima)
    View var_content_tambah_lima;

    @BindView(R.id.input_tahun)
    EditText input_tahun;
    @BindView(R.id.input_nama_site)
    EditText input_nama_site;
    @BindView(R.id.input_nama_pemilik)
    EditText input_nama_pemilik;
    @BindView(R.id.var_input_nama_pemilik)
    EditText var_input_nama_pemilik;
    @BindView(R.id.radio_kategori)
    RadioGroup radio_kategori;
    @BindView(R.id.input_kategori)
    EditText input_kategori;
    @BindView(R.id.input_alamat)
    EditText input_alamat;
    @BindView(R.id.input_kelurahan)
    EditText input_kelurahan;
    @BindView(R.id.var_input_kelurahan)
    EditText var_input_kelurahan;
    @BindView(R.id.input_kecamatan)
    EditText input_kecamatan;
    @BindView(R.id.var_input_kecamatan)
    EditText var_input_kecamatan;
    @BindView(R.id.radio_papan)
    RadioGroup radio_papan;
    @BindView(R.id.input_papan)
    EditText input_papan;
    @BindView(R.id.input_kawasan)
    EditText input_kawasan;
    @BindView(R.id.var_input_kawasan)
    EditText var_input_kawasan;

    @BindView(R.id.var_input_degrees_lat)
    EditText var_input_degrees_lat;
    @BindView(R.id.var_input_minutes_lat)
    EditText var_input_minutes_lat;
    @BindView(R.id.var_input_seconds_lat)
    EditText var_input_seconds_lat;

    @BindView(R.id.var_input_degrees_long)
    EditText var_input_degrees_long;
    @BindView(R.id.var_input_minutes_long)
    EditText var_input_minutes_long;
    @BindView(R.id.var_input_seconds_long)
    EditText var_input_seconds_long;
    @BindView(R.id.input_lat)
    EditText input_lat;
    @BindView(R.id.input_long)
    EditText input_long;


    @BindView(R.id.radio_tipe_site)
    RadioGroup radio_tipe_site;
    @BindView(R.id.var_input_struktur)
    EditText var_input_struktur;
    @BindView(R.id.input_tinggi_menara)
    EditText input_tinggi_menara;
    @BindView(R.id.input_tinggi_gedung)
    EditText input_tinggi_gedung;
    @BindView(R.id.input_panjang_lahan)
    EditText input_panjang_lahan;
    @BindView(R.id.input_lebar_lahan)
    EditText input_lebar_lahan;
    @BindView(R.id.input_luas_lahan)
    EditText input_luas_lahan;
    @BindView(R.id.radio_kondisi)
    RadioGroup radio_kondisi;
    @BindView(R.id.input_tipe_site)
    EditText input_tipe_site;
    @BindView(R.id.input_kondisi)
    EditText input_kondisi;
    @BindView(R.id.input_struktur)
    EditText input_struktur;

    @BindView(R.id.radio_pertanahan)
    RadioGroup radio_pertanahan;
    @BindView(R.id.radio_penangkal_petir)
    RadioGroup radio_penangkal_petir;
    @BindView(R.id.radio_catu_daya)
    RadioGroup radio_catu_daya;
    @BindView(R.id.radio_lampu_halangan_penerbangan)
    RadioGroup radio_lampu_halangan_penerbangan;
    @BindView(R.id.radio_marka_halangan_penerbangan)
    RadioGroup radio_marka_halangan_penerbangan;
    @BindView(R.id.radio_kelistrikan)
    RadioGroup radio_kelistrikan;
    @BindView(R.id.input_pertanahan)
    EditText input_pertanahan;
    @BindView(R.id.input_penangkal_petir)
    EditText input_penangkal_petir;
    @BindView(R.id.input_catu_daya)
    EditText input_catu_daya;
    @BindView(R.id.input_lampu_halangan_penerbangan)
    EditText input_lampu_halangan_penerbangan;
    @BindView(R.id.input_marka_halangan_penerbangan)
    EditText input_marka_halangan_penerbangan;
    @BindView(R.id.input_kelistrikan)
    EditText input_kelistrikan;

    @BindView(R.id.input_akses_jalan_satu)
    ImageView input_akses_jalan_satu;
    @BindView(R.id.input_akses_jalan_dua)
    ImageView input_akses_jalan_dua;
    @BindView(R.id.input_papan_identitas)
    ImageView input_papan_identitas;
    @BindView(R.id.input_site_area_luar)
    ImageView input_site_area_luar;
    @BindView(R.id.input_site_area_dalam)
    ImageView input_site_area_dalam;
    @BindView(R.id.input_kondisi_utara)
    ImageView input_kondisi_utara;
    @BindView(R.id.input_kondisi_timur)
    ImageView input_kondisi_timur;
    @BindView(R.id.input_kondisi_selatan)
    ImageView input_kondisi_selatan;
    @BindView(R.id.input_kondisi_barat)
    ImageView input_kondisi_barat;



    @BindView(R.id.input_foto_satu)
    EditText input_foto_satu;

    @BindView(R.id.input_foto_dua)
    EditText input_foto_dua;
    @BindView(R.id.input_foto_papan)
    EditText input_foto_papan;
    @BindView(R.id.input_foto_site_luar)
    EditText input_foto_site_luar;
    @BindView(R.id.input_foto_site_dalam)
    EditText input_foto_site_dalam;
    @BindView(R.id.input_foto_kondisi_utara)
    EditText input_foto_kondisi_utara;
    @BindView(R.id.input_foto_kondisi_timur)
    EditText input_foto_kondisi_timur;
    @BindView(R.id.input_foto_kondisi_selatan)
    EditText input_foto_kondisi_selatan;
    @BindView(R.id.input_foto_kondisi_barat)
    EditText input_foto_kondisi_barat;


    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;


    APIInterface apiInterface;
    Activity context = this;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String usrmail;
    int page = 0;
    SharedPreferences sharedpreferences;
    LocationManager locationManager;
    String provider;

    public int totalfoto = 0;
    public String totalfotostr = "";

    public Uri mImageCaptureUriSatu,mImageCaptureUriDua,mImageCaptureUriPapan,mImageCaptureUriSiteLuar,mImageCaptureUriSiteDalam,mImageCaptureUriKondisiUtara,mImageCaptureUriKondisiTimur,mImageCaptureUriKondisiSelatan,mImageCaptureUriKondisiBarat;

    private static final String TAG = TambahActivity.class.getSimpleName();

    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> items2=new ArrayList<>();
    SpinnerDialog spinnerDialog;
    SpinnerDialog spinnerDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false); // back button
        setTitle("A. Informasi Site");

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
        if(page==0){
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        }


        verifyStoragePermissions(TambahActivity.this);



        apiInterface = APIClient.getClient().create(APIInterface.class); //retrofit
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        usrmail = sharedpreferences.getString("username", null);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        var_input_nama_pemilik.setInputType(InputType.TYPE_NULL);
        var_input_nama_pemilik.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    var_input_nama_pemilik.setInputType(0);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(var_input_nama_pemilik.getWindowToken(), 0);
                    kategori("nama_pemilik");
                }
            }
        });
        var_input_nama_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var_input_nama_pemilik.setInputType(0);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(var_input_nama_pemilik.getWindowToken(), 0);
                kategori("nama_pemilik");
            }
        });


        loaddata("kelurahan");
        loaddata("kecamatan");
          spinnerDialog=new SpinnerDialog(TambahActivity.this,items,"Pilih Kelurahan",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation
          spinnerDialog2=new SpinnerDialog(TambahActivity.this,items2,"Pilih Kecamatan",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                //Toast.makeText(TambahActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //selectedItems.setText(item + " Position: " + position);
                input_kelurahan.setText(item);
                var_input_kelurahan.setText(item);
            }
        });

        spinnerDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                //Toast.makeText(TambahActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                //selectedItems.setText(item + " Position: " + position);
                input_kecamatan.setText(item);
                var_input_kecamatan.setText(item);
            }
        });

        var_input_kelurahan.setInputType(InputType.TYPE_NULL);
        var_input_kelurahan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //var_input_kelurahan.setInputType(0);
                   // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                  //  imm.hideSoftInputFromWindow(var_input_kelurahan.getWindowToken(), 0);
                  //  kategori("kelurahan");
                    spinnerDialog.showSpinerDialog();
                }
            }
        });
        var_input_kelurahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // var_input_kelurahan.setInputType(0);
              //  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
              //  imm.hideSoftInputFromWindow(var_input_kelurahan.getWindowToken(), 0);
              //  kategori("kelurahan");
                spinnerDialog.showSpinerDialog();
            }
        });

        var_input_kecamatan.setInputType(InputType.TYPE_NULL);
        var_input_kecamatan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //var_input_kecamatan.setInputType(0);
                   // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                  //  imm.hideSoftInputFromWindow(var_input_kecamatan.getWindowToken(), 0);
                  //  kategori("kecamatan");
                    spinnerDialog2.showSpinerDialog();
                }
            }
        });
        var_input_kecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // var_input_kecamatan.setInputType(0);
              //  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               // imm.hideSoftInputFromWindow(var_input_kecamatan.getWindowToken(), 0);
              //  kategori("kecamatan");
                spinnerDialog2.showSpinerDialog();
            }
        });

        var_input_kawasan.setInputType(InputType.TYPE_NULL);
        var_input_kawasan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    var_input_kawasan.setInputType(0);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(var_input_kawasan.getWindowToken(), 0);
                    kategori("kawasan");
                }
            }
        });
        var_input_kawasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var_input_kawasan.setInputType(0);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(var_input_kawasan.getWindowToken(), 0);
                kategori("kawasan");
            }
        });


        radio_kategori.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioBTS:
                        input_kategori.setText("BTS");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioBSC:
                        input_kategori.setText("BSC/MSC");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        radio_papan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioAda:
                        input_papan.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioTidakAda:
                        input_papan.setText("Tidak Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        radio_tipe_site.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioGreenField:
                        input_tipe_site.setText("Green Field");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioRoofTop:
                        input_tipe_site.setText("Roof Top");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioLainnya:
                        input_tipe_site.setText("Lainnya");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        radio_kondisi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioTerawat:
                        input_kondisi.setText("Terawat");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioTidakTerawat:
                        input_kondisi.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_pertanahan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioPertanahanAda:
                        input_pertanahan.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioPertanahanTidak:
                        input_pertanahan.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_penangkal_petir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioPenangkalAda:
                        input_penangkal_petir.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioPenangkalTidak:
                        input_penangkal_petir.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_catu_daya.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioCatuAda:
                        input_catu_daya.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioCatuTidak:
                        input_catu_daya.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_lampu_halangan_penerbangan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioLampuAda:
                        input_lampu_halangan_penerbangan.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioLampuTidak:
                        input_lampu_halangan_penerbangan.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_marka_halangan_penerbangan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioMarkaAda:
                        input_marka_halangan_penerbangan.setText("Ada");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioMarkaTidak:
                        input_marka_halangan_penerbangan.setText("Tidak");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        radio_kelistrikan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioKelistrikanPLN:
                        input_kelistrikan.setText("PLN");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioKelistrikanGenset:
                        input_kelistrikan.setText("Genset");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioKelistrikanPLNGenset:
                        input_kelistrikan.setText("PLN & Genset");
                        //Toast.makeText(getApplicationContext(), input_kategori.getText().toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        var_input_degrees_lat.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                    latlong("lat");
                }
            }
        });
        var_input_minutes_lat.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                    latlong("lat");
                }
            }
        });
        var_input_seconds_lat.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                    latlong("lat");
                }
            }
        });

        var_input_degrees_long.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                    latlong("long");
                }
            }
        });
        var_input_minutes_long.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                latlong("long");
                }
            }
        });
        var_input_seconds_long.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0) {
                  latlong("long");
                }
            }
        });

        var_input_struktur.setInputType(InputType.TYPE_NULL);
        var_input_struktur.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    var_input_struktur.setInputType(0);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(var_input_struktur.getWindowToken(), 0);
                    kategori("struktur");
                }
            }
        });
        var_input_struktur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var_input_struktur.setInputType(0);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(var_input_struktur.getWindowToken(), 0);
                kategori("struktur");
            }
        });


        input_akses_jalan_satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriSatu = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriSatu);
                i.putExtra("return-data", true);
                startActivityForResult(i, 1);

            }
        });
        input_akses_jalan_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriDua = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriDua);
                i.putExtra("return-data", true);
                startActivityForResult(i, 2);

            }
        });

        input_papan_identitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriPapan = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriPapan);
                i.putExtra("return-data", true);
                startActivityForResult(i, 3);

            }
        });

        input_site_area_luar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriSiteLuar = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriSiteLuar);
                i.putExtra("return-data", true);
                startActivityForResult(i, 4);

            }
        });
        input_site_area_dalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriSiteDalam = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriSiteDalam);
                i.putExtra("return-data", true);
                startActivityForResult(i, 5);

            }
        });

        input_kondisi_utara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriKondisiUtara = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriKondisiUtara);
                i.putExtra("return-data", true);
                startActivityForResult(i, 6);

            }
        });
        input_kondisi_timur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriKondisiTimur = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriKondisiTimur);
                i.putExtra("return-data", true);
                startActivityForResult(i, 7);

            }
        });
        input_kondisi_selatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriKondisiSelatan = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriKondisiSelatan);
                i.putExtra("return-data", true);
                startActivityForResult(i, 8);

            }
        });
        input_kondisi_barat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File image = new File(appFolderCheckandCreate(), "img" + getTimeStamp() + ".jpg");
                mImageCaptureUriKondisiBarat = Uri.fromFile(image);

                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUriKondisiBarat);
                i.putExtra("return-data", true);
                startActivityForResult(i, 9);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode != RESULT_CANCELED){
            if (requestCode == 1) {
                handleSmallCameraPhoto(mImageCaptureUriSatu,"akses_jalan_satu");
            }
            else if (requestCode == 2) {
                handleSmallCameraPhoto(mImageCaptureUriDua,"akses_jalan_dua");
            }
            else if (requestCode == 3) {
                handleSmallCameraPhoto(mImageCaptureUriPapan,"papan_identitas");
            }
            else if (requestCode == 4) {
                handleSmallCameraPhoto(mImageCaptureUriSiteLuar,"site_luar");
            }
            else if (requestCode == 5) {
                handleSmallCameraPhoto(mImageCaptureUriSiteDalam,"site_dalam");
            }
            else if (requestCode == 6) {
                handleSmallCameraPhoto(mImageCaptureUriKondisiUtara,"kondisi_utara");
            }
            else if (requestCode == 7) {
                handleSmallCameraPhoto(mImageCaptureUriKondisiTimur,"kondisi_timur");
            }
            else if (requestCode == 8) {
                handleSmallCameraPhoto(mImageCaptureUriKondisiSelatan,"kondisi_selatan");
            }
            else if (requestCode == 9) {
                handleSmallCameraPhoto(mImageCaptureUriKondisiBarat,"kondisi_barat");
            }
        }

    }

    private void handleSmallCameraPhoto(Uri uri, String type)
    {
        Bitmap bmp=null;

        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }


        String capturedPicFilePath = getRealPathFromURIPath(uri,this);

        if(type.equals("akses_jalan_satu")) {
            input_foto_satu.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_akses_jalan_satu);
        }
        else if(type.equals("akses_jalan_dua")) {
            input_foto_dua.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_akses_jalan_dua);
        }
        else if(type.equals("papan_identitas")) {
            input_foto_papan.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_papan_identitas);
        }
        else if(type.equals("site_luar")) {
            input_foto_site_luar.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_site_area_luar);
        }
        else if(type.equals("site_dalam")) {
            input_foto_site_dalam.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_site_area_dalam);
        }
        else if(type.equals("kondisi_utara")) {
            input_foto_kondisi_utara.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_kondisi_utara);
        }
        else if(type.equals("kondisi_timur")) {
            input_foto_kondisi_timur.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_kondisi_timur);
        }
        else if(type.equals("kondisi_selatan")) {
            input_foto_kondisi_selatan.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_kondisi_selatan);
        }
        else if(type.equals("kondisi_barat")) {
            input_foto_kondisi_barat.setText(capturedPicFilePath);
            Picasso.with(context).load(uri).fit().centerInside().into(input_kondisi_barat);
        }


    }

    private String appFolderCheckandCreate(){

        String appFolderPath="";
        File externalStorage = Environment.getExternalStorageDirectory();

        if (externalStorage.canWrite())
        {
            appFolderPath = externalStorage.getAbsolutePath() + "/MyApp";
            File dir = new File(appFolderPath);

            if (!dir.exists())
            {
                dir.mkdirs();
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Storage media not found or is full !", Toast.LENGTH_LONG).show();
        }

        return appFolderPath;
    }



    private String getTimeStamp() {

        final long timestamp = new Date().getTime();

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        final String timeString = new SimpleDateFormat("HH_mm_ss_SSS").format(cal.getTime());


        return timeString;
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();

            TambahActivity.this.onSuperBackPressed();


    }
    public void onSuperBackPressed(){
        super.onBackPressed();
    }


    public void loaddata(final String type){

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(TambahActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<NamaPemilikList>> call3 = apiInterface.doGetNamaPemilikList(type);
        call3.enqueue(new Callback<List<NamaPemilikList>>() {
            @Override
            public void onResponse(Call<List<NamaPemilikList>> call, Response<List<NamaPemilikList>> response) {
                int no = -1;
                List<NamaPemilikList> jsonResponse = response.body();
                for (int i = 0; i < jsonResponse.size(); i++) {
                    //dataModels.add(new NamaPemilikList(jsonResponse.get(i).getId(),jsonResponse.get(i).getNama_kategori()));
                    //test[]=1;


                    if(type.equals("kelurahan")) {
                        items.add(jsonResponse.get(i).getNama_kelurahan());

                    }
                    else if(type.equals("kecamatan")) {
                        items2.add(jsonResponse.get(i).getNama_kecamatan());
                    }


                }



                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<NamaPemilikList>> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
            }
        });

    }

    public void kategori(final String type){
            final List<String> nama_provider = new ArrayList<>();
            final List<String> id_provider = new ArrayList<>();

        final List<String> nama_kelurahan = new ArrayList<>();
        final List<String> id_kelurahan = new ArrayList<>();

        final List<String> nama_kecamatan = new ArrayList<>();
        final List<String> id_kecamatan = new ArrayList<>();

        final List<String> nama_kawasan = new ArrayList<>();
        final List<String> id_kawasan = new ArrayList<>();

        final List<String> nama_struktur = new ArrayList<>();
        final List<String> id_struktur = new ArrayList<>();

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(TambahActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<NamaPemilikList>> call3 = apiInterface.doGetNamaPemilikList(type);
        call3.enqueue(new Callback<List<NamaPemilikList>>() {
            @Override
            public void onResponse(Call<List<NamaPemilikList>> call, Response<List<NamaPemilikList>> response) {
                int no = -1;
                List<NamaPemilikList> jsonResponse = response.body();
                for (int i = 0; i < jsonResponse.size(); i++) {
                    //dataModels.add(new NamaPemilikList(jsonResponse.get(i).getId(),jsonResponse.get(i).getNama_kategori()));
                    //test[]=1;

                    if(type.equals("nama_pemilik")) {
                        nama_provider.add(jsonResponse.get(i).getNama_provider());
                        id_provider.add(jsonResponse.get(i).getId_provider());
                        if (input_nama_pemilik.getText().toString().equals(jsonResponse.get(i).getId_provider())) {
                            no = i;
                        }
                    }
                    else if(type.equals("kelurahan")) {
                        items.add(jsonResponse.get(i).getNama_kelurahan());
                       nama_kelurahan.add(jsonResponse.get(i).getNama_kelurahan());
                      id_kelurahan.add(jsonResponse.get(i).getId_kelurahan());
                        if (input_kelurahan.getText().toString().equals(jsonResponse.get(i).getId_kelurahan())) {
                            no = i;
                        }
                    }
                    else if(type.equals("kecamatan")) {
                        nama_kecamatan.add(jsonResponse.get(i).getNama_kecamatan());
                        id_kecamatan.add(jsonResponse.get(i).getId_kecamatan());
                        if (input_kecamatan.getText().toString().equals(jsonResponse.get(i).getId_kecamatan())) {
                            no = i;
                        }
                    }
                    else if(type.equals("kawasan")) {
                        nama_kawasan.add(jsonResponse.get(i).getNama_kawasan());
                        id_kawasan.add(jsonResponse.get(i).getId_kawasan());
                        if (input_kawasan.getText().toString().equals(jsonResponse.get(i).getId_kawasan())) {
                            no = i;
                        }
                    }
                    else if(type.equals("struktur")) {
                        nama_struktur.add(jsonResponse.get(i).getNama_struktur());
                        id_struktur.add(jsonResponse.get(i).getId_struktur());
                        if (input_struktur.getText().toString().equals(jsonResponse.get(i).getId_struktur())) {
                            no = i;
                        }
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(getResources().getString(R.string.header_kategori_builder));

                int checkedItem = no;

                if(type.equals("nama_pemilik")) {
                    builder.setSingleChoiceItems(nama_provider.toArray(new String[nama_provider.size()]), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user checked an item
                            input_nama_pemilik.setText(id_provider.get(which));
                            var_input_nama_pemilik.setText(nama_provider.get(which));

                        }
                    });
                }
                else if(type.equals("kelurahan")) {
                    builder.setSingleChoiceItems(nama_kelurahan.toArray(new String[nama_kelurahan.size()]), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user checked an item
                            input_kelurahan.setText(nama_kelurahan.get(which));
                            var_input_kelurahan.setText(nama_kelurahan.get(which));

                        }
                    });
                }
                else if(type.equals("kecamatan")) {
                    builder.setSingleChoiceItems(nama_kecamatan.toArray(new String[nama_kecamatan.size()]), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user checked an item
                            input_kecamatan.setText(nama_kecamatan.get(which));
                            var_input_kecamatan.setText(nama_kecamatan.get(which));

                        }
                    });
                }
                else if(type.equals("kawasan")) {
                    builder.setSingleChoiceItems(nama_kawasan.toArray(new String[nama_kawasan.size()]), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user checked an item
                            input_kawasan.setText(nama_kawasan.get(which));
                            var_input_kawasan.setText(nama_kawasan.get(which));

                        }
                    });
                }
                else if(type.equals("struktur")) {
                    builder.setSingleChoiceItems(nama_struktur.toArray(new String[nama_struktur.size()]), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user checked an item
                            input_struktur.setText(nama_struktur.get(which));
                            var_input_struktur.setText(nama_struktur.get(which));

                        }
                    });
                }


                builder.setPositiveButton(getResources().getString(R.string.ok_builder), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user clicked OK

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<NamaPemilikList>> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    //startActivity(new Intent(getApplicationContext(), TambahActivity.class));
                    finish();
                    return true;
                case R.id.action_kembali:
                    //startActivity(new Intent(getApplicationContext(), TambahActivity.class));
                    //finish();
                    if(page==1){
                        setTitle("A. Informasi Site");
                        var_content_tambah_satu.setVisibility(View.VISIBLE);
                        var_content_tambah_dua.setVisibility(View.INVISIBLE);
                        var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                        var_content_tambah_empat.setVisibility(View.INVISIBLE);
                        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
                        page=0;
                    }
                    else if(page==2){
                        setTitle("B. Informasi Geografis");
                        var_content_tambah_satu.setVisibility(View.INVISIBLE);
                        var_content_tambah_dua.setVisibility(View.VISIBLE);
                        var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                        var_content_tambah_empat.setVisibility(View.INVISIBLE);
                        var_content_tambah_lima.setVisibility(View.INVISIBLE);
                        bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                        page=1;
                    }
                    else if(page==3){
                        setTitle("C. Informasi Konstruksi");
                        var_content_tambah_satu.setVisibility(View.INVISIBLE);
                        var_content_tambah_dua.setVisibility(View.INVISIBLE);
                        var_content_tambah_tiga.setVisibility(View.VISIBLE);
                        var_content_tambah_empat.setVisibility(View.INVISIBLE);
                        var_content_tambah_lima.setVisibility(View.INVISIBLE);
                        bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                        page=2;
                    }
                    else if(page==4){
                        setTitle("D. Sarana Pendukung");
                        var_content_tambah_satu.setVisibility(View.INVISIBLE);
                        var_content_tambah_dua.setVisibility(View.INVISIBLE);
                        var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                        var_content_tambah_empat.setVisibility(View.VISIBLE);
                        var_content_tambah_lima.setVisibility(View.INVISIBLE);
                        bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                        page=3;
                    }



                    return true;
                case R.id.action_lanjut:
                    if(page==0){
                            if(!input_tahun.getText().toString().equals("") &&!input_nama_site.getText().toString().equals("") && !input_nama_pemilik.getText().toString().equals("") && !input_kategori.getText().toString().equals("") && !input_kelurahan.getText().toString().equals("") && !input_kecamatan.getText().toString().equals("") && !input_papan.getText().toString().equals("") && !input_kawasan.getText().toString().equals("")&& !input_alamat.getText().toString().equals(""))
                            {
                                setTitle("B. Informasi Geografis");
                                var_content_tambah_satu.setVisibility(View.INVISIBLE);
                                var_content_tambah_dua.setVisibility(View.VISIBLE);
                                var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                                var_content_tambah_empat.setVisibility(View.INVISIBLE);
                                bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                                page=1;
                            }else{
                                Toast.makeText(getApplicationContext(), "Harap isi semua kolom input yang tersedia!", Toast.LENGTH_LONG).show();
                           }

                    }
                    else if(page==1){
                        if(!input_lat.getText().toString().equals("") && !input_long.getText().toString().equals(""))
                        {
                            setTitle("C. Informasi Konstruksi");
                            var_content_tambah_satu.setVisibility(View.INVISIBLE);
                            var_content_tambah_dua.setVisibility(View.INVISIBLE);
                            var_content_tambah_tiga.setVisibility(View.VISIBLE);
                            var_content_tambah_empat.setVisibility(View.INVISIBLE);
                            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                            page=2;
                        }else{
                            Toast.makeText(getApplicationContext(), "Harap isi semua kolom input yang tersedia!", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(page==2){
                            setTitle("D. Sarana Pendukung");
                            var_content_tambah_satu.setVisibility(View.INVISIBLE);
                            var_content_tambah_dua.setVisibility(View.INVISIBLE);
                            var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                            var_content_tambah_empat.setVisibility(View.VISIBLE);
                            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                            page=3;
                    }
                    else if(page==3){
                        setTitle("E. Foto Site");
                        var_content_tambah_satu.setVisibility(View.INVISIBLE);
                        var_content_tambah_dua.setVisibility(View.INVISIBLE);
                        var_content_tambah_tiga.setVisibility(View.INVISIBLE);
                        var_content_tambah_empat.setVisibility(View.INVISIBLE);
                        var_content_tambah_lima.setVisibility(View.VISIBLE);
                        bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                        page=4;
                    }
                    return true;
            }
            return false;
        }
    };


    public void getlat(View v) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    0);
        }
        // Getting LocationManager object
        statusCheck();
        locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null && !provider.equals("")) {
            if (!provider.contains("gps")) { // if gps is disabled
                final Intent poke = new Intent();
                poke.setClassName("com.android.settings",
                        "com.android.settings.widget.SettingsAppWidgetProvider");
                poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                poke.setData(Uri.parse("3"));
                sendBroadcast(poke);
            }
            // Get the location from the given provider
            Location location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 0, 0, this);
            }


            if (location != null)
                onLocationChanged(location);
            else
                location = locationManager.getLastKnownLocation(provider);
            if (location != null)
                onLocationChanged(location);
            else

                Toast.makeText(getBaseContext(), "Location can't be retrieved",
                        Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getBaseContext(), "No Provider Found",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public void latlong(final String type){

        if(type.equals("lat")){
            double d, m, s;
            if(var_input_degrees_lat.getText().toString().equals("") || var_input_degrees_lat.getText().toString().equals("-")){
                d = 0;
            }else{
                d = Double.valueOf(var_input_degrees_lat.getText().toString());
            }
            if(var_input_minutes_lat.getText().toString().equals("") || var_input_minutes_lat.getText().toString().equals("-")){
                m = 0;
            }else{
                m = Double.parseDouble(var_input_minutes_lat.getText().toString());
            }
            if(var_input_seconds_lat.getText().toString().equals("") || var_input_seconds_lat.getText().toString().equals("-")){
                s = 0;
            }else{
                s = Double.parseDouble(var_input_seconds_lat.getText().toString());
            }


            double dd = Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));

            input_lat.setText(String.valueOf(dd));
        }else if(type.equals("long")){
            double d, m, s;
            if(var_input_degrees_long.getText().toString().equals("") || var_input_degrees_long.getText().toString().equals("-")){
                d = 0;
            }else{
                d = Double.valueOf(var_input_degrees_long.getText().toString());
            }
            if(var_input_minutes_long.getText().toString().equals("") || var_input_minutes_long.getText().toString().equals("-")){
                m = 0;
            }else{
                m = Double.parseDouble(var_input_minutes_long.getText().toString());
            }
            if(var_input_seconds_long.getText().toString().equals("") || var_input_seconds_long.getText().toString().equals("-")){
                s = 0;
            }else{
                s = Double.parseDouble(var_input_seconds_long.getText().toString());
            }


            double dd = Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));

            input_long.setText(String.valueOf(dd));
        }
    }



    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                "Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,
                                        final int id) {
                        startActivity(new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,
                                        final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        input_long.setText(String.valueOf(location.getLongitude()));
        input_lat.setText(String.valueOf(location.getLatitude()));
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    0);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private void upload() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(TambahActivity.this);
        progressDialog.setMessage("Menyimpan Data...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        //content satu
        RequestBody val_tahun =RequestBody.create(MediaType.parse("multipart/form-data"), input_tahun.getText().toString());
        RequestBody val_nama_site =RequestBody.create(MediaType.parse("multipart/form-data"), input_nama_site.getText().toString());
        RequestBody val_nama_pemilik =RequestBody.create(MediaType.parse("multipart/form-data"), input_nama_pemilik.getText().toString());
        RequestBody val_kategori =RequestBody.create(MediaType.parse("multipart/form-data"), input_kategori.getText().toString());
        RequestBody val_alamat =RequestBody.create(MediaType.parse("multipart/form-data"), input_alamat.getText().toString());
        RequestBody val_kelurahan =RequestBody.create(MediaType.parse("multipart/form-data"), input_kelurahan.getText().toString());
        RequestBody val_kecamatan =RequestBody.create(MediaType.parse("multipart/form-data"), input_kecamatan.getText().toString());
        RequestBody val_kabupaten =RequestBody.create(MediaType.parse("multipart/form-data"), "Indramayu");
        RequestBody val_papan =RequestBody.create(MediaType.parse("multipart/form-data"), input_papan.getText().toString());
        RequestBody val_kawasan =RequestBody.create(MediaType.parse("multipart/form-data"), input_kawasan.getText().toString());

        //content dua
        RequestBody val_lat =RequestBody.create(MediaType.parse("multipart/form-data"), input_lat.getText().toString());
        RequestBody val_long =RequestBody.create(MediaType.parse("multipart/form-data"), input_long.getText().toString());

        //content tiga
        RequestBody val_tipe_site =RequestBody.create(MediaType.parse("multipart/form-data"), input_tipe_site.getText().toString());
        RequestBody val_struktur =RequestBody.create(MediaType.parse("multipart/form-data"), input_struktur.getText().toString());
        RequestBody val_kondisi =RequestBody.create(MediaType.parse("multipart/form-data"), input_kondisi.getText().toString());
        RequestBody val_tinggi_menara =RequestBody.create(MediaType.parse("multipart/form-data"), input_tinggi_menara.getText().toString());
        RequestBody val_tinggi_gedung =RequestBody.create(MediaType.parse("multipart/form-data"), input_tinggi_gedung.getText().toString());
        RequestBody val_panjang_lahan =RequestBody.create(MediaType.parse("multipart/form-data"), input_panjang_lahan.getText().toString());
        RequestBody val_lebar_lahan =RequestBody.create(MediaType.parse("multipart/form-data"), input_lebar_lahan.getText().toString());
        RequestBody val_luas_lahan =RequestBody.create(MediaType.parse("multipart/form-data"), input_luas_lahan.getText().toString());

        //content empat
        RequestBody val_pertanahan =RequestBody.create(MediaType.parse("multipart/form-data"), input_pertanahan.getText().toString());
        RequestBody val_penangkal_petir =RequestBody.create(MediaType.parse("multipart/form-data"), input_penangkal_petir.getText().toString());
        RequestBody val_catu_daya =RequestBody.create(MediaType.parse("multipart/form-data"), input_catu_daya.getText().toString());
        RequestBody val_lampu_halangan_penerbangan =RequestBody.create(MediaType.parse("multipart/form-data"), input_lampu_halangan_penerbangan.getText().toString());
        RequestBody val_marka_halangan_penerbangan =RequestBody.create(MediaType.parse("multipart/form-data"), input_marka_halangan_penerbangan.getText().toString());
        RequestBody val_kelistrikan =RequestBody.create(MediaType.parse("multipart/form-data"), input_kelistrikan.getText().toString());
        RequestBody val_username =RequestBody.create(MediaType.parse("multipart/form-data"), usrmail);
        Call<SiteRespond> resultCall = apiInterface.uploaddata(val_tahun,val_nama_site,val_nama_pemilik,val_kategori,val_alamat,val_kelurahan,val_kecamatan,val_kabupaten,val_papan,val_kawasan,
                val_lat,val_long,
                val_tipe_site,val_struktur,val_tinggi_menara,val_tinggi_gedung,val_panjang_lahan,val_lebar_lahan,val_luas_lahan,val_kondisi,
                val_pertanahan,val_penangkal_petir,val_catu_daya,val_lampu_halangan_penerbangan,val_marka_halangan_penerbangan,val_kelistrikan,val_username);



        resultCall.enqueue(new Callback<SiteRespond>() {
            @Override
            public void onResponse(Call<SiteRespond> call, Response<SiteRespond> response) {

                progressDialog.dismiss();

                // Response Success or Fail

                if (!response.body().getError()) {
                    String id = response.body().getMessage();
                    queryuploadgambar(id);
                    //Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();}



            }

            @Override
            public void onFailure(Call<SiteRespond> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void queryuploadgambar(String id) {


        if(!input_foto_satu.getText().toString().equals("")){
        uploadgambar("satu",id);
        }
       if(!input_foto_dua.getText().toString().equals("")){
            uploadgambar("dua",id);
        }
       if(!input_foto_papan.getText().toString().equals("")){
            uploadgambar("tiga",id);
        }
     if(!input_foto_site_luar.getText().toString().equals("")){
            uploadgambar("empat",id);
        }
        if(!input_foto_site_dalam.getText().toString().equals("")){
            uploadgambar("lima",id);
        }
        if(!input_foto_kondisi_utara.getText().toString().equals("")){
            uploadgambar("enam",id);
        }
       if(!input_foto_kondisi_timur.getText().toString().equals("")){
            uploadgambar("tujuh",id);
        }
        if(!input_foto_kondisi_selatan.getText().toString().equals("")){
            uploadgambar("delapan",id);
        }
         if(!input_foto_kondisi_barat.getText().toString().equals("")){
            uploadgambar("sembilan",id);
        }




    }

    private void uploadgambar(final String type, String id) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(TambahActivity.this);

        if(type.equals("satu")){
            progressDialog.setMessage("Upload Foto Akses Jalan 1");
        }else if(type.equals("dua")){
            progressDialog.setMessage("Upload Foto Akses Jalan 2");
        }
        else if(type.equals("tiga")){
            progressDialog.setMessage("Upload Foto Papan Identitas 1");
        }
        else if(type.equals("empat")){
            progressDialog.setMessage("Upload Foto Site Area Tampak Luar");
        }
        else if(type.equals("lima")){
            progressDialog.setMessage("Upload Foto Site Area Tampak Dalam");
        }
        else if(type.equals("enam")){
            progressDialog.setMessage("Upload Foto Kondisi Sekitar Utara");
        }
        else if(type.equals("tujuh")){
            progressDialog.setMessage("Upload Foto Kondisi Sekitar Timur");
        }
        else if(type.equals("delapan")){
            progressDialog.setMessage("Upload Foto Kondisi Sekitar Selatan");
        }
        else if(type.equals("sembilan")){
            progressDialog.setMessage("Upload Foto Kondisi Sekitar Barat");
        }

        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String filePath = "";
        if(type.equals("satu")){
            filePath = getRealPathFromURIPath(mImageCaptureUriSatu, TambahActivity.this);
        }else if(type.equals("dua")){
            filePath = getRealPathFromURIPath(mImageCaptureUriDua, TambahActivity.this);
        }
        else if(type.equals("tiga")){
            filePath = getRealPathFromURIPath(mImageCaptureUriPapan, TambahActivity.this);
        }
        else if(type.equals("empat")){
            filePath = getRealPathFromURIPath(mImageCaptureUriSiteLuar, TambahActivity.this);
        }
        else if(type.equals("lima")){
            filePath = getRealPathFromURIPath(mImageCaptureUriSiteDalam, TambahActivity.this);
        }
        else if(type.equals("enam")){
            filePath = getRealPathFromURIPath(mImageCaptureUriKondisiUtara, TambahActivity.this);
        }
        else if(type.equals("tujuh")){
            filePath = getRealPathFromURIPath(mImageCaptureUriKondisiTimur, TambahActivity.this);
        }
        else if(type.equals("delapan")){
            filePath = getRealPathFromURIPath(mImageCaptureUriKondisiSelatan, TambahActivity.this);
        }
        else if(type.equals("sembilan")){
            filePath = getRealPathFromURIPath(mImageCaptureUriKondisiBarat, TambahActivity.this);
        }

        File file = new File(filePath);

        RequestBody val_file = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), val_file);



/*
        String filePathtiga = getRealPathFromURIPath(mImageCaptureUriPapan, TambahActivity.this);
        File file_papan = new File(filePathtiga);
        String filePathempat = getRealPathFromURIPath(mImageCaptureUriSiteLuar, TambahActivity.this);
        File file_site_luar = new File(filePathempat);
        String filePathlima = getRealPathFromURIPath(mImageCaptureUriSiteDalam, TambahActivity.this);
        File file_site_dalam = new File(filePathlima);
        String filePathenam = getRealPathFromURIPath(mImageCaptureUriKondisiUtara, TambahActivity.this);
        File file_kondisi_utara = new File(filePathenam);
        String filePathtujuh = getRealPathFromURIPath(mImageCaptureUriKondisiTimur, TambahActivity.this);
        File file_kondisi_timur = new File(filePathtujuh);
        String filePathdelapan = getRealPathFromURIPath(mImageCaptureUriKondisiSelatan, TambahActivity.this);
        File file_kondisi_selatan = new File(filePathdelapan);
        String filePathsembilan = getRealPathFromURIPath(mImageCaptureUriKondisiBarat, TambahActivity.this);
        File file_kondisi_barat = new File(filePathsembilan);


        RequestBody val_akses_jalan_satu = RequestBody.create(MediaType.parse("image/*"), file_akses_jalan_satu);
        MultipartBody.Part fileToUpload_akses_jalan_satu = MultipartBody.Part.createFormData("file", file_akses_jalan_satu.getName(), val_akses_jalan_satu);

        RequestBody val_akses_jalan_dua = RequestBody.create(MediaType.parse("image/*"), file_akses_jalan_dua);
        MultipartBody.Part fileToUpload_akses_jalan_dua = MultipartBody.Part.createFormData("file2", file_akses_jalan_dua.getName(), val_akses_jalan_dua);

        RequestBody val_papan = RequestBody.create(MediaType.parse("image/*"), file_papan);
        MultipartBody.Part fileToUpload_papan = MultipartBody.Part.createFormData("file3", file_papan.getName(), val_papan);
        RequestBody val_site_luar = RequestBody.create(MediaType.parse("image/*"), file_site_luar);
        MultipartBody.Part fileToUpload_site_luar = MultipartBody.Part.createFormData("file4", file_site_luar.getName(), val_site_luar);
        RequestBody val_site_dalam = RequestBody.create(MediaType.parse("image/*"), file_site_dalam);
        MultipartBody.Part fileToUpload_site_dalam = MultipartBody.Part.createFormData("file5", file_site_dalam.getName(), val_site_dalam);
        RequestBody val_kondisi_utara = RequestBody.create(MediaType.parse("image/*"), file_kondisi_utara);
        MultipartBody.Part fileToUpload_kondisi_utara = MultipartBody.Part.createFormData("file6", file_kondisi_utara.getName(), val_kondisi_utara);
        RequestBody val_kondisi_timur = RequestBody.create(MediaType.parse("image/*"), file_kondisi_timur);
        MultipartBody.Part fileToUpload_kondisi_timur = MultipartBody.Part.createFormData("file7", file_kondisi_timur.getName(), val_kondisi_timur);
        RequestBody val_kondisi_selatan = RequestBody.create(MediaType.parse("image/*"), file_kondisi_selatan);
        MultipartBody.Part fileToUpload_kondisi_selatan = MultipartBody.Part.createFormData("file8", file_kondisi_selatan.getName(), val_kondisi_selatan);
        RequestBody val_kondisi_barat = RequestBody.create(MediaType.parse("image/*"), file_kondisi_barat);
        MultipartBody.Part fileToUpload_kondisi_barat = MultipartBody.Part.createFormData("file9", file_kondisi_barat.getName(), val_kondisi_barat);
*/



        RequestBody val =RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody val_type =RequestBody.create(MediaType.parse("multipart/form-data"), type);
        //RequestBody val_akses_jalan_dua = RequestBody.create(MediaType.parse("image/*"), file_akses_jalan_dua);
        //RequestBody val_papan_identitas = RequestBody.create(MediaType.parse("image/*"), file_papan_identitas);
        // RequestBody val_site_luar = RequestBody.create(MediaType.parse("image/*"), file_site_luar);
        // RequestBody val_site_dalam = RequestBody.create(MediaType.parse("image/*"), file_site_dalam);
        // RequestBody val_site_dalam = RequestBody.create(MediaType.parse("image/*"), file_site_dalam);
        //  RequestBody val_kondisi_utara = RequestBody.create(MediaType.parse("image/*"), file_kondisi_utara);
        // RequestBody val_kondisi_timur = RequestBody.create(MediaType.parse("image/*"), file_kondisi_timur);
        //  RequestBody val_kondisi_selatan = RequestBody.create(MediaType.parse("image/*"), file_kondisi_selatan);
        //  RequestBody val_kondisi_barat = RequestBody.create(MediaType.parse("image/*"), file_kondisi_barat);
        Call<SiteRespond> resultCall = apiInterface.uploadfoto(fileToUpload,val,val_type);



        resultCall.enqueue(new Callback<SiteRespond>() {
            @Override
            public void onResponse(Call<SiteRespond> call, Response<SiteRespond> response) {

                progressDialog.dismiss();

                // Response Success or Fail

                if (!response.body().getError()) {
                    if(type.equals(totalfotostr)){
                        Toast.makeText(getApplicationContext(), "Berhasil menambahkan titik lokasi", Toast.LENGTH_LONG).show();
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                    //Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                  }





            }

            @Override
            public void onFailure(Call<SiteRespond> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }

    public void simpan(View v) {
       // if(!input_foto_satu.getText().toString().equals("") && !input_foto_dua.getText().toString().equals("") && !input_foto_papan.getText().toString().equals("") && !input_foto_site_luar.getText().toString().equals("") && !input_foto_site_dalam.getText().toString().equals("") && !input_foto_kondisi_utara.getText().toString().equals("") && !input_foto_kondisi_timur.getText().toString().equals("") && !input_foto_kondisi_selatan.getText().toString().equals("") && !input_foto_kondisi_barat.getText().toString().equals("")){
        if(!input_foto_satu.getText().toString().equals("")){
            totalfotostr="satu";
            totalfoto++;
        }
        if(!input_foto_dua.getText().toString().equals("")){
            totalfotostr="dua";
            totalfoto++;
        }
        if(!input_foto_papan.getText().toString().equals("")){
            totalfotostr="tiga";
            totalfoto++;
        }
        if(!input_foto_site_luar.getText().toString().equals("")){
            totalfotostr="empat";
            totalfoto++;
        }
        if(!input_foto_site_dalam.getText().toString().equals("")){
            totalfotostr="lima";
            totalfoto++;
        }
        if(!input_foto_kondisi_utara.getText().toString().equals("")){
            totalfotostr="enam";
            totalfoto++;
        }
        if(!input_foto_kondisi_timur.getText().toString().equals("")){
            totalfotostr="tujuh";
            totalfoto++;
        }
        if(!input_foto_kondisi_selatan.getText().toString().equals("")){
            totalfotostr="delapan";
            totalfoto++;
        }
        if(!input_foto_kondisi_barat.getText().toString().equals("")){
            totalfotostr="sembilan";
            totalfoto++;
        }

            upload();
       // }
       // else{
       //    Toast.makeText(getApplicationContext(), "Harap masukan seluruh foto yang tertera!", Toast.LENGTH_LONG).show();
      //  }


    }

}