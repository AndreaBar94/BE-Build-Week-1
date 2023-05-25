package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardDAO {
	
	private final EntityManager em;

	public CardDAO(EntityManager em) {
		this.em = em;
	}
	
	public List<Card> getAllCard() {
		try {
			TypedQuery<Card> query = em.createQuery("SELECT c FROM card c", Card.class);
			List<Card> cards = query.getResultList();
			for(Card c : cards) {
				log.info(c.toString());
			};
			return cards;
		} catch (Exception e) {
			log.error("error: " + e);
			throw e;
		}
		
	};
	
	public void saveCard(Card a) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(a);
			t.commit();
			log.info("Card saved!!");
			
		} catch (Exception e) {
			log.error("error: " + e);
		}
	}
	
	public void deleteCard( UUID id) {
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			Card card = em.find(Card.class, id);
			em.remove(card);
			t.commit();
			log.info("Removed Card");
		} catch (Exception e) {
			log.error("error: " + e);
		}
	};
	
	public Card findById(String id) {
		Card found = em.find(Card.class, UUID.fromString(id));
		return found;
	}
}
