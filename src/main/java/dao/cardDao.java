package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.card;

public class cardDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(cardDao.class);
	
	private final EntityManager em;

	public cardDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void saveCard(card a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		logger.info("Card saved!!");
	}
	
	public card findById(String id) {
		card found = em.find(card.class, UUID.fromString(id));
		return found;
	}
}
