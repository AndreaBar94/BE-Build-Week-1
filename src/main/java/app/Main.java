package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import entities.AuthorizedDealer;
import entities.Ticket;
import entities.Travel_Document;
import util.JpaUtil;

public class Main {
	
	public static Logger logger = LoggerFactory.getLogger(Main.class);
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		Travel_DocumentDAO tDAO = new Travel_DocumentDAO(em);
		DealersDAO dDAO = new DealersDAO();
		
		AuthorizedDealer d1 = new AuthorizedDealer();
		Ticket t1 = new Ticket(LocalDate.now(), d1, false);
		Ticket t2 = new Ticket(LocalDate.now(), d1, false);
		Ticket t3 = new Ticket(LocalDate.now(), d1, false);
		
		
		dDAO.createAuthorizedDealer(d1);
		

		
		
		
		tDAO.save(t1);
		tDAO.save(t2);
		tDAO.save(t3);
		
		//TRANSACTION COMMIT


		//CLOSING EM
		em.close();
		emf.close();
	}

}
