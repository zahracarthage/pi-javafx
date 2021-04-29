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
public class article {
    public static String pathfile; 
    public static String filename="";
    
    private int id; 
    private String nom; 
    private String description; 
    private String categorie; 
    private int prix; 
    private String image;
    
    
    public article()
    {
        
    }
    public article( int id, String nom, String description, String categorie, int prix, String image)
    {
        this.id=id; 
        this.nom=nom; 
        this.description=description; 
        this.categorie=categorie; 
        this.prix=prix; 
        this.image=image; 
    }
public article(String nom, String description, String categorie, int prix, String image)
{
    this.nom=nom; 
    this.description=description;
    this.categorie=categorie; 
    this.prix=prix; 
    this.image=image; 
}
    public article( int id, String nom, String description, String categorie, int prix)
    {
        this.id=id; 
        this.nom=nom; 
        this.description=description; 
        this.categorie=categorie; 
        this.prix=prix; 
    }
       public int getId()
     {return id;}
   
   public String getNom()
   {return nom; }
   
   public  String getDescription()
   {return description;}
   
   public double getPrix()
   { return prix; }
   
   public String getCategorie()
   { return categorie; }
      public String getImg()
   { return image;}
  public void setId(int id) {
        this.id = id;
    }

   
   public void setNom(String nom)
   {this.nom=nom; }
   
   public void setDescription(String description)
   {this.description=description;}
   
   public void setPrix(int prix)
   {this.prix=prix;}
   
   public void setCategorie(String category)
   {this.categorie=categorie ;}
   

   
   public void setImg(String image)
   {this.image=image;}
   
   
  @Override 
  public String toString()
  {
      return "article{" + "id=" +id + ", nom=" + nom + ", description=" + description +  "categorie=" + categorie +", prix=" + prix + ",image=" + image + '}'; 
  }
   


    
}
