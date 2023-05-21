package com.climax.climax.controller;

import com.climax.climax.CalculMoyenne;
import com.climax.climax.exception.UploadResponseMessage;
import com.climax.climax.model.Employe;
import com.climax.climax.readFiles.CsvFileRead;
import com.climax.climax.service.EmployeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/employe")
@MultipartConfig
public class EmployeController {

    private  final EmployeService employeService;

    @Autowired
    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    //à delete
    private static  final List<String> contentTypes = Arrays.asList("file/csv", "file/json", "file/txt");


    /**
     * méthode création d'un employé
     */
    @PostMapping("/empl")
    public ResponseEntity<?> creerEmploye(@RequestBody Employe employe) throws Exception {
        Employe employeSaved = employeService.ajouterEmploye(employe);
        return ResponseEntity.status(HttpStatus.OK).body(employeSaved);
    }

    /**
     * methode pour recuperer la moyenne
     * @return
     */
    /**
     clcul moyenne
     */
    @GetMapping("/getMoyenne")
    public  Map<String, Double> calculMoyenne(){
        return employeService.getMoyenne();
    }

    @GetMapping("/")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.getAllEmployes();
        return ResponseEntity.ok(employes);
    }

    /**
     calcul moyenne salaire
     */
    @GetMapping("/salaireM")
    public Map<String, Double> getSalaireByProfession() {
        return employeService.getSalaireByProfession();
    }

    //to delete C

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/importation/file")
    public Map<String, Double> importerEmployeClient(@RequestParam("file") MultipartFile file) throws IOException {
        return employeService.importerEmployeFromFile(file);
    }

}
