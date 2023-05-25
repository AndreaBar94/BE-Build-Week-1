package app;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.CardDAO;
import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import entities.AuthorizedDealer;
import entities.Bus;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.Ticket;
import entities.User;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		UserDAO userDao = new UserDAO(em);
		CardDAO cardDAO = new CardDAO(em);
		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
		DealersDAO dealersDAO = new DealersDAO(em);
		VehicleDAO vDAO = new VehicleDAO(em);

		// Creazione degli oggetti User

		User user1 = new User("John", "Doe");
		userDao.saveUser(user1);

		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.saveAuthorizedDealer(dealer1);

		Card card1 = new Card(LocalDate.of(2023, 3, 10));
		card1.setUser(user1);
		card1.toUpCredit(210);
		cardDAO.saveCard(card1);

		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
		travelDAO.save(pass1);

		Ticket t1 = new Ticket(LocalDate.now(), dealer1, false, user1);
		Ticket t2 = new Ticket(LocalDate.now(), dealer1, false, user1);
		Ticket t3 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user1);
		travelDAO.save(t1);
		travelDAO.save(t2);
		travelDAO.save(t3);

		Bus b1 = new Bus(10);
		vDAO.saveVehicle(b1);

		t1.setVehicle(b1);
		t2.setVehicle(b1);
		t3.setVehicle(b1);

		vDAO.validateTicket(t1.getId().toString(), b1.getId().toString());
		vDAO.validateTicket(t2.getId().toString(), b1.getId().toString());
		vDAO.validateTicket(t3.getId().toString(), b1.getId().toString());
		vDAO.saveVehicle(b1);
		vDAO.docPerVehicleAndDate(vDAO.getVehicleById(UUID.fromString("e4ea7b87-b7df-4376-81a5-d8a95dca1704")),
				LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));

		em.close();
		emf.close();
	}

}
