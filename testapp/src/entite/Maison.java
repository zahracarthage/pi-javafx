/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;



/**
 *
 * @author Asus
 */
public class Maison {
    
    
    private int id;
 
    private String nom;
    private String adresse;
    private int num;
    private int prix;
    private int note;
    private String image; 
    public static String pathfile; 
    public static String filename="";
    
      public Maison() {
    }

    public Maison(int id, String nom, String adresse, int num, int prix, int note, String image) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.num = num;
        this.prix = prix;
        this.note = note;
        this.image = image;
    }

    public Maison(String nom, String adresse, int num, int prix, int note, String image) {
        this.nom = nom;
        this.adresse = adresse;
        this.num = num;
        this.prix = prix;
        this.note = note;
        this.image = image;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    
}
