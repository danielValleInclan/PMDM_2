package com.example.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DialogPersonalizado.DialogListener {

    Button dialogSi;
    Button pbDialog;
    Button dialogMO;
    Button dialogLista;
    Button dialogCustom;
    Button dialogDate;
    Button dialogTime;
    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogSi = findViewById(R.id.dialogSi);
        pbDialog = findViewById(R.id.pbDialog);
        dialogMO = findViewById(R.id.dialogMultiplesOpciones);
        dialogLista = findViewById(R.id.dialogLista);
        dialogCustom = findViewById(R.id.dialogCustom);
        dialogDate = findViewById(R.id.dialogDate);
        dialogTime = findViewById(R.id.dialogTime);
        username = findViewById(R.id.textViewUsername);
        password = findViewById(R.id.textViewPass);

        dialogSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //dialog.setCancelable(false);
                dialog.setMessage("¿Salir?");
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        pbDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.dialog_pb);

            AlertDialog alertDialog = builder.create();
            alertDialog.setMessage("Cargando datos");
            alertDialog.show();
            }
        });

        dialogMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogMultiplesOpciones dialogMO = new DialogMultiplesOpciones();
                dialogMO.show(getSupportFragmentManager(), "DialogRadio");
            }
        });

        dialogLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLista dialogLista = new DialogLista();
                dialogLista.show(getSupportFragmentManager(), "DialogLista");
            }
        });

        dialogCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPersonalizado dialogPersonalizado = new DialogPersonalizado();
                dialogPersonalizado.show(getSupportFragmentManager(), "Dialog personalizado");
            }
        });

        dialogDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Aquí puedes manejar la fecha seleccionada
                                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                                Toast.makeText(MainActivity.this, "Fecha seleccionada: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
                            }
                        }, anio, mes, day
                );
                datePickerDialog.show();
            }
        });

        dialogTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR);
                int minutos = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String tiempoSeleccionado = "Has seleccionado: " + i + ":" + i1;
                        Toast.makeText(MainActivity.this, tiempoSeleccionado, Toast.LENGTH_SHORT).show();
                    }
                },hora,minutos, true // false para usar el formato de 24 horas, true para usar el formato de 12 horas
                );
                timePickerDialog.show();
            }
        });
    }

    public void aplicarTextos(String nombre, String contrasena){
        username.setText(nombre);
        password.setText(contrasena);
    }
}