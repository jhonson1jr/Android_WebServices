package com.example.lanternaverde.retrofit.views;

import android.app.ProgressDialog;
import android.os.Bundle;
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

/**
 * Created by Lanterna Verde on 19/08/2017.
 */

public class adicionaUsuario extends AppCompatActivity {

    private Communicator communicator; //objeto da classe Communicator.java que faz meio de campo com
    private String username, password;
    private EditText txtNome, txtSenha;
    private Button btnCadastrar;
    private ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro); //chama tela de cadastro de usuarios

        communicator = new Communicator(); //instanciando o objeto que aciona a interação com o server
        //instanciando os objetos:
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Cadastrando ...");

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show(); //exibe na tela o andamento
                btnCadastrar.setEnabled(false); //desabilitando o botao ao clicar
                username = txtNome.getText().toString();
                password = txtSenha.getText().toString();
                cadastraUsuario(username, password);
            }
        });
    }

    //cadastrando novo usuario:
    private void cadastraUsuario(String nome, String senha){
        communicator.efetuarCadastro(nome, senha);
        //após cadastrar, habilita botao, oculta barra de progresso e limpa os campos:
        btnCadastrar.setEnabled(true);
        progress.dismiss();
        txtNome.setText("");
        txtSenha.setText("");
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
        Toast.makeText(this, ""+serverEventCadastrar.getServerResponseCadastrar().getMessage(), Toast.LENGTH_SHORT).show();
    }

    //se houver evento do servidor de erro:
    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Log.i("Erro",errorEvent.getErrorMsg());
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }
}
