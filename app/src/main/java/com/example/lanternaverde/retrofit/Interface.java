package com.example.lanternaverde.retrofit;

import com.example.lanternaverde.retrofit.serversResponse.ServerResponse;
import com.example.lanternaverde.retrofit.serversResponse.ServerResponseCadastrar;
import com.example.lanternaverde.retrofit.serversResponse.ServerResponseListarUsuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lanterna Verde on 07/08/2017.
 * vai executar a solicitacao ao servidor
 */

public interface Interface {
    //Login via "POST"
    @FormUrlEncoded
    @POST("/android/api.php")
    Call<ServerResponse> post(
            @Field("method") String method,
            @Field("username") String username,
            @Field("password") String password
    );

    //cadastrando via post:
    @FormUrlEncoded
    @POST("/android/insert.php")
    Call<ServerResponseCadastrar> cadastrar(
            @Field("method") String method,
            @Field("username") String username,
            @Field("password") String password
    );

    //listando usuarios cadastrados
    @GET("/android/selectUser.php")
    Call<ServerResponseListarUsuario> visualizarUsuarios();

    //atualizando usuario:
    @FormUrlEncoded
    @POST("/android/updateUser.php")
    Call<ServerResponseCadastrar> atualizarUsuario(
            @Field("id") int id,
            @Field("username") String username,
            @Field("password") String password
    );


    //This method is used for "GET"
    @GET("/android/api.php")
    Call<ServerResponse> get(
            @Query("method") String method,
            @Query("username") String username,
            @Query("password") String password
    );
}