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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
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
import com.dnp.wasdal.retrofit.APIClient;
import com.dnp.wasdal.retrofit.APIInterface;
import com.dnp.wasdal.retrofit.NamaPemilikList;
import com.dnp.wasdal.retrofit.SiteRespond;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_nama_pemilik)
    EditText input_nama_pemilik;
    @BindView(R.id.var_input_nama_pemilik)
    EditText var_input_nama_pemilik;
    @BindView(R.id.radio_kategori)
    RadioGroup radio_kategori;
    @BindView(R.id.input_kategori)
    EditText input_kategori;


    APIInterface apiInterface;
    Activity context = this;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String usrmail;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button
        setTitle("A. Informasi Site");

        verifyStoragePermissions(TambahActivity.this);
        apiInterface = APIClient.getClient().create(APIInterface.class); //retrofit
        //sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        //usrmail = sharedpreferences.getString("email", null);
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







    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }



    private void upload(String status) {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(TambahActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loadingprogress));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
/*
        RequestBody val_title =RequestBody.create(MediaType.parse("multipart/form-data"), title.getText().toString());
        RequestBody val_description =RequestBody.create(MediaType.parse("multipart/form-data"), description.getText().toString());
        RequestBody val_content =RequestBody.create(MediaType.parse("multipart/form-data"), editor.toHtml());
        RequestBody val_email =RequestBody.create(MediaType.parse("multipart/form-data"), usrmail);
        RequestBody val_kategori =RequestBody.create(MediaType.parse("multipart/form-data"), input_nama_pemilik.getText().toString());

        Call<SiteRespond> resultCall = apiInterface.upload(val_title, val_description, val_content, val_email, val_kategori);



        resultCall.enqueue(new Callback<SiteRespond>() {
            @Override
            public void onResponse(Call<SiteRespond> call, Response<SiteRespond> response) {

                progressDialog.dismiss();

                // Response Success or Fail

                if (!response.body().getError()) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();}



            }

            @Override
            public void onFailure(Call<SiteRespond> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
        */
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

    public void kategori(String type){
            final List<String> nama_provider = new ArrayList<>();
            final List<String> id_provider = new ArrayList<>();

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
                    nama_provider.add(jsonResponse.get(i).getNama_provider());
                    id_provider.add(jsonResponse.get(i).getId_provider());
                    if(input_nama_pemilik.getText().toString().equals(jsonResponse.get(i).getId_provider())){
                        no=i;
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(getResources().getString(R.string.header_kategori_builder));

                int checkedItem = no;

                // cow
                builder.setSingleChoiceItems(nama_provider.toArray(new String[nama_provider.size()]), checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // user checked an item
                        input_nama_pemilik.setText(id_provider.get(which));
                        var_input_nama_pemilik.setText(nama_provider.get(which));

                    }
                });

// add OK and Cancel buttons
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


}