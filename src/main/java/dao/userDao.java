package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.card;
import entities.user;

public class userDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(userDao.class);
	
	private final EntityManager em;

	public userDao(EntityManager em) {
		this.em = em;
	}
	
	public List<user> getAllCard() {
		try {
			TypedQuery<user> query = em.createQuery("SELECT u FROM user u", user.class);
			List<user> users = query.getResultList();
			for(user u : users) {
				logger.info(u.toString());
			};
			return users;
		} catch (Exception e) {
			logger.error("error: " + e);
			throw e;
		}
		
	};
	
	public void saveUser(user a) {
		
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(a);
			t.commit();
			logger.info("Saved user!!");
			
		} catch (Exception e) {
			logger.error("errore: " + e);
		}
	}
	
	
	
	public void deleteUser(UUID id) {
		
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			user user = em.find(user.class, id);
			em.remove(user);
			t.commit();
			logger.info("User delete");
		} catch (Exception e) {
			logger.error("errore: " + e);
		}
	};
	
	public user findById(String id) {
		user found = em.find(user.class, UUID.fromString(id));
		return found;
	}
	
}
