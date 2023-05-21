package com.climax.climax.readFiles;

import com.climax.climax.model.Employe;
import com.climax.climax.inteface.FileRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileRead implements FileRead {

    public List<Employe> readFile(File filePath) {
        List<Employe> employes = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] donnees = line.split(",");
                String nom = donnees[0].trim();
                String prenom = donnees[1].trim();
                int age = Integer.parseInt(donnees[2].trim());
                String profession = donnees[3].trim();
                int salaire = Integer.parseInt(donnees[4].trim());

                Employe employe = new Employe(nom, prenom, age, profession, salaire);
                employes.add(employe);
            }
        } catch (IOException e) {
            // Gérer les erreurs de lecture du fichier
            e.printStackTrace();
        }
        return employes;
    }

    @Override
    public List<Employe> readFile(String filePath) {
        List<Employe> employes = new ArrayList<>();
        return employes;
    }
}
