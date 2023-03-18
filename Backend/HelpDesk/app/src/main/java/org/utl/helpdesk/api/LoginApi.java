package org.utl.helpdesk.api;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("log/in")

    Call<Void> login(@Query("usuario") String usuario,
                     @Query("contrasenia") String password);


}
