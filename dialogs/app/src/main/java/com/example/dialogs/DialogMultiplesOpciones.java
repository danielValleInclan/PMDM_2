package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class DialogMultiplesOpciones extends DialogFragment {
   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
       String[] arrayFrutas = {"Manzana", "Plátano"};
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

       builder.setMessage("Frutas");

       builder.setMultiChoiceItems(arrayFrutas, null, new DialogInterface.OnMultiChoiceClickListener(){
           @Override
           public void onClick(DialogInterface dialogInterface, int i, boolean isChecked){
                //Lógica para interactuar con los objetos seleccionados
           }
       });

       builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
                //Para guardar múltiples datos, en este caso las frutas que he seleccionado
           }
       });

       builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               //Cancela y cierra la ventana
           }
       });

       return builder.create();
   }
}