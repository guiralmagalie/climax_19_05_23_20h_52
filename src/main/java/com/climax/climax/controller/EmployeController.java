package com.climax.climax.controller;

import com.climax.climax.model.Employe;
import com.climax.climax.service.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employe")
public class EmployeController {

    private  final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }
    /**
     * méthode création d'un employé
     */
    @PostMapping("/empl")
    public ResponseEntity<?> creerEmploye(@RequestBody Employe employe) throws Exception {
        Employe employeSaved = employeService.ajouterEmploye(employe);
        return ResponseEntity.status(HttpStatus.OK).body(employeSaved);
    }
}
