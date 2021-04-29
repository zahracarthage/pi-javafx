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
public class reclamation {
    
    private int id; 
    private String nom; 
    private String prenom; 
    private String email; 
    private String subject;
    private String message;

    private int etat = 0;
    
    
    public reclamation()
    {
        
    }
    public reclamation( int id, String nom, String prenom, String email, String subject, String message, int etat)
    {
        this.id=id; 
        this.nom=nom; 
        this.prenom=prenom; 
        this.email=email; 
        this.subject=subject; 
        this.message=message; 
        this.etat=etat;
    }

    
      public reclamation( String nom, String prenom, String email, String subject, String message, int etat)
    {
        this.nom=nom; 
        this.prenom=prenom; 
        this.email=email; 
        this.subject=subject; 
        this.message=message; 
        this.etat=etat;
    }
      public reclamation(String nom, String prenom, String email, String subject, String message )
    {
        this.nom=nom; 
        this.prenom=prenom; 
        this.email=email; 
        this.subject=subject; 
        this.message=message; 
    }
    

       public int getId()
     {return id;}
   
   public String getNom()
   {return nom; }
   
   public  String getPrenom()
   {return prenom;}
   
   public String getEmail()
   { return email; }
   
   public String getSubject()
   { return subject; }
      public String getMessage()
   { return message;}
      
          public int getEtat()
   { return etat;}
      
  public void setId(int id) {
        this.id = id;
    }

   
   public void setNom(String nom)
   {this.nom=nom; }
   
   public void setPrenom(String prenom)
   {this.prenom=prenom;}
   
   public void setEmail(String email)
   {this.email=email;}
   
   public void setSubject(String subject)
   {this.subject=subject ;}
   
   
   
   public void setMessage(String message)
   {this.message=message ;}
   

   
   public void setEtat(int etat)
   {this.etat=etat;}
   
   
  @Override 
  public String toString()
  {
      return "article{" + "id=" +id + ", nom=" + nom + ", prenom=" + prenom +  "email=" + email +", subject=" + subject + ",message=" + message + ",etat" + etat + '}'; 
  }
    
}
