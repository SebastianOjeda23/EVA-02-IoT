package com.example.gestiontareas;

import java.util.Random;

public class Group {
    private String groupName;
    private String groupDescription;
    private String groupCode; // Nuevo campo para el código

    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupCode = generateRandomCode(); // Generar el código al crear el grupo
    }

    private String generateRandomCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Puedes incluir números si deseas

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getGroupCode() {
        return groupCode; // Método para obtener el código del grupo
    }
}
