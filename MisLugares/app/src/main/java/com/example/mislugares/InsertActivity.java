package com.example.mislugares;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mislugares.model.GeoPunto;
import com.example.mislugares.model.Lugar;
import com.example.mislugares.model.TipoLugar;
import com.example.mislugares.repository.LugaresRepository;
import com.example.mislugares.repository.impl.LugaresImpl;
import com.example.mislugares.util.ImageUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

public class InsertActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText comentarioEditText;
    private EditText ubicacionEditText;
    private EditText urlEditText;
    private RadioGroup radioGroup;
    private byte[] imageData;
    private Button btnSeleccionarImagen;
    private Button btnCapturePhoto;
    private LugaresRepository lugaresImpl;

    private static final int PICK_IMAGE_REQUEST = 1; // Para imagen
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar el botón de retroceso en la barra de acción

        lugaresImpl = new LugaresImpl(this);

        // Inicializar vistas
        nombreEditText = findViewById(R.id.editTextNombre);
        comentarioEditText = findViewById(R.id.editTextComentario);
        ubicacionEditText = findViewById(R.id.editTextUbicacion);
        urlEditText = findViewById(R.id.editTextUrl);
        radioGroup = findViewById(R.id.radioGroup);

        // Imagen

        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnCapturePhoto = findViewById(R.id.btnCapturarFoto);
        btnCapturePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void selectImageFromGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imageData = ImageUtils.bitmapToByteArray(imageBitmap);
                    Log.d("IMAGEN", "onActivityResult: imageData -> " + Arrays.toString(imageData));
                    mostrarImagen(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_IMAGE_CAPTURE && data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageData = ImageUtils.bitmapToByteArray(imageBitmap);
                mostrarImagen(imageBitmap);
            }
        }
    }

    private void mostrarImagen(Bitmap bitmap){
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            // Manejar el clic en el botón de retroceso
            finish(); // Cerrar la actividad
        } else if (itemId == R.id.action_save) {
            // Guardar el nuevo lugar
            guardarNuevoLugar();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    private void guardarNuevoLugar() {
        // Obtener datos de los campos de entrada
        String nombre = nombreEditText.getText().toString().trim();
        String comentario = comentarioEditText.getText().toString().trim();
        String ubicacion = ubicacionEditText.getText().toString().trim();
        String url = urlEditText.getText().toString().trim();
        int tipoLugarId = radioGroup.getCheckedRadioButtonId();
        TipoLugar tipoLugar = null;

        // Validar los datos

        if (tipoLugarId == R.id.radioButtonBar) {
            tipoLugar = TipoLugar.BAR;
        } else if (tipoLugarId == R.id.radioButtonMirador) {
            tipoLugar = TipoLugar.MIRADOR;
        } else if (tipoLugarId == R.id.radioButtonMuseo) {
            tipoLugar = TipoLugar.MUSEO;
        } else if (tipoLugarId == R.id.radioButtonParque) {
            tipoLugar = TipoLugar.PARQUE;
        }


        // Crear un nuevo objeto Lugar con los datos
        GeoPunto geoPunto = new GeoPunto(0.0, 0.0);
        Lugar nuevoLugar = new Lugar(
                nombre,
                ubicacion,
                comentario,
                geoPunto,
                0.0,
                url,
                new Date(),
                tipoLugar,
                imageData
        );

        Log.d("IMAGEN", "guardarNuevoLugar: Imagen -> " + imageData);

        // Agregar el nuevo lugar a la base de datos
        lugaresImpl.addLugar(nuevoLugar);

        // Cerrar la actividad
        finish();
    }
}
