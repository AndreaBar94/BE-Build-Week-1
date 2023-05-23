package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.card;

public class cardDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(cardDao.class);
	
	private final EntityManager em;

	public cardDao(EntityManager em) {
		this.em = em;
	}
	
	public List<card> getAllCard() {
		try {
			TypedQuery<card> query = em.createQuery("SELECT c FROM card c", card.class);
			List<card> cards = query.getResultList();
			for(card c : cards) {
				logger.info(c.toString());
			};
			return cards;
		} catch (Exception e) {
			logger.error("error: " + e);
			throw e;
		}
		
	};
	
	public void saveCard(card a) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(a);
			t.commit();
			logger.info("Card saved!!");
			
		} catch (Exception e) {
			logger.error("error: " + e);
		}
	}
	
	public void deleteCard( UUID id) {
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			card card = em.find(card.class, id);
			em.remove(card);
			t.commit();
			logger.info("Removed Card");
		} catch (Exception e) {
			logger.error("error: " + e);
		}
	};
	
	public card findById(String id) {
		card found = em.find(card.class, UUID.fromString(id));
		return found;
	}
}
