package com.climax.climax.readFiles;

import com.climax.climax.model.Employe;
import com.climax.climax.inteface.FileRead;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlFileRead implements FileRead {


    @Override
    public List<Employe> readFile(String filePath) {
        List<Employe> employes = new ArrayList<>();
        return employes;
    }
}
