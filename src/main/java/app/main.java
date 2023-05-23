package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import util.JpaUtil;
import entities.user;
import entities.card;
import dao.userDao;
import dao.cardDao;


public class main {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(main.class);
	
	private static EntityManagerFactory  emf= JpaUtil.getEntityManagerFactory(); 
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		userDao ud = new userDao(em);
		cardDao td = new cardDao(em);
		
		
	// creazione utenti
		
		user p1 = new user("Giovanni", "Storti");
		user p2 = new user("Aldo", "Baglio");
		user p3 = new user("Giovanni", "Rossi");
		user p4 = new user("Giacomo", "Poretti");
		
	// creazione tessere 
		
		card t1 = new card(LocalDate.of(2022, 3, 24), false);
		card t2 = new card(LocalDate.of(2023, 3, 24), true);
		card t3 = new card(LocalDate.of(2021, 5, 24), false);
		card t4 = new card(LocalDate.of(2023, 2, 24), true);
		card t5 = new card(LocalDate.of(2023, 5, 24), true);
		
		
	// salvataggio in DB
		
		ud.saveUser(p1);
		ud.saveUser(p2);
		ud.saveUser(p3);
		ud.saveUser(p4);
		
		td.saveCard(t1);
		td.saveCard(t2);
		td.saveCard(t3);
		td.saveCard(t4);
		td.saveCard(t5);
	}

}
