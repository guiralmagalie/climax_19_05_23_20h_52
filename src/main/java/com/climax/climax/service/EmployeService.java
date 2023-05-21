package com.climax.climax.service;

import com.climax.climax.CalculMoyenne;
import com.climax.climax.controller.EmployeController;
import com.climax.climax.inteface.FileReadRepository;
import com.climax.climax.model.Employe;
import com.climax.climax.inteface.EmployeRepository;
import com.climax.climax.model.FileUpload;
import com.climax.climax.readFiles.CsvFileRead;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class EmployeService {
    /**
     *à supprimer
     */

    private EmployeRepository employeRepository;
    private FileReadRepository fileReadRepository;
    private static Path root = Paths.get("uploadFiles2");

    @Autowired
    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    public Employe ajouterEmploye (Employe employe) throws Exception {
        if (!employe.getNom().isEmpty()){
            return employeRepository.save(employe);
        }else throw new Exception("Veuillez saisir un champ ");

    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
    //to delete C
    public void enregistrerEmployes(List<Employe> employes) {
        employeRepository.saveAll(employes);
    }

    /**
     * uploadFile
     * @param file
     * @throws IOException
     */
    public void uploadFile(MultipartFile file) throws IOException {
        List<Employe> employes = employeRepository.findAll();
        // Spécifiez le répertoire de destination pour enregistrer le fichier
        String destinationDirectory = "uploadFiles2";
        // Vérifiez si le fichier n'est pas vide
        if (!file.isEmpty()) {
            // Obtenez le nom d'origine du fichier
            String fileName = file.getOriginalFilename();
            // Créez le chemin complet pour le fichier de destination
            String destinationPath = destinationDirectory + fileName;
            // Enregistrez le fichier sur le disque
            File destFile = new File(destinationPath);
            file.transferTo(destFile);
            System.out.println("Le fichier a été uploadé avec succès.");
            System.out.println(destFile);
        } else {
            // Gérez le cas où aucun fichier n'a été fourni
            System.out.println("Aucun fichier n'a été fourni.");
        }
    }
    public Map<String, Double> getSalaireByProfession() {
        List<Object[]> result = employeRepository.getSalaireByProfession();
        Map<String, Double> moyenneSalaries = new HashMap<>();

        for (Object[] row : result) {
            String profession = (String) row[0];
            Double averageSalary = (Double) row[1];
            moyenneSalaries.put(profession, averageSalary);
        }

        return moyenneSalaries;
    }

    public void importEmployees(MultipartFile file) throws IOException {
        // Logic to import employees from the file and save them to the database
        List<Employe> employes = parseEmployesFromFile(file);
        employeRepository.saveAll(employes);
    }
    private List<Employe> parseEmployesFromFile(MultipartFile file) throws IOException {
        List<Employe> employes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5) {
                String nom = parts[0].trim();
                String prenom = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                String prof = parts[3].trim();
                int salair = Integer.parseInt(parts[4].trim());

                employes.add(new Employe(nom, prenom, age, prof, salair));
            }
        }
        return employes;
    }

    public Map<String, Double>  getMoyenne(){
        CsvFileRead csvReader = new CsvFileRead();
        List<Employe> employes = csvReader.readFile("src/main/java/com/climax/climax/fichiers/test.csv");
        return CalculMoyenne.calculSalaireParProfession(employes);
    }

    public Map<String, Double> importerEmployeFromFile(MultipartFile file) throws IOException {
        // Logique de traitement du fichier

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            List<Employe> employes = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employe employe = new Employe(data[0],data[1],Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4]));
                //  new Employe(data[0], data[1], Double.parseDouble(data[2]));
                // Integer.parseInt(donnees[2].trim());
                employes.add(employe);
            }

            uploadFile(file);
            // employeService.enregistrerEmployes(employes);
            return CalculMoyenne.calculSalaireParProfession(employes);
            //ResponseEntity.status(HttpStatus.CREATED).body("Importation réussie !");
        }catch (IOException e){
            return  null;
        }
    }






}
