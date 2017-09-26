package com.example.lanternaverde.retrofit.serversEvent;

import com.example.lanternaverde.retrofit.serversResponse.ServerResponse;

/**
 * Created by Lanterna Verde on 08/08/2017.
 * para mostrar respostas do servidor
 */

public class ServerEvent {
    private ServerResponse serverResponse;

    public ServerEvent(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

}
