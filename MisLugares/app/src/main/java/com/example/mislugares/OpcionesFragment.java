package com.example.mislugares;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class OpcionesFragment extends PreferenceActivity implements Preference.OnPreferenceClickListener {
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        addPreferencesFromResource(R.xml.opciones);

        // Obtener referencia a las preferencias Cancelar y Confirmar
        Preference cancelButton = findPreference("cancel_button");
        Preference confirmButton = findPreference("confirm_button");

        // Establecer escuchadores de clic para los botones
        cancelButton.setOnPreferenceClickListener(this);
        confirmButton.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("cancel_button")) {
            // Lógica para el botón Cancelar
            finish(); // Cerrar la actividad y volver al MainActivity
        } else if (preference.getKey().equals("confirm_button")) {
            // Lógica para el botón Confirmar
            SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Obtener y guardar los valores de las preferencias
            EditTextPreference userPreference = (EditTextPreference) findPreference("user");
            String username = userPreference.getText();
            editor.putString("user", username);

            EditTextPreference passwdPreference = (EditTextPreference) findPreference("passwd");
            String password = passwdPreference.getText();
            editor.putString("passwd", password);

            // Aplicar los cambios
            editor.apply();

            // Mostrar notificación
            mostrarNotificacion("Preferencias confirmadas", username, password);


            // Cerrar la actividad y volver al MainActivity
            finish();
        }
        return false;
    }

    private void mostrarNotificacion(String mensaje, String username, String password) {
        Toast.makeText(this, mensaje + " " + username + " " + password, Toast.LENGTH_SHORT).show();
    }
}
