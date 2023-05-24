package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.Card;
import entities.User;

public class userDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(userDao.class);
	
	private final EntityManager em;

	public userDao(EntityManager em) {
		this.em = em;
	}
	
	public List<User> getAllUser() {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM user u", User.class);
			List<User> users = query.getResultList();
			for(User u : users) {
				logger.info(u.toString());
			};
			return users;
		} catch (Exception e) {
			logger.error("error: " + e);
			throw e;
		}
		
	};
	
	public void saveUser(User a) {
		
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
			User user = em.find(User.class, id);
			em.remove(user);
			t.commit();
			logger.info("User delete");
		} catch (Exception e) {
			logger.error("errore: " + e);
		}
	};
	
	public User findById(String id) {
		User found = em.find(User.class, UUID.fromString(id));
		return found;
	}
	
}
