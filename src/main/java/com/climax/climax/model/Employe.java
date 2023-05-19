package com.climax.climax.model;

import com.climax.climax.abstracts.AbstractId;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Employe")
public class Employe extends AbstractId implements Serializable {
    @NotNull
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;
    @NotNull
    @Column(name = "prenom", nullable = false, unique = true)
    private String prenom;
    @NotNull
    @Column(name = "age", nullable = false, unique = true)
    private int age;
    @NotNull
    @Column(name = "profession", nullable = false, unique = true)
    private String profession;
    @NotNull
    @Column(name = "salaire", nullable = false, unique = true)
    private int salaire;

    public Employe() {
    }

    public Employe(String nom, String prenom, int age, String profession, int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.profession = profession;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}
