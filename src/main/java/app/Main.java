package app;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import dao.VehicleDAO;
import dao.cardDao;
import dao.userDao;
import entities.AuthorizedDealer;
import entities.Bus;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.Ticket;
import entities.Tram;
import entities.Travel_Document;
import entities.User;
import entities.Vehicle;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		userDao userDao = new userDao(em);
		cardDao cardDAO = new cardDao(em);
		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
		DealersDAO dealersDAO = new DealersDAO(em);
		VehicleDAO vehicleDAO = new VehicleDAO(em);
		
		// Creazione degli oggetti User
		User user1 = new User("John", "Doe");
		userDao.saveUser(user1);
		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.saveAuthorizedDealer(dealer1);
//
//		Card card1 = new Card(LocalDate.now());
//		card1.setUser(user1);
//		cardDAO.saveCard(card1);
//
//		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
//				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
//		travelDAO.save(pass1);
		Travel_Document ticket3 = new Ticket(LocalDate.now(), dealer1, false, user1);
        travelDAO.save(ticket3);
        
       
//		Vehicle vehicle1 = new Bus(50);
		vehicleDAO.validateTicket("cbfd4da2-8d43-47df-9ce6-fcd0dbf2fbb7", "9e1fc09b-ad0f-407f-85bd-5dad38db230e");
//        vehicleDAO.saveVehicle(vehicle1);
//        
//        Vehicle vehicle2 = new Tram(200);
//        vehicleDAO.saveVehicle(vehicle2);
//        
        
        
        
		em.close();
		emf.close();
	}

}
