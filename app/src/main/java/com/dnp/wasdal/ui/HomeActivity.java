package com.dnp.wasdal.ui;

/**
 * Created by dimasnurpanca on 3/11/2018.
 */
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dnp.wasdal.R;
import com.dnp.wasdal.helper.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.progressBar1)
    ProgressBar spinner;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    Activity context = this;
    private WebView mWebView;
    private ProgressDialog progressBar;
    private boolean canExit;
    private static final String TAG = HomeActivity.class.getSimpleName();
    String ShowOrHideWebViewInitialUse = "show";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.action_home);


        mWebView = webview;
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        mWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
// chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        //final AlertDialog alertDialog = new AlertDialog.Builder(this).create();


        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
               // progressBar = ProgressDialog.show(HomeActivity.this, "Menyiapkan", "Silahkan tunggu...");
                if (ShowOrHideWebViewInitialUse.equals("show")) {
                    webview.setVisibility(webview.INVISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                }
                webview.setVisibility(webview.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            public void onPageStarted(WebView webview, String url, Bitmap favicon) {

                // only make it invisible the FIRST time the app is run
                if (ShowOrHideWebViewInitialUse.equals("show")) {
                    webview.setVisibility(webview.INVISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                }
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                //if (progressBar.isShowing()) {
                   // progressBar.dismiss();
                //}

                String webUrl = url;
                if(webUrl.equals("http://wasdal.dolaynata.com/") || webUrl.equals("http://wasdal.dolaynata.com/login/logout")) {
                    enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    navigationView.setVisibility(View.GONE);
                }else{
                    navigationView.setVisibility(View.VISIBLE);
                    enableDisableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
                ShowOrHideWebViewInitialUse = "hide";
                spinner.setVisibility(View.GONE);
                view.setVisibility(webview.VISIBLE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(HomeActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
               // if (progressBar.isShowing()) {
                   // progressBar.dismiss();
               // }
                ShowOrHideWebViewInitialUse = "hide";
                spinner.setVisibility(View.GONE);
                view.setVisibility(webview.VISIBLE);
            }
        });
        mWebView.loadUrl("http://wasdal.dolaynata.com/");
        //setContentView(mWebView);

    }



    public void enableDisableDrawer(int mode) {
        if (drawer != null) {
            drawer.setDrawerLockMode(mode);
        }
    }


    public void aksesweb(String urls) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        mWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
// chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        //final AlertDialog alertDialog = new AlertDialog.Builder(this).create();


        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                // progressBar = ProgressDialog.show(HomeActivity.this, "Menyiapkan", "Silahkan tunggu...");
                if (ShowOrHideWebViewInitialUse.equals("show")) {
                    webview.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                }
                webview.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            public void onPageStarted(WebView webview, String url, Bitmap favicon) {

                // only make it invisible the FIRST time the app is run
                if (ShowOrHideWebViewInitialUse.equals("show")) {
                    webview.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                }
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                //if (progressBar.isShowing()) {
                // progressBar.dismiss();
                //}

                String webUrl = url;
                if(webUrl.equals("http://wasdal.dolaynata.com/") || webUrl.equals("http://wasdal.dolaynata.com/login/logout")) {
                    enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    navigationView.setVisibility(View.GONE);
                }else{
                    navigationView.setVisibility(View.VISIBLE);
                    enableDisableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
                ShowOrHideWebViewInitialUse = "hide";
                spinner.setVisibility(View.GONE);
                webview.setVisibility(View.VISIBLE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(HomeActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                // if (progressBar.isShowing()) {
                // progressBar.dismiss();
                // }
                ShowOrHideWebViewInitialUse = "hide";
                spinner.setVisibility(View.GONE);
                webview.setVisibility(View.VISIBLE);

                //view.setVisibility(webview.VISIBLE);
            }
        });
        mWebView.loadUrl(urls);
    }


    @Override
    public void onBackPressed() {
        String urls = mWebView.getUrl();
        if(urls.equals("http://wasdal.dolaynata.com/") || urls.equals("http://wasdal.dolaynata.com/login/logout")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert!")
                    .setMessage("Anda yakin untuk menutup aplikasi ini?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // finish();

                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                            finish();
                        }

                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }else {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert!")
                        .setMessage("Anda yakin untuk menutup aplikasi ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // finish();

                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory(Intent.CATEGORY_HOME);
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent);
                                finish();
                            }

                        })
                        .setNegativeButton("Tidak", null)
                        .show();
            }
        }




    }

    public Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    canExit = false;
                    break;
                default:
                    break;
            }
            return canExit;
        }
    });




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId()) {
            case R.id.nav_home:

                aksesweb("http://wasdal.dolaynata.com/home/view");
                break;
            case R.id.nav_data_menara:
                aksesweb("http://wasdal.dolaynata.com/site/view");
                break;
            case R.id.nav_galeri_foto:
                aksesweb("http://wasdal.dolaynata.com/gallery/index");
                break;
            case R.id.nav_ringkasan_laporan:
                aksesweb("http://manado.dolaynata.com/laporan");
                break;
            case R.id.nav_pengaturan:
             //   startActivity(new Intent(getApplicationContext(), EventActivity.class));
            //    finish();
                break;
            case R.id.nav_riwayat:
             //   startActivity(new Intent(getApplicationContext(), KomunitasActivity.class));
             //   finish();
                break;
            case R.id.nav_bantuan:
                break;
            case R.id.nav_item_logout:
                aksesweb("http://wasdal.dolaynata.com/login/logout");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    //aksesweb("http://wasdal.dolaynata.com/home/view");
                    return true;
                case R.id.action_tambah:
                    startActivity(new Intent(getApplicationContext(), TambahActivity.class));
                    finish();
                    return true;
                case R.id.action_grafik:
                    return true;
            }
            return false;
        }
    };


}
