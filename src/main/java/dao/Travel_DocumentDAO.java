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
		t.begin();
		em.persist(a);
		t.commit();
		log.info("Travel Document saved!");
		Travel_Document.incrementDocumentsIssued(a.getPuntoEmissione());
	}
	
	public Travel_Document findByUUID(String id) {
		Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
		return found;
	}
	
	public void delete(String id) {
		Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
		if (found != null) {
			em.remove(found);
			System.out.println("Travel Document with id " + id + " deleted!");
		}
	}
	
	public void getTotalDocumentsIssued() {
		log.info("The total number of travel documents sold is: " + Travel_Document.getTotalDocumentsIssued());
	}
	
	
	public void printDocumentsIssuedBySeller() {
	    Map<UUID, Integer> documentsIssuedBySeller = Travel_Document.getDocumentsIssuedBySeller();
	    for (Map.Entry<UUID, Integer> entry : documentsIssuedBySeller.entrySet()) {
	        UUID sellerId = entry.getKey();
	        int numDocumentsIssued = entry.getValue();
	        log.info("Seller ID: " + sellerId + ", Number of Documents Issued: " + numDocumentsIssued);
	    }
	}
}
