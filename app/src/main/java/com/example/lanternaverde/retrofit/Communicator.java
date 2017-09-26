package com.example.lanternaverde.retrofit;

import android.util.Log;

import com.example.lanternaverde.retrofit.serversEvent.ServerEvent;
import com.example.lanternaverde.retrofit.serversEvent.ServerEventCadastrar;
import com.example.lanternaverde.retrofit.serversEvent.ServerEventListarUsuario;
import com.example.lanternaverde.retrofit.serversResponse.ServerResponse;
import com.example.lanternaverde.retrofit.serversResponse.ServerResponseCadastrar;
import com.example.lanternaverde.retrofit.serversResponse.ServerResponseListarUsuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Produce;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lanterna Verde on 07/08/2017.
 * executa a chamada e contém os métodos que criam o Rest Adapter.
 */

public class Communicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://192.168.249.2/"; //fixei este IP para ser o servidor

    //efetuando login
    public void loginPost(String username, String password){

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.post("login",username,password);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    //cadastrar novo usuario
    public void efetuarCadastro(String username, String password){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Interface api = retrofit.create(Interface.class);
        Call<ServerResponseCadastrar> call = api.cadastrar("cadastrar", username, password);
        call.enqueue(new Callback<ServerResponseCadastrar>() {
            @Override
            public void onResponse(Call<ServerResponseCadastrar> call, Response<ServerResponseCadastrar> response) {
                BusProvider.getInstance().post(new ServerEventCadastrar(response.body()));
                Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<ServerResponseCadastrar> call, Throwable t) {
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });

    }

    //atualizar usuario recebendo id, nome e senha por parametro
    public void atualizarUsuario(int id, String username, String password){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Interface api = retrofit.create(Interface.class);
        Call<ServerResponseCadastrar> call = api.atualizarUsuario(id,username,password);
        call.enqueue(new Callback<ServerResponseCadastrar>() {
            @Override
            public void onResponse(Call<ServerResponseCadastrar> call, Response<ServerResponseCadastrar> response) {
                BusProvider.getInstance().post(new ServerEventCadastrar(response.body()));
                Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<ServerResponseCadastrar> call, Throwable t) {
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });

    }

    //listar todos os usuarios
    public void listaUsuarios(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Interface api = retrofit.create(Interface.class);
        Call<ServerResponseListarUsuario> call = api.visualizarUsuarios();
        call.enqueue(new Callback<ServerResponseListarUsuario>() {
            @Override
            public void onResponse(Call<ServerResponseListarUsuario> call, Response<ServerResponseListarUsuario> response) {
                BusProvider.getInstance().post(new ServerEventListarUsuario(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponseListarUsuario> call, Throwable t) {
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    public void loginGet(String username, String password) {
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.get("login", username, password);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });

    }
    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }

    @Produce
    public ServerEventCadastrar produceServerEventCadastrar(ServerResponseCadastrar serverResponseCadastrar) {
        return new ServerEventCadastrar(serverResponseCadastrar);
    }

    @Produce
    public ServerEventListarUsuario produceServerEventCadastrar(ServerResponseListarUsuario serverResponseListarUsuario) {
        return new ServerEventListarUsuario(serverResponseListarUsuario);
    }

}
