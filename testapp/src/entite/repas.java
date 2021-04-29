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
public class repas {
    
    public static String pathfile; 
    public static String filename="";
    
    private int id; 
    private String nom; 
    private String description; 
    private int price; 
    private String category;
    private String adresse; 
    private String img; 

    
    public repas()
    {}
    public repas(int id, String nom, String description, int price, String category, String adresse, String img)
    {
        this.id=id; 
        this.nom=nom; 
        this.description=description; 
        this.price=price; 
        this.category=category; 
        this.adresse=adresse;
        this.img=img; 
    }
     
   public repas(String nom, String description, int price, String category, String adresse, String img)
   {
   this.nom=nom; 
   this.description=description; 
   this.price=price; 
   this.category=category; 
   this.adresse=adresse; 
   this.img=img; 
   }
   
   public repas(int id, String nom, String description, int price, String category, String adresse)
   {
  this.id=id;
     this.nom=nom; 
       this.description=description; 
   this.price=price; 
   this.category=category; 
   this.adresse=adresse; 
   }

    public repas(int id, String nom) {
        
         this.id=id;
     this.nom=nom; 
    }
            
   public int getId()
     {return id;}
   
   public String getNom()
   {return nom; }
   
   public  String getDescription()
   {return description;}
   
   public double getPrice()
   { return price; }
   
   public String getCategory()
   { return category; }
   
   public String getAdresse()
   {return adresse; }
   
   public String getImg()
   { return img;}
   
   public void setId(int id) {
        this.id = id;
    }

   
   public void setNom(String nom)
   {this.nom=nom; }
   
   public void setDescription(String description)
   {this.description=description;}
   
   public void setPrice(int price)
   {this.price=price;}
   
   public void setCategory(String category)
   {this.category=category ;}
   
   public void setAdresse(String adresse)
   {this.adresse=adresse;}
   
   public void setImg(String img)
   {this.img=img;}
   
   
  @Override 
  public String toString()
  {
      return "repas{" + "id=" +id + ", nom=" + nom + ", description=" + description + ", price=" + price + ", category=" + category +", adresse=" + adresse + ", img=" + img + '}'; 
  }
   
}
