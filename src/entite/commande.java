/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author zcart
 */
public class commande {
  
    private int id; 
    private String date; 
    private int quantite; 
    private int prix_totale;
    
    
    public commande()
    {
        
    }
    public commande( int id, String date, int quantite, int prix_totale)
    {
        this.id=id; 
        this.date=date; 
        this.quantite=quantite; 
        this.prix_totale=prix_totale;
    }
public commande(String date, int quantite, int prix_totale)
{
            this.date=date; 
        this.quantite=quantite; 
        this.prix_totale=prix_totale;

}
    
       public int getId()
     {return id;}
   
   public String getDate()
   {return date; }
   
   public  int getQuantite()
   {return quantite ;}
   
   public int getPrixT()
   { return prix_totale; }
   
  
  public void setId(int id) {
        this.id = id;
    }

   
   public void setDate(String date)
   {this.date=date; }
   
   public void setQuantite (int quantite)
   {this.quantite=quantite;}
   
   public void setPrixT(int prix)
   {this.prix_totale=prix_totale;}
   
   

   
   
   
  @Override 
  public String toString()
  {
      return "commande{" + "id=" +id + ", date=" + date + ", quantite=" + quantite  +", prix_totale=" + prix_totale  + '}'; 
  }
}
