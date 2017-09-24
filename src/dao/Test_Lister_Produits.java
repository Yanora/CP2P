package dao;

 
import java.util.List;

import bean.produit; 

 
public class Test_Lister_Produits { 
 
   
  public static void main(String [] args) { 
     
    DAO dao = new DAO(); 
    dao.ouvrir(); 
     
    List<produit> produits = dao.listerProduits(); 
     
    System.out.println(); 
    System.out.println("nb Produits = "+produits.size()); 
     
    for (produit prod : produits) { 
      System.out.println("produit "+prod.getId()+" nom = "+prod.getNom()+", prix = "+ prod.getPrix()+"€, nombre vendu = "+ prod.getNb_Sold());
      
      if(prod.getNom().equals("Cafe")){
    	  dao.updateProduct(prod.getId().toString(), 4, true);
      }
    }
     
    
   produits = dao.listerProduits(); 
    
   System.out.println(); 
   System.out.println("nb Produits = "+produits.size()); 
    
   for (produit prod : produits) { 
     System.out.println("produit "+prod.getId()+" nom = "+prod.getNom()+", prix = "+ prod.getPrix()+"€, nombre vendu = "+ prod.getNb_Sold());
   }
    
    dao.fermer(); 
  } 
   
}