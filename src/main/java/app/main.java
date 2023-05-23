package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import util.JpaUtil;
import entities.utente;
import entities.tessera;
import dao.utenteDao;
import dao.tesseraDao;


public class main {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(main.class);
	
	private static EntityManagerFactory  emf= JpaUtil.getEntityManagerFactory(); 
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		utenteDao ud = new utenteDao(em);
		tesseraDao td = new tesseraDao(em);
		
		
	// creazione utenti
		
		utente p1 = new utente("Giovanni", "Storti");
		utente p2 = new utente("Aldo", "Baglio");
		utente p3 = new utente("Giovanni", "Rossi");
		utente p4 = new utente("Giacomo", "Poretti");
		
	// creazione tessere 
		
		tessera t1 = new tessera(LocalDate.of(2022, 3, 24), false);
		tessera t2 = new tessera(LocalDate.of(2023, 3, 24), true);
		tessera t3 = new tessera(LocalDate.of(2021, 5, 24), false);
		tessera t4 = new tessera(LocalDate.of(2023, 2, 24), true);
		tessera t5 = new tessera(LocalDate.of(2023, 5, 24), true);
		
		
	// salvataggio in DB
		
		ud.saveUtente(p1);
		ud.saveUtente(p2);
		ud.saveUtente(p3);
		ud.saveUtente(p4);
		
		td.saveTessera(t1);
		td.saveTessera(t2);
		td.saveTessera(t3);
		td.saveTessera(t4);
		td.saveTessera(t5);
	}

}
