package com.climax.climax.inteface;

import com.climax.climax.model.Employe;
import com.climax.climax.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileReadRepository extends JpaRepository<FileUpload, UUID> {

    FileUpload findFileByFilename(String filename);
}
