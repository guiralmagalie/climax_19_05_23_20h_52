package com.climax.climax;

import com.climax.climax.model.Employe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CalculMoyenne {
    public static Map<String, Double> calculSalaireParProfession(List<Employe> employes) {
        Map<String, Double> moyenneSalaire = new HashMap<>();
        Map<String, Integer> nombreProfessions = new HashMap<>();
        Map<String, Integer> totalSalariesProfession = new HashMap<>();

        for (Employe employe : employes) {
            String profession = employe.getProfession();
            int salary = employe.getSalaire();

            // Mettre à jour le total des salaires pour chaque profession
            totalSalariesProfession.merge(profession, salary, Integer::sum);

            // Mettre à jour le nombre de clients pour chaque profession
            nombreProfessions.merge(profession, 1, Integer::sum);
        }

        for (String profession : nombreProfessions.keySet()) {
            int totalCount = nombreProfessions.get(profession);
            int totalSalair = totalSalariesProfession.get(profession);
            double averageSalary = (double) totalSalair / totalCount;
            moyenneSalaire.put(profession, averageSalary);
        }

        return moyenneSalaire;
    }


}
