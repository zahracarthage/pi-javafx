/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;



/**
 *
 * @author 21628
 */
public class Event {
    
   private int id;
    private String nom;
    private String date;
    private int capacite;
    private String description;
    private String adresse;

    public Event(int id, String nom, String date, int capacite, String description, String adresse) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
        this.adresse = adresse;
    }

    public Event(String nom, String date, int capacite, String description, String adresse) {
        this.nom = nom;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}
