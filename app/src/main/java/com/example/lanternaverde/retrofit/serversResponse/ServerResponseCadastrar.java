package com.example.lanternaverde.retrofit.serversResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lanterna Verde on 16/08/2017.
 * vai gerenciar as respostas do servidor ao efetuar cadastro
 */

public class ServerResponseCadastrar implements Serializable{

    @SerializedName("message")
    private String message;
    @SerializedName("response_code")
    private int responseCode;

    public ServerResponseCadastrar(String message, int responseCode){
        this.message = message;
        this.responseCode = responseCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
