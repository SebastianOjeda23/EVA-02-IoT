package com.example.gestiontareas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private ArrayList<Task> allTasks; // Lista de todas las tareas
    private ArrayList<Task> displayedTasks; // Tareas que se muestran
    private TaskAdapter taskAdapter;
    private TabLayout tabLayout;
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        tabLayout = findViewById(R.id.tabLayout);
        listViewTasks = findViewById(R.id.listViewTasks);

        // Inicializar lista de tareas
        allTasks = new ArrayList<>();
        displayedTasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, displayedTasks);

        listViewTasks.setAdapter(taskAdapter);
        setupTabs();

        Button btnAddTask = findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(v -> showAddTaskDialog());
    }

    private void setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Pendientes"));
        tabLayout.addTab(tabLayout.newTab().setText("Completadas"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateDisplayedTasks(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                updateDisplayedTasks(tab.getPosition());
            }
        });

        updateDisplayedTasks(0); // Mostrar tareas pendientes por defecto
    }

    private void updateDisplayedTasks(int tabPosition) {
        displayedTasks.clear();
        if (tabPosition == 0) { // Tareas pendientes
            for (Task task : allTasks) {
                if (!task.isCompleted()) {
                    displayedTasks.add(task);
                }
            }
        } else { // Tareas completadas
            for (Task task : allTasks) {
                if (task.isCompleted()) {
                    displayedTasks.add(task);
                }
            }
        }
        taskAdapter.notifyDataSetChanged();
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

        final TextView editTaskName = dialogView.findViewById(R.id.editTaskName);
        final TextView editTaskDescription = dialogView.findViewById(R.id.editTaskDescription);
        Button btnDialogAddTask = dialogView.findViewById(R.id.btnDialogAddTask);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnDialogAddTask.setOnClickListener(v -> {
            String taskName = editTaskName.getText().toString();
            String taskDescription = editTaskDescription.getText().toString();

            if (!taskName.isEmpty()) {
                // Crear la tarea como incompleta
                Task newTask = new Task(taskName, taskDescription, false);
                allTasks.add(newTask); // Agregar a la lista de todas las tareas
                updateDisplayedTasks(tabLayout.getSelectedTabPosition()); // Actualizar vista
                dialog.dismiss();
            } else {
                editTaskName.setError("El nombre de la tarea es obligatorio");
            }
        });

        dialog.show();
    }
}