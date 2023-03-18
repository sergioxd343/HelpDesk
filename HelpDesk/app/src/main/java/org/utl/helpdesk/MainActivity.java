package org.utl.helpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.utl.helpdesk.api.LoginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText txtUser;
    EditText txtPassword;
    Button btnLogin;

    private static String URL_BASE = "http://192.168.16.15:8080/help_desk/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(view -> {
            iniciarSesion();
        });
    }

    public void iniciarSesion() {
        String user = String.valueOf(txtUser.getText());
        String password = String.valueOf(txtPassword.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginApi login = retrofit.create(LoginApi.class);
        Call<Void> call = login.login(user, password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    txtUser.setError("Los datos son incorrectos");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ocurrio un error en el Servidor"+t.fillInStackTrace(), Toast.LENGTH_LONG).show();
            }
        });

    }
}