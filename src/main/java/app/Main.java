package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import dao.cardDao;
import dao.userDao;
import entities.AuthorizedDealer;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.User;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		userDao userDao = new userDao(em);
		cardDao cardDAO = new cardDao(em);
		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
		DealersDAO dealersDAO = new DealersDAO();

		// Creazione degli oggetti User
		User user1 = new User("John", "Doe");
		userDao.saveUser(user1);
		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.createAuthorizedDealer(dealer1);

		Card card1 = new Card(LocalDate.now());
		card1.setUser(user1);
		cardDAO.saveCard(card1);

		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
		travelDAO.save(pass1);
		em.close();
		emf.close();
	}

}
