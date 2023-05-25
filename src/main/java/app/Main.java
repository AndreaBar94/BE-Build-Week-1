package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import dao.CardDAO;
import dao.UserDAO;
import entities.AuthorizedDealer;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.User;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		UserDAO userDao = new UserDAO(em);
		CardDAO cardDAO = new CardDAO(em);
		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
		DealersDAO dealersDAO = new DealersDAO();

		// Creazione degli oggetti User
		User user1 = new User("John", "Doe");
		userDao.saveUser(user1);
		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.createAuthorizedDealer(dealer1);

		Card card1 = new Card(LocalDate.of(2023, 3 , 10));
		card1.setUser(user1);
		card1.toUpCredit(210);
		cardDAO.saveCard(card1);

		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
		travelDAO.save(pass1);
		em.close();
		emf.close();
	}

}
