package org.utl.helpdesk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.utl.helpdesk.api.EmpleadoApi;
import org.utl.helpdesk.model.Empleado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Listado_empleado extends Fragment {
    private static String BASE_URL = "http://192.168.72.94:8080/help_desk/api/";

    public List<Empleado> listaEmpleado = new ArrayList<>();
    RecyclerView recyclerViewEmpleados;
    MyAdapterEmpleado myAdapterEmpleado;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_listado_empleado, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("Estamos en el onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        recyclerViewEmpleados = view.findViewById(R.id.recyclerViewEmpleados);
        recyclerViewEmpleados.setLayoutManager(new LinearLayoutManager(getActivity()));
        cargarEmpleados();

    }

    public void cargarEmpleados() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmpleadoApi empleadoApi = retrofit.create(EmpleadoApi.class);
        Call<List<Empleado>> listadoEmpleado = empleadoApi.getAll();
        listadoEmpleado.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (response.code() == 200) {
                    for (Empleado e:response.body()) {
                        listaEmpleado.add(e);
                        System.out.println(e.getNombreEmpleado());
                    }
                    myAdapterEmpleado = new MyAdapterEmpleado(getActivity(), listaEmpleado);
                    recyclerViewEmpleados.setAdapter(myAdapterEmpleado);
                    myAdapterEmpleado.notifyDataSetChanged();
                    System.out.println(listaEmpleado.size());
                } else  if (response.code() == 404) {
                    System.out.println("No se pudo mandar datos");
                    Toast.makeText(getContext(), "Lista vacia", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast.makeText(getContext(), "Intentelo nuevamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}