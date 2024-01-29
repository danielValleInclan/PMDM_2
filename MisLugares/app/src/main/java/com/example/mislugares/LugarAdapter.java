package com.example.mislugares;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mislugares.model.Lugar;

import java.util.List;

public class LugarAdapter extends ArrayAdapter<Lugar> {

    private List<Lugar> lugares;

    public LugarAdapter(@NonNull Context context, int resource, @NonNull List<Lugar> lugares) {
        super(context, resource, lugares);
        this.lugares = lugares;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        Lugar lugar = lugares.get(position);

        // Asociar datos del lugar a las vistas del diseño
        TextView textViewNombre = convertView.findViewById(R.id.textViewNombre);
        TextView textViewValoracion = convertView.findViewById(R.id.textViewValoracion);
        TextView textViewTipoLugar = convertView.findViewById(R.id.textViewTipoLugar);
        TextView textViewDireccion = convertView.findViewById(R.id.textViewDireccion);
        ImageView imageViewImagen = convertView.findViewById(R.id.imageViewImagen);

        textViewNombre.setText(lugar.getNombre());
        textViewValoracion.setText(String.valueOf(lugar.getValoracion()));
        textViewTipoLugar.setText(lugar.getTipoLugar());
        textViewDireccion.setText(lugar.getDireccion());

        // Obtén la ruta de la imagen desde el objeto Lugar
        String rutaImagen = lugar.getPathImagen();

        // Verifica si la ruta de la imagen no es nula o vacía
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            // Cargar la imagen desde la ruta y establecerla en el ImageView
            // Puedes usar cualquier biblioteca de carga de imágenes como Glide o Picasso
            // Aquí un ejemplo básico:
            Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
            imageViewImagen.setImageBitmap(bitmap);
        } else {
            // Si la ruta de la imagen está vacía, puedes establecer una imagen de fallback o dejar el ImageView vacío
            imageViewImagen.setImageResource(R.drawable.altavoz);
        }



        return convertView;
    }
}

