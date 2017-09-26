package com.example.lanternaverde.retrofit.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lanternaverde.retrofit.BusProvider;
import com.example.lanternaverde.retrofit.Communicator;
import com.example.lanternaverde.retrofit.ErrorEvent;
import com.example.lanternaverde.retrofit.R;
import com.example.lanternaverde.retrofit.serversEvent.ServerEventCadastrar;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;


/**
 * Created by Lanterna Verde on 20/08/2017.
 */

public class atualizarUsuario extends AppCompatActivity {
    private EditText txtEditNome, txtEditSenha;
    private Button btnAtualizar;
    private ProgressDialog progress;
    private Communicator communicator; //objeto da classe Communicator.java que faz meio de campo com

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualizar);
        ButterKnife.bind(this);

        communicator = new Communicator(); //instanciando o objeto que aciona a interação com o server
        btnAtualizar = (Button)findViewById(R.id.btnAtualizar);
        txtEditNome = (EditText)findViewById(R.id.txtEditNome);
        txtEditSenha = (EditText)findViewById(R.id.txtEditSenha);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Atualizar Usuário");

        //valores que virão do OnClick da classe ViewOrder do RecyclerViewAdapter.java
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id",1);
        String nome = intent.getStringExtra("nome");
        String senha= intent.getStringExtra("senha");

        txtEditNome.setText(nome);
        txtEditSenha.setText(senha);

        //instanciando a barra de progresso
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Atualizando ...");

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int id1 = Integer.parseInt(""+id); //convertendo o ID pra int (vem string) -- TA DANDO NULLPOINTER
                String novoNome = txtEditNome.getText().toString();
                String novaSenha = txtEditSenha.getText().toString();
                progress.show();
                communicator.atualizarUsuario(id, novoNome, novaSenha);
            }
        });
    }
    //metodos necessários para manipular as respostas do servidor
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
    public void onServerEvent(ServerEventCadastrar serverEventCadastrar){
        progress.dismiss();
        Toast.makeText(this, ""+serverEventCadastrar.getServerResponseCadastrar().getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, listarUsuarios.class)); //após atualizar, redireciona pra listagem
    }

    //se houver evento do servidor de erro:
    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Log.i("Erro",errorEvent.getErrorMsg());
        progress.dismiss();
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }
}
