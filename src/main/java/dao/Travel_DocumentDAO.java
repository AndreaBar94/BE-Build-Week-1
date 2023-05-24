package dao;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AuthorizedDealer;
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
		} catch (Exception e) {
			if (t.isActive()) {
                t.rollback();
            }
            log.error("Errore durante il salvataggio del veicolo.", e);
            throw e;
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
	
}
