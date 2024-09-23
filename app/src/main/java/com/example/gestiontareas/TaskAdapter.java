package com.example.gestiontareas;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Task> tasks;           // Lista de tareas mostradas
    private LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<Task> displayedTasks) {
        this.context = context;
        this.tasks = displayedTasks; // Tareas que se muestran
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_task, parent, false);
        }

        TextView taskNameTextView = convertView.findViewById(R.id.textViewTaskName);
        TextView taskDescriptionTextView = convertView.findViewById(R.id.textViewTaskDescription);
        CheckBox checkBoxCompleted = convertView.findViewById(R.id.checkBoxCompleted);
        Button btnEditTask = convertView.findViewById(R.id.btnEditTask);
        Button btnDeleteTask = convertView.findViewById(R.id.btnDeleteTask);

        Task task = tasks.get(position);

        taskNameTextView.setText(task.getTaskName());
        taskDescriptionTextView.setText(task.getTaskDescription());

        // Manejar el cambio del estado de la tarea
        checkBoxCompleted.setOnCheckedChangeListener(null);
        checkBoxCompleted.setChecked(task.isCompleted());

        checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked); // Cambiar el estado de la tarea
            notifyDataSetChanged(); // Refrescar la lista
        });

        // Editar la tarea
        btnEditTask.setOnClickListener(v -> showEditTaskDialog(task));

        // Eliminar la tarea
        btnDeleteTask.setOnClickListener(v -> {
            tasks.remove(position);
            notifyDataSetChanged(); // Refrescar la lista después de eliminar la tarea
        });

        return convertView;
    }

    private void showEditTaskDialog(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);

        final TextView editTaskName = dialogView.findViewById(R.id.editTaskName);
        final TextView editTaskDescription = dialogView.findViewById(R.id.editTaskDescription);
        Button btnUpdateTask = dialogView.findViewById(R.id.btnDialogAddTask);

        // Mostrar los valores actuales de la tarea
        editTaskName.setText(task.getTaskName());
        editTaskDescription.setText(task.getTaskDescription());

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnUpdateTask.setText("Actualizar");
        btnUpdateTask.setOnClickListener(v -> {
            String updatedTaskName = editTaskName.getText().toString();
            String updatedTaskDescription = editTaskDescription.getText().toString();

            if (!updatedTaskName.isEmpty()) {
                task.setTaskName(updatedTaskName);
                task.setTaskDescription(updatedTaskDescription);
                notifyDataSetChanged(); // Refrescar la lista después de la edición
                dialog.dismiss();
            } else {
                editTaskName.setError("El nombre de la tarea es obligatorio");
            }
        });

        dialog.show();
    }
}