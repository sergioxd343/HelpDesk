package org.utl.helpdesk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button btnEmpleado;
    Button btnTicket;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        btnEmpleado = findViewById(R.id.btnEmpleado);
        btnTicket = findViewById(R.id.btnTicket);
        btnLogout = findViewById(R.id.btnLogout);

        btnEmpleado.setOnClickListener(view -> {
            Intent intentEmpleado = new Intent(HomeActivity.this, EmpleadoActivity.class);
            Toast.makeText(this, "Registre un empleado", Toast.LENGTH_LONG).show();
            startActivity(intentEmpleado);
        });

        btnTicket.setOnClickListener(view -> {
            Intent intentTicket = new Intent(this, TicketActivity.class);
            Toast.makeText(this, "Crea el ticket", Toast.LENGTH_LONG).show();
            startActivity(intentTicket);
        });

        btnLogout.setOnClickListener(view -> {
            Intent intentLogout = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Se cerró la sesión", Toast.LENGTH_LONG).show();
            startActivity(intentLogout);
        });
    }
}
