package com.climax.climax.service;

import com.climax.climax.model.Employe;
import com.climax.climax.inteface.EmployeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeService {
    private EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    public Employe ajouterEmploye (Employe employe) throws Exception {
        if (!employe.getNom().isEmpty()){
            return employeRepository.save(employe);
        }else throw new Exception("Veuillez saisir un champ ");

    }
}
