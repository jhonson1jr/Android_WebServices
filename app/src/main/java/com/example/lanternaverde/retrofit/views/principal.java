package com.example.lanternaverde.retrofit.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lanternaverde.retrofit.BusProvider;
import com.example.lanternaverde.retrofit.Communicator;
import com.example.lanternaverde.retrofit.ErrorEvent;
import com.example.lanternaverde.retrofit.R;
import com.example.lanternaverde.retrofit.serversEvent.ServerEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class principal extends AppCompatActivity {
    private Communicator communicator;
    private String username, password;
    private EditText usernameET, passwordET;
    private Button loginButtonPost, loginButtonGet;
    private TextView information, extraInformation;
    private final static String TAG = "MainActivity";
    public static Bus bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        communicator = new Communicator();

        usernameET = (EditText)findViewById(R.id.usernameInput);
        passwordET = (EditText)findViewById(R.id.passwordInput);
        //This is used to hide the password's EditText characters. So we can avoid the different hint font.
        passwordET.setTransformationMethod(new PasswordTransformationMethod());

        loginButtonPost = (Button)findViewById(R.id.loginButtonPost);
        loginButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameET.getText().toString();
                password = passwordET.getText().toString();
                usePost(username, password);
            }
        });

        loginButtonGet = (Button)findViewById(R.id.loginButtonGet);
        loginButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameET.getText().toString();
                password = passwordET.getText().toString();
                useGet(username, password);
            }
        });

        information = (TextView)findViewById(R.id.information);
        extraInformation = (TextView)findViewById(R.id.extraInformation);
    }

    private void usePost(String username, String password){
        communicator.loginPost(username, password);
    }

    private void useGet(String username, String password){
        communicator.loginGet(username, password);
    }

    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    //caso haja evento do servidor no login:
    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        Toast.makeText(this, ""+serverEvent.getServerResponse().getMessage(), Toast.LENGTH_SHORT).show();
        if(serverEvent.getServerResponse().getUsername() != null){
            information.setText("Username: "+serverEvent.getServerResponse().getUsername() + " || Password: "+serverEvent.getServerResponse().getPassword());
        }
        extraInformation.setText("" + serverEvent.getServerResponse().getMessage());
        if (serverEvent.getServerResponse().getResponseCode() == 1){ //se o servidor retornar codigo 1, login OK
            startActivity(new Intent(this, inicio.class)); //chamando a classe java que add user
        }
    }


    //se houver evento do servidor de erro:
    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Log.i("Erro",errorEvent.getErrorMsg());
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }
}

