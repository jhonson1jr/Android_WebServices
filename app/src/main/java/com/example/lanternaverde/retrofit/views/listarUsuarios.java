package com.example.lanternaverde.retrofit.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lanternaverde.retrofit.BusProvider;
import com.example.lanternaverde.retrofit.Communicator;
import com.example.lanternaverde.retrofit.ErrorEvent;
import com.example.lanternaverde.retrofit.R;
import com.example.lanternaverde.retrofit.RecyclerViewAdapter;
import com.example.lanternaverde.retrofit.jsonConsultaUsuarios;
import com.example.lanternaverde.retrofit.serversEvent.ServerEventListarUsuario;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Lanterna Verde on 19/08/2017.
 */

public class listarUsuarios extends AppCompatActivity {

    private RecyclerViewAdapter viewAdapter;
    private List<jsonConsultaUsuarios> results = new ArrayList<>();
    private Communicator communicator;
    private RecyclerView recyclerView;

    private ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuarios);
        ButterKnife.bind(this);
        communicator = new Communicator(); //instanciando o objeto que aciona a interação com o server

        //exibindo mensagem de carregando ao usuario
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Carregando ...");
        progress.show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Listagem dos Usuários"); //titulo

        //instanciando o container pra exibir os dados
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //viewAdapter = new RecyclerViewAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(viewAdapter);

        //chamando a funcao que inicia a requisicao ao server
        communicator.listaUsuarios(); //listando os usuarios pela classe communicator.java
    }

    @Override //pegando o item clicado
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //funcoes necessarias para manipulação dos eventos do servidor
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

    //caso haja evento do servidor ao cadastrar:
    @Subscribe
    public void onServerEvent(ServerEventListarUsuario serverEventListarUsuario){
        int value = serverEventListarUsuario.getServerResponseListarUsuario().getResponseCode();
        if (value == 1) { //se a resposta do servidor for 1, OK
            results = serverEventListarUsuario.getServerResponseListarUsuario().getResult();
            viewAdapter = new RecyclerViewAdapter(listarUsuarios.this, results);
            recyclerView.setAdapter(viewAdapter);
        }
        progress.dismiss();
    }

    //se houver evento do servidor de erro:
    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Log.i("Erro",errorEvent.getErrorMsg());
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }

}
