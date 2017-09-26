package com.example.lanternaverde.retrofit;

/**
 * Created by Lanterna Verde on 19/08/2017.
 * tornar acessiveis os dados voltados pelo json do servidor
 */

public class jsonConsultaUsuarios {
    private String usuario, senha;
    private int id;

    public String getUsuario(){
        return usuario;
    }
    public String getSenha(){
        return senha;
    }
    public int getId(){
        return id;
    }
}
