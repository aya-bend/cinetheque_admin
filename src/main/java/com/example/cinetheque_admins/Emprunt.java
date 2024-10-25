package com.example.cinetheque_admins;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CD cd;

    private String utilisateur;  // Identifiant de l'utilisateur
    private Date dateEmprunt;
    private boolean retourne;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public CD getCd() {
        return cd;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public boolean isRetourne() {
        return retourne;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

}
