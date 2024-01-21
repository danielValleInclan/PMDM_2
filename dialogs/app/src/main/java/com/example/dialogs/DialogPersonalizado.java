package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class DialogPersonalizado extends DialogFragment {

    private EditText username;
    private EditText password;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflator = requireActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.custom_dialog, null);
        builder.setView(view);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        builder.setPositiveButton("Iniciar sesión", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(username.getText().toString().length() > 0){
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    listener.aplicarTextos(user, pass);
                }
            }
        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
            // Intenta realizar un casting del contexto (context) a un objeto que implemente la interfaz DialogListener.
            // Esto significa que esperas que la actividad que contiene el fragmento implemente la interfaz DialogListener.
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar DialogListener");
        }
    }

    public interface DialogListener{
        void aplicarTextos(String username, String password);
    }
}
