/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Reservation {
    
    private int id;
    private Date dateentre;
    private Date datesortie;

    public Reservation() {
    }

    public Reservation(int id, Date dateentre, Date datesortie) {
        this.id = id;
        this.dateentre = dateentre;
        this.datesortie = datesortie;
    }

    public Reservation(Date dateentre, Date datesortie) {
        this.dateentre = dateentre;
        this.datesortie = datesortie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateentre() {
        return dateentre;
    }

    public void setDateentre(Date dateentre) {
        this.dateentre = dateentre;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }
}
    
    

    