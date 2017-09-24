package dao; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import bean.produit;

public class DAO { 

	EntityManagerFactory emf = null; 
	EntityManager em = null; 

	public void ouvrir() { 
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		try { 
			emf = Persistence.createEntityManagerFactory("PressToPlay"); 
			em = emf.createEntityManager();     
		} 
		catch (Exception e) { 
			System.out.println("Erreur DAO.ouvrir "+e.getMessage()); 
		} 

	} 

	public void fermer() { 
		try { 
			em.close(); 
			emf.close(); 
		} 
		catch (Exception e) { 
			System.out.println("Erreur DAO.fermer "+e.getMessage()); 
		} 
	} 
	
	

	public void enregistrerProduit(produit prod) { 
		em.persist(prod); 

	} 

	public List<produit> listerProduits() { 
		List<produit> lst = em.createQuery("select p from produit p").getResultList(); 
		return lst; 
	}
	
	public produit getProduit(String id){
		List<produit> prod = em.createQuery("select p from produit p where id="+id).getResultList();
		if(prod.isEmpty())
			return null;

		return prod.get(0);
	}


	public void updateProduct(String id, Integer value, boolean add){
		produit prod = getProduit(id);
		if(prod == null){
			new Exception("id Inconnu");
		}

		if(add){
			prod.setNb_Sold(prod.getNb_Sold() + value);
		}else{
			if(prod.getNb_Sold() - value < 0){
				System.out.println("Il y a un con dans l'equipe");        
		        try {
		            Files.write(Paths.get("D:/CaissePress2Play.log"), "Il y a un con dans l'equipe\n".getBytes(), StandardOpenOption.APPEND);
		        }catch (IOException e) {
		            //exception handling left as an exercise for the reader
		        }
		        
			}else{
				prod.setNb_Sold(prod.getNb_Sold() - value);
			}
		}

    	EntityTransaction tx = this.em.getTransaction(); 
    	tx.begin(); 
		enregistrerProduit(prod);
		tx.commit();
	}
}
