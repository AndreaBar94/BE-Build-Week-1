package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.utente;

public class utenteDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(utenteDao.class);
	
	private final EntityManager em;

	public utenteDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void saveUtente(utente a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		logger.info("Utente salvato!!");
	}
	
	public utente findById(String id) {
		utente found = em.find(utente.class, UUID.fromString(id));
		return found;
	}
}
