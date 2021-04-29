/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;

/**
 *
 * @author Zannou
 */
public class Resactivites {
    private int idresactivites;
    private int nbpersonnes;
    private Date dateres ;
    private int idact;

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }
  

    public Resactivites() {
    }

    public Resactivites(int idresactivites, int nbpersonnes, Date dateres) {
        this.idresactivites = idresactivites;
        this.nbpersonnes = nbpersonnes;
        this.dateres = dateres;
    }

    public Resactivites(int nbpersonnes, Date dateres) {
        this.nbpersonnes = nbpersonnes;
        this.dateres = dateres;
    }

    public int getIdresactivites() {
        return idresactivites;
    }

    public void setIdresactivites(int idresactivites) {
        this.idresactivites = idresactivites;
    }

    public int getNbpersonnes() {
        return nbpersonnes;
    }

    public void setNbpersonnes(int nbpersonnes) {
        this.nbpersonnes = nbpersonnes;
    }

    public Date getDateres() {
        return dateres;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }
    

    @Override
    public String toString() {
        return "Resactivites{" + "idresactivites=" + idresactivites + ", nbpersonnes=" + nbpersonnes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resactivites other = (Resactivites) obj;
        if (this.idresactivites != other.idresactivites) {
            return false;
        }
        if (this.nbpersonnes != other.nbpersonnes) {
            return false;
        }
        return true;
    }
   
    
}
