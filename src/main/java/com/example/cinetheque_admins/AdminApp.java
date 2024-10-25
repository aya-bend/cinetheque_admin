package com.example.cinetheque_admins;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.util.Properties;

public class AdminApp {

    // Interface EJB AdminService
    private AdminService adminService;

    public AdminApp() {
        // Initialiser la connexion avec le service EJB
        try {
            adminService = lookupAdminService();
        } catch (NamingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion au serveur EJB");
            System.exit(1);
        }

        // Créer une fenêtre Swing pour les opérations d'administration
        JFrame frame = new JFrame("Administration des CDs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Ajouter un formulaire pour gérer les CDs
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblTitre = new JLabel("Titre du CD:");
        JTextField txtTitre = new JTextField();

        JButton btnAjouter = new JButton("Ajouter CD");
        JButton btnSupprimer = new JButton("Supprimer CD");

        panel.add(lblTitre);
        panel.add(txtTitre);
        panel.add(btnAjouter);
        panel.add(btnSupprimer);

        // Bouton pour ajouter un CD
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = txtTitre.getText();
                if (!titre.isEmpty()) {
                    adminService.ajouterCD(titre);
                    JOptionPane.showMessageDialog(frame, "CD ajouté avec succès !");
                } else {
                    JOptionPane.showMessageDialog(frame, "Le titre ne peut pas être vide !");
                }
            }
        });

        // Bouton pour supprimer un CD (demander un ID pour simplifier)
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = txtTitre.getText();
                if (!titre.isEmpty()) {
                    adminService.supprimerCD(Long.parseLong(titre)); // Ici on supprime par ID pour l'exemple
                    JOptionPane.showMessageDialog(frame, "CD supprimé avec succès !");
                } else {
                    JOptionPane.showMessageDialog(frame, "L'ID ne peut pas être vide !");
                }
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Méthode pour rechercher le service EJB via JNDI
    private AdminService lookupAdminService() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); // Adapter à votre URL de serveur

        Context context = new InitialContext(props);
        return (AdminService) context.lookup("ejb:/cinetheque_serveur-1.0-SNAPSHOT/AdminServiceBean!com.example.cinetheque_serveur.beans.admin.AdminService?stateful");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminApp();
            }
        });
    }
}