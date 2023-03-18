package org.utl.helpdesk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.utl.helpdesk.model.Empleado;

import java.util.List;

public class MyAdapterEmpleado extends RecyclerView.Adapter<MyAdapterEmpleado.MyEmpleadoViewHolder> {
    Context context;
    List<Empleado> empleadoList;


    public MyAdapterEmpleado(Context context, List<Empleado> empleadoList) {
        this.context = context;
        this.empleadoList = empleadoList;
    }

    @NonNull
    @Override
    public MyEmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_empleado, parent, false);
        return new MyEmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyEmpleadoViewHolder holder, int position) {
        Empleado empleado = empleadoList.get(position);
        holder.txtNombreEmpleado.setText(empleado.getNombreEmpleado() + " " +empleado.getPrimerApellido() + " " +empleado.getSegundoApellido());
        holder.txtRFC.setText(empleado.getRfc());
        holder.txtEmail.setText(empleado.getEmail());
        holder.txtTelefono.setText(empleado.getTelefono());

        byte[] decodedString = Base64.decode(empleado.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imgEmpleadoRv.setImageBitmap(decodedByte);

        // Establecer la cadena Base64 en el campo de texto
        holder.txtDepartamento.setText(empleado.getFoto());


    }

    @Override
    public int getItemCount() {
        return empleadoList.size() == 0 ? 0 : empleadoList.size();

    }

    public static class MyEmpleadoViewHolder extends RecyclerView.ViewHolder { //1.-Se extiende de RecyclerView

        TextView txtNombreEmpleado;
        TextView txtRFC;
        TextView txtEmail;
        TextView txtTelefono;
        TextView txtDepartamento;
        ImageView imgEmpleadoRv;

        public MyEmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreEmpleado = itemView.findViewById(R.id.txtNombreEmpleado);
            txtRFC = itemView.findViewById(R.id.txtRFC);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            imgEmpleadoRv = itemView.findViewById(R.id.imgEmpleadoRv);
            txtDepartamento = itemView.findViewById(R.id.txtDepartamento);



        }

    }



}
