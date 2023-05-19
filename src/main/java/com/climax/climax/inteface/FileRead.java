package com.climax.climax.inteface;

import com.climax.climax.model.Employe;

import java.util.List;

public interface FileRead {
    //liste des employés
     List<Employe> readFile(String filePath);

}
