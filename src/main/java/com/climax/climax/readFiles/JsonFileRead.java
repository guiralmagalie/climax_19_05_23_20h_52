package com.climax.climax.readFiles;

import com.climax.climax.model.Employe;
import com.climax.climax.inteface.FileRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileRead implements FileRead {
    @Override
    public List<Employe> readFile(String filePath) {
        List<Employe> employes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Utiliser une bibliothèque JSON pour désérialiser chaque ligne en objet Employe
                Employe employe = parseJson(line);
                employes.add(employe);
            }
        } catch (IOException e) {
            e.printStackTrace();

    }
        return employes;
    }

    private Employe parseJson(String json) {
        Employe employe = parseJson(json);
        return employe;
    }
    }
