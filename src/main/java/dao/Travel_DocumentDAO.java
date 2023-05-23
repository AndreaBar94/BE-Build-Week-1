package dao;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Travel_Document;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Travel_DocumentDAO {
	
	private final EntityManager em;
	
	public Travel_DocumentDAO (EntityManager em) {
		this.em = em;
	}
	
	public void save(Travel_Document a) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(a);
			t.commit();
			log.info("Travel Document saved!");
			Travel_Document.incrementDocumentsIssued(a.getPuntoEmissione());
		} catch (Exception e) {
			log.info("Saving error: " + e);
		}
		
	}
	
	public Travel_Document findByUUID(String id) {
		Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
		return found;
	}
	
	public void delete(String id) {
		try {
			Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
			if (found != null) {
			em.remove(found);
			System.out.println("Travel Document with id " + id + " deleted!");
		}
		} catch (Exception e) {
			log.info("Delete error: " + e);
		}
		
	}
	
	public void getTotalDocumentsIssued() {
		try {
			int x = Travel_Document.getTotalDocumentsIssued();
			log.info("The total number of travel documents sold is: " + x);
		} catch (Exception e) {
			log.info("No data found" + e);
		}
	}
	
	public void printDocumentsIssuedBySeller() {
		try {
			Map<UUID, Integer> documentsIssuedBySeller = Travel_Document.getDocumentsIssuedBySeller();
	    for (Map.Entry<UUID, Integer> entry : documentsIssuedBySeller.entrySet()) {
	        UUID sellerId = entry.getKey();
	        int numDocumentsIssued = entry.getValue();
	        log.info("Seller ID: " + sellerId + ", Number of Documents Issued: " + numDocumentsIssued);
	    }
		} catch (Exception e) {
			log.info("Error trying fetching the results" + e);
		}
	    
	}
}
