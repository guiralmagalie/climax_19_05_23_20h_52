package com.climax.climax.service;

import com.climax.climax.inteface.EmployeRepository;
import com.climax.climax.inteface.FileReadRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFiles {
    private static Path root = Paths.get("uploadFiles");
    private FileReadRepository fileReadRepository;
    private EmployeRepository employeRepository;

    public UploadFiles(FileReadRepository fileReadRepository, EmployeRepository employeRepository) {
        this.fileReadRepository = fileReadRepository;
        this.employeRepository = employeRepository;
    }
    /**
     * Methode de creation du dossier de telechargement
     *
     */

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Impossilbe de creer le dossier de teleChargement!");
        }
    }

    /**
     * Methode qui renvois le fichier en fonction du nom
     * @param filename
     * @return
     */
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("impossible de lire le fichier!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}


