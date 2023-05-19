package com.climax.climax.controller;

import com.climax.climax.service.UploadFiles;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/uploadfile")
public class UploadController {
    private final UploadFiles uploadFiles;

    public UploadController(UploadFiles uploadFiles) {
        this.uploadFiles = uploadFiles;
    }
    /**
     * Affiche un fichier
     * @param filename
     * @return
     */
    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFiles(@PathVariable String filename) {
        Resource file = uploadFiles.load(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("file/json"))//là où on change le type de fichier
                .body(file);
    }

}
