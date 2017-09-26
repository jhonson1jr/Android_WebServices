package com.example.lanternaverde.retrofit.views;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by Lanterna Verde on 19/08/2017.
 */

public class inicio  extends ListActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] cursos = new String[]{"Novo usuario", "Listar Usuarios", "Sair"};
        //criando um adaptador inserindo um modelo xml padrao do android pra listagem passando o array por parametro:
        ArrayAdapter<String> aaCursos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cursos);
        setListAdapter(aaCursos);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);//instanciando
        Object objSelecionado = this.getListAdapter().getItem(position); //pegando o nome do menu selecionado
        String menuSelecionado = objSelecionado.toString(); //convertendo pra string
        switch (position){
            case 0: startActivity(new Intent(this, adicionaUsuario.class)); break;//0 novo usuario (pré cadastrado no manifest.xml
            case 1: startActivity(new Intent(this, listarUsuarios.class));break; //listar usuarios;
            case 2: finish(); //encerra aplicacao
        }
    }


}
