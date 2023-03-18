package org.utl.helpdesk.api;

import org.utl.helpdesk.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmpleadoApi {
    @GET("empleado/getAll")
    Call<List<Empleado>> getAll();

    @GET("empleado/insertar?")
    Call<Empleado> insert(
            @Query("nombreEmpleado") String nombreEmp,
            @Query("primerApellido") String primerApellido,
            @Query("segundoApellido") String segundoApellido,
            @Query("rfc") String rfc,
            @Query("email") String email,
            @Query("telefono") String telefono,
            @Query("foto") String foto,
            @Query("nombreUsuario") String user,
            @Query("contrasenia") String pass,
            @Query("rol") String rol,
            @Query("idDepartamento") int depa

    );

}
