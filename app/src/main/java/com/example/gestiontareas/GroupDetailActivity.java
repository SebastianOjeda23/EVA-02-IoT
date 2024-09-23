package com.example.gestiontareas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GroupDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        TextView groupNameTextView = findViewById(R.id.textViewGroupName);
        TextView groupDescriptionTextView = findViewById(R.id.textViewGroupDescription);
        TextView groupCodeTextView = findViewById(R.id.textViewGroupCode); // Nuevo TextView para el código

        String groupName = getIntent().getStringExtra("group_name");
        String groupDescription = getIntent().getStringExtra("group_description");
        String groupCode = getIntent().getStringExtra("group_code"); // Obtener el código

        groupNameTextView.setText(groupName);
        groupDescriptionTextView.setText(groupDescription);
        groupCodeTextView.setText("Código: " + groupCode); // Mostrar el código
    }
}