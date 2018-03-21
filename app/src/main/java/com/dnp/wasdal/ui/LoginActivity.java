package com.dnp.wasdal.ui;

/**
 * Created by dimasnurpanca on 3/18/2018.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.dnp.wasdal.R;
import com.dnp.wasdal.retrofit.APIClient;
import com.dnp.wasdal.retrofit.APIInterface;
import com.dnp.wasdal.retrofit.LoginList;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    APIInterface apiInterface;
    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_FORGOT = 0;
    SharedPreferences sharedpreferences;
    Boolean session = false;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PREMIUM = "premium";
    String usrmail, usrname, premium;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;

    Activity context = this;
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



        pDialog = new ProgressDialog(LoginActivity.this);





        apiInterface = APIClient.getClient().create(APIInterface.class);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        usrmail = sharedpreferences.getString(TAG_EMAIL, null);
        if (session) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra(TAG_EMAIL, usrmail);
            finish();
            startActivity(intent);
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });









        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }



    private void displayProgressDialog() {
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }



    private void hideProgressDialog() {
        pDialog.dismiss();
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // TODO: Implement your own authentication logic here.

        Call<LoginList> call = apiInterface.doGetLoginList(email,password,android_id);
        call.enqueue(new Callback<LoginList>() {
            @Override
            public void onResponse(Call<LoginList> call, Response<LoginList> response) {
                LoginList loginList = response.body();
                String status = loginList.status;
                //Log.e("testid : ", loginList.status);
                Log.e("id : ", loginList.id);

                if(status.equals("1")){
                    // menyimpan login ke session
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(session_status, true);
                    editor.putString(TAG_EMAIL, loginList.email);
                    editor.putString(TAG_USERNAME, loginList.username);
                    editor.putString("id", loginList.id);
                    editor.putString("namalengkap", loginList.namalengkap);
                    editor.putString("role", loginList.role);
                    editor.putString("status", loginList.aktif);
                    editor.putString("gender", loginList.gender);
                    editor.putString("birthday", loginList.birthday);
                    editor.commit();
                    progressDialog.dismiss();
                    onLoginSuccess();
                }
                else if(status.equals("3")){
                    progressDialog.dismiss();
                    onLoginFailedNotActive();
                }

                else
                {
                    progressDialog.dismiss();
                    onLoginFailed();
                }

            }

            @Override
            public void onFailure(Call<LoginList> call, Throwable t) {
                progressDialog.dismiss();
                onLoginFailed();
                call.cancel();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);



    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity


        super.onBackPressed();
        finish();
    }

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View dialogView = inflater.inflate(R.layout.popup, null);
        dialogBuilder.setView(dialogView);

        final TextView symbol2 = (TextView) dialogView.findViewById(R.id.symbol2);
        final TextView text = (TextView) dialogView.findViewById(R.id.text);
        final CheckBox checkbox = (CheckBox) dialogView.findViewById(R.id.simpleCheckBox);

        checkbox.setVisibility(View.GONE);
        symbol2.setText("Gagal Login!");
        text.setText("Username atau Password yang Anda masukan salah! \n \n Silahkan Cek kembali data login Anda. \n"
        );
        dialogBuilder.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });


        final AlertDialog b = dialogBuilder.create();
        b.show();
        b.getWindow().setBackgroundDrawableResource(R.color.warnaLight);



        Button buttonNegative = b.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colorPrimaryLight));
        b.setCancelable(false);
        b.setCanceledOnTouchOutside(false);



        _loginButton.setEnabled(true);
    }

    public void onLoginFailedNotActive() {
        // Toast.makeText(getBaseContext(), "Gagal Login! Silahkan cek kembali data login Anda", Toast.LENGTH_LONG).show();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View dialogView = inflater.inflate(R.layout.popup, null);
        dialogBuilder.setView(dialogView);

        final TextView symbol2 = (TextView) dialogView.findViewById(R.id.symbol2);
        final TextView text = (TextView) dialogView.findViewById(R.id.text);
        final CheckBox checkbox = (CheckBox) dialogView.findViewById(R.id.simpleCheckBox);

        checkbox.setVisibility(View.GONE);
        symbol2.setText("Akun Belum Di Aktivasi!");
        text.setText("Silahkan Cek Inbox Email Anda, dan lakukan aktivasi segera. \n"
        );
        dialogBuilder.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });


        final AlertDialog b = dialogBuilder.create();
        b.show();
        b.getWindow().setBackgroundDrawableResource(R.color.warnaLight);



        Button buttonNegative = b.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colorPrimaryLight));
        b.setCancelable(false);
        b.setCanceledOnTouchOutside(false);
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() ) {
            _emailText.setError("enter a valid username");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }








    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
    }




}