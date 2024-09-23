package com.example.gestiontareas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class GroupAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Group> groupList;
    private LayoutInflater inflater;

    public GroupAdapter(Context context, ArrayList<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView groupNameTextView = convertView.findViewById(R.id.textViewGroupName);
        TextView groupDescriptionTextView = convertView.findViewById(R.id.textViewGroupDescription);
        TextView groupCodeTextView = convertView.findViewById(R.id.textViewGroupCode); // Asegúrate de que este TextView está en tu layout

        Group group = groupList.get(position);

        groupNameTextView.setText(group.getGroupName());
        groupDescriptionTextView.setText(group.getGroupDescription());
        groupCodeTextView.setText(group.getGroupCode()); // Mostrar el código del grupo

        // Configurar el clic para abrir la nueva actividad
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TasksActivity.class);
            intent.putExtra("group_name", group.getGroupName());
            intent.putExtra("group_description", group.getGroupDescription());
            intent.putExtra("group_code", group.getGroupCode()); // Pasar el código al intent
            context.startActivity(intent);
        });

        return convertView;
    }
}