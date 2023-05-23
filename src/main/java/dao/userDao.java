package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import entities.user;

public class userDao {
	private static Logger logger = (Logger) LoggerFactory.getLogger(userDao.class);
	
	private final EntityManager em;

	public userDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void saveUser(user a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		logger.info("Saved user!!");
	}
	
	public user findById(String id) {
		user found = em.find(user.class, UUID.fromString(id));
		return found;
	}
}
