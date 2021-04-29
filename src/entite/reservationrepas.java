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
public class reservationrepas {
    
    private int id; 
    private int idr; 
    private String date; 
    private int nbrpersonnes;
    private String message; 
    private String heure; 
    private String nom; 
    private String email; 
    private String phone; 
    
    public reservationrepas()
    {}

    public reservationrepas(int id, int idr, String date, int nbrpersonnes, String message, String nom, String email, String phone)
    {
        this.id=id; 
        this.idr=idr; 
        this.date=date; 
        this.nbrpersonnes=nbrpersonnes; 
        this.message=message; 
    
        this.nom=nom; 
        this.email=email; 
        this.phone=phone;
    }
    
     public reservationrepas(int idr, String date, int nbrpersonnes, String message,  String nom, String email, String phone)
    {
        
        this.idr=idr; 
        this.date=date; 
        this.nbrpersonnes=nbrpersonnes; 
        this.message=message; 
        this.nom=nom; 
        this.email=email; 
        this.phone=phone;
    }
     
  





   
   
     
  
    public int getId()
            
    {return id; }
    
    public int getIdr()
            
    {return  idr;}
    
    public String getDate()
            
    {
        return date; 
    }
    
    public int getNbrpersonnes ()
    {
        return nbrpersonnes; 
    }
    public String getMessage()
    {
        return message; 
    }
    
    public String getHeure()
    {
    return heure;
    }
    
    public String getNom()
    {
    return nom;
    }
    
    public String getEmail()
    {
    return email;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setId(int id)
    {
        this.id=id; 
    }
    
    public void setIdr(int idr)
    {
        this.idr=idr;
    }
    
    public void setDate(String date)
    { 
    this.date=date; 
    }
    
    public void setnbrpersonnes(int nbrpersonnes)             
    {
    this.nbrpersonnes=nbrpersonnes;
    }
    
    public void setMessage(String message)
    {
    this.message=message; 
    }
    
    public void setHeure(String heure)
    {
    this.heure=heure; 
    }
    
    public void setNom(String nom)
    {
     this.nom=nom;    
    }
    public void setEmail()
    {
    this.email=email;
    }
    public void setPhone()
    {
    this.phone=phone;
    }
    
    @Override
    public String toString()
            
    {
        return "reservationrepas{" + "id=" +id + ", idr=" + idr + ", date=" + date + ", nbrpersonnes=" + nbrpersonnes + ", message=" + message +", heure=" + heure + ", nom=" + nom + ", email" + email + ",phone" +  '}'; 
    }
            
            
            }
