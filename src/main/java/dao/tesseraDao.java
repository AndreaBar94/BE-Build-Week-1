package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.tessera;

public class tesseraDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(utenteDao.class);
	
	private final EntityManager em;

	public tesseraDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void saveTessera(tessera a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		logger.info("Utente salvato!!");
	}
	
	public tessera findById(String id) {
		tessera found = em.find(tessera.class, UUID.fromString(id));
		return found;
	}
}
