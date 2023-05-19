package com.climax.climax;

import com.climax.climax.readFiles.CsvFileRead;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculMoyenne {
    public static Map<String, Double> calculateAverageSalaryByProfession(List<Employe> employes) {
        Map<String, Double> averageSalaries = new HashMap<>();
        Map<String, Integer> professionCounts = new HashMap<>();
        Map<String, Integer> professionTotalSalaries = new HashMap<>();

        for (Employe employe : employes) {
            String profession = employe.getProfession();
            int salary = employe.getSalaire();

            // Mettre à jour le total des salaires pour chaque profession
            professionTotalSalaries.merge(profession, salary, Integer::sum);

            // Mettre à jour le nombre de clients pour chaque profession
            professionCounts.merge(profession, 1, Integer::sum);
        }

        for (String profession : professionCounts.keySet()) {
            int totalCount = professionCounts.get(profession);
            int totalSalary = professionTotalSalaries.get(profession);
            double averageSalary = (double) totalSalary / totalCount;
            averageSalaries.put(profession, averageSalary);
        }

        return averageSalaries;
    }
}
