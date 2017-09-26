package com.example.lanternaverde.retrofit.serversEvent;

import com.example.lanternaverde.retrofit.serversResponse.ServerResponseCadastrar;

/**
 * Created by Lanterna Verde on 16/08/2017.
 */

public class ServerEventCadastrar {

    private ServerResponseCadastrar serverResponseCadastrar;

    public ServerEventCadastrar(ServerResponseCadastrar serverResponseCadastrar) {
        this.serverResponseCadastrar = serverResponseCadastrar;
    }

    public ServerResponseCadastrar getServerResponseCadastrar() {
        return serverResponseCadastrar;
    }

    public void setServerResponseCadastrar(ServerResponseCadastrar serverResponseCadastrar) {
        this.serverResponseCadastrar = serverResponseCadastrar;
    }

}
