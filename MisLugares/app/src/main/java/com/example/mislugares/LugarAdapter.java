package com.example.mislugares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mislugares.model.Lugar;
import com.example.mislugares.util.ImageUtils;

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
        RatingBar ratingBar = convertView.findViewById(R.id.ratingBarValoracion);
        ImageView imageViewImagen = convertView.findViewById(R.id.imageViewImagen);

        textViewNombre.setText(lugar.getNombre());
        ratingBar.setRating((float) lugar.getValoracion());

        byte[] imagenData = lugar.getImagenBytes();

        ImageUtils.mostrarImagenDesdeByteArray(imagenData, imageViewImagen);

        return convertView;
    }
}

