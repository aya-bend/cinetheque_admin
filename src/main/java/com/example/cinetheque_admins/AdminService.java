package com.example.cinetheque_admins;

import java.util.List;

public interface AdminService {
    void ajouterCD(String titre);
    void supprimerCD(Long cdId);
    List<Emprunt> voirTousLesEmprunts();
}