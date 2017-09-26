package com.example.lanternaverde.retrofit.serversEvent;

import com.example.lanternaverde.retrofit.serversResponse.ServerResponseListarUsuario;

/**
 * Created by Lanterna Verde on 19/08/2017.
 */

public class ServerEventListarUsuario {

    private ServerResponseListarUsuario serverResponseListarUsuario;

    public ServerEventListarUsuario(ServerResponseListarUsuario serverResponseListarUsuario) {
        this.serverResponseListarUsuario = serverResponseListarUsuario;
    }

    public ServerResponseListarUsuario getServerResponseListarUsuario() {
        return serverResponseListarUsuario;
    }

    public void setServerResponseListarUsuario(ServerResponseListarUsuario serverResponseListarUsuario) {
        this.serverResponseListarUsuario = serverResponseListarUsuario;
    }
}
