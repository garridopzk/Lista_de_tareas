package com.example.lista_de_tareas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText ingreso;
    TextView nombre;
    Button menuButton;
    List<String> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ingreso = findViewById(R.id.ingreso);
        nombre = findViewById(R.id.tareas);
        menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMenu();
            }
        });
    }

    private void mostrarMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Menu");

        String[] opciones = {"Añadir Tarea", "Borrar Tarea", "Buscar Tarea"};
        builder.setItems(opciones, (dialog, which) -> {
            switch (which) {
                case 0:
                    añadirTarea();
                    break;
                case 1:
                    borrarTarea();
                    break;
                case 2:
                    buscarTarea();
                    break;
            }
        });

        builder.show();
    }

    private void añadirTarea() {
        String task = ingreso.getText().toString();
        if (!task.isEmpty()) {
            tasks.add(task);
            updateTasksDisplay();
            ingreso.getText().clear();
            Toast.makeText(MainActivity2.this, "Tarea añadida", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "Por favor, ingrese una tarea", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrarTarea() {
        if (!tasks.isEmpty()) {
            tasks.remove(tasks.size() - 1);
            updateTasksDisplay();
            Toast.makeText(MainActivity2.this, "Tarea borrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "No hay tareas para borrar", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarTarea() {
        String tareaBuscada = ingreso.getText().toString();
        boolean tareaEncontrada = false;
        for (String tarea : tasks) {
            if (tarea.equalsIgnoreCase(tareaBuscada)) {
                tareaEncontrada = true;
                break;
            }
        }
        if (tareaEncontrada) {
            nombre.setText("Tarea encontrada: " + tareaBuscada);
        } else {
            nombre.setText("Tarea no encontrada");
        }
    }

    private void updateTasksDisplay() {
        nombre.setText(String.join("\n", tasks));
    }
}
