package org.utl.helpdesk;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.utl.helpdesk.api.EmpleadoApi;
import org.utl.helpdesk.model.Empleado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegistroEmpleadoFragment extends Fragment {

    EditText etNombre;
    EditText etPrimerApellido;
    EditText etSegundoApellido;
    EditText etRFC;
    EditText etEmail;
    EditText etTelefono;
    EditText etUsuario;
    EditText etContrasenia;
    EditText etRol;
    EditText etDepartamento;

    ImageView imgFotoEmpleado;
    ImageButton btnSubir;
    EditText txtFoto;
    Button btnRegistro;

    String strImagen;

    private static String URL_BASE = "http://192.168.16.15:8080/help_desk/api/";
    public RegistroEmpleadoFragment() {
        // Required empty public constructor
    }
    public static RegistroEmpleadoFragment newInstance(String param1, String param2) {
        RegistroEmpleadoFragment fragment = new RegistroEmpleadoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registro_empleado, container, false);

        // Mapeo de Cajas de Texto
        etNombre = rootView.findViewById(R.id.etNombre);
        etPrimerApellido = rootView.findViewById(R.id.etPrimerApellido);
        etSegundoApellido = rootView.findViewById(R.id.etSegundoApellido);
        etRFC = rootView.findViewById(R.id.etRFC);
        etEmail = rootView.findViewById(R.id.etCorreo);
        etTelefono = rootView.findViewById(R.id.etTelefono);
        etUsuario = rootView.findViewById(R.id.etUser);
        etContrasenia = rootView.findViewById(R.id.etPassword);
        etRol = rootView.findViewById(R.id.etRol);
        etDepartamento = rootView.findViewById(R.id.etDepartamento);

        imgFotoEmpleado = rootView.findViewById(R.id.imgFoto);

        txtFoto = rootView.findViewById(R.id.txtFoto);

        //  Mapeo de Botones
        btnRegistro = rootView.findViewById(R.id.btnRegistroEmpleado);
        btnSubir = rootView.findViewById(R.id.btnSubir);

        btnRegistro.setOnClickListener(view -> {
            registrarEmpleado();
            Toast.makeText(getContext(), "Se pudo registrar un empleado", Toast.LENGTH_SHORT).show();
        });

        btnSubir.setOnClickListener(view -> {
            cargarFoto();

        });
        // Inflate the layout for this fragment
        return rootView;
    }



    public void registrarEmpleado() {
        String nombreEmpleado = String.valueOf(etNombre.getText());
        String primerApellido = String.valueOf(etSegundoApellido.getText());
        String segundoApellido = String.valueOf(etSegundoApellido.getText());
        String rfc = String.valueOf(etRFC.getText());
        String email = String.valueOf(etEmail.getText());
        String telefono = String.valueOf(etTelefono.getText());
        String foto = String.valueOf(txtFoto.getText());
        System.out.println(foto);
        String user = String.valueOf(etUsuario.getText());
        String pass = String.valueOf(etContrasenia.getText());
        String rol = String.valueOf(etRol.getText());
        String depa = String.valueOf(etDepartamento.getText());
        int h = Integer.parseInt(depa);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            EmpleadoApi apiregEmp= retrofit.create(EmpleadoApi.class);
            Call<Empleado> call = apiregEmp.insert(nombreEmpleado, primerApellido, segundoApellido, rfc,email,telefono,foto, user,pass,rol,h);

            call.enqueue(new Callback<Empleado>() {
                @Override
                public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        Toast.makeText(getContext(), "Se pudo registrar un empleado", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getContext(), "Ocurrió un error, vuelva a intentarlo", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Empleado> call, Throwable t) {
                    Toast.makeText(getContext(), "Se ha insertado un nuevo usuario", Toast.LENGTH_LONG).show();


                }
            });



    }


    /*Todo lo que tenga que ver con cargar foto*/
    public void cargarFoto() {
        encodeImage();
    }


    private void encodeImage() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }else {
            selectImage();
        }
    }


    private void selectImage() {
        txtFoto.setText("");
        imgFotoEmpleado.setImageBitmap(null);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione la imagen:"), 100 );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage();
        }else {
            Toast.makeText(getActivity(), "Los permisos fueron negados, cambie los permisos desde los ajustes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data!= null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                byte[] bytes = stream.toByteArray();
                strImagen = Base64.encodeToString(bytes, Base64.DEFAULT);
                txtFoto.setText(strImagen);
            }catch (IOException e){
                throw  new RuntimeException();
            }
        }
    }

    /*Todo lo que tenga que ver con cargar foto*/







}