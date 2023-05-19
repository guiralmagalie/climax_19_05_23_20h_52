package com.climax.climax.model;

import com.climax.climax.abstracts.AbstractId;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class FileUpload extends AbstractId implements Serializable {
    private String filename;
    private String url;
    private Long size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
