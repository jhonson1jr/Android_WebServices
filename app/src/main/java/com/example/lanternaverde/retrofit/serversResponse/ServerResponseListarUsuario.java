package com.example.lanternaverde.retrofit.serversResponse;

import com.example.lanternaverde.retrofit.jsonConsultaUsuarios;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lanterna Verde on 19/08/2017.
 * Instancia e manipula os dados vindo do servidor
 */

public class ServerResponseListarUsuario implements Serializable {

    @SerializedName("response_code")
    private int responseCode;
    @SerializedName("resultado")
    private List<jsonConsultaUsuarios> resultado; //criando listagem da classe q acesso os dados do json vindo do server

    public ServerResponseListarUsuario(int responseCode, List<jsonConsultaUsuarios> resultado){
        this.responseCode = responseCode;
        this.resultado = resultado;
    }

    //metodos gets e sets

    public List<jsonConsultaUsuarios> getResult(){
        return resultado;
    }

    public void setResult(List<jsonConsultaUsuarios> resultado){
        this.resultado = resultado;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
