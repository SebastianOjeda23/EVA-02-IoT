package com.example.gestiontareas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private ArrayList<Group> groupList;
    private GroupAdapter groupAdapter;
    private ListView listViewGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listViewGroups = findViewById(R.id.listViewGroups);
        Button btnAddGroup = findViewById(R.id.btnAddGroup);
        Button btnJoinGroup = findViewById(R.id.btnJoinGroup); // Agregado el botón aquí

        groupList = new ArrayList<>();
        groupAdapter = new GroupAdapter(this, groupList);
        listViewGroups.setAdapter(groupAdapter);

        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGroupDialog();
            }
        });

        btnJoinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinGroupDialog();
            }
        });
    }

    private void showAddGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_group, null);

        final EditText editGroupName = dialogView.findViewById(R.id.editGroupName);
        final EditText editGroupDescription = dialogView.findViewById(R.id.editGroupDescription);
        Button btnAdd = dialogView.findViewById(R.id.btnDialogAdd);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = editGroupName.getText().toString();
                String groupDescription = editGroupDescription.getText().toString();

                if (!groupName.isEmpty() && !groupDescription.isEmpty()) {
                    groupList.add(new Group(groupName, groupDescription));
                    groupAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(MainActivity4.this, "Grupo agregado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity4.this, "Por favor, completa ambos campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    private void showJoinGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_join_group, null);

        final EditText editGroupCode = dialogView.findViewById(R.id.editGroupCode);
        Button btnJoin = dialogView.findViewById(R.id.btnDialogJoin);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupCode = editGroupCode.getText().toString();
                // Aquí puedes implementar la lógica para unirte al grupo usando el código
                if (!groupCode.isEmpty()) {
                    // Ejemplo de acción: agregar grupo temporalmente
                    groupList.add(new Group("Grupo " + groupCode, "Descripción de " + groupCode)); // Cambia según tu lógica
                    groupAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity4.this, "Unido al grupo " + groupCode, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity4.this, "Por favor, ingresa un código de grupo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
}
