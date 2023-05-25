package app;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.VehicleDAO;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

//		UserDAO userDao = new UserDAO(em);
//		CardDAO cardDAO = new CardDAO(em);
//		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
//		DealersDAO dealersDAO = new DealersDAO(em);
		VehicleDAO vDAO = new VehicleDAO(em);
//		RouteDAO rDAO = new RouteDAO(em);
//		TravelDAO tDAO = new TravelDAO(em);
//
//		// Creazione degli oggetti User
//
//		User user1 = new User("John", "Doe");
//		userDao.saveUser(user1);
//
//		AuthorizedDealer dealer1 = new AuthorizedDealer();
//		dealersDAO.saveAuthorizedDealer(dealer1);
//
//		Card card1 = new Card(LocalDate.of(2023, 3, 10));
//		card1.setUser(user1);
//		card1.toUpCredit(210);
//		cardDAO.saveCard(card1);
//
//		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
//				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
//		travelDAO.save(pass1);
//
//		Ticket t1 = new Ticket(LocalDate.now(), dealer1, false, user1);
//		Ticket t2 = new Ticket(LocalDate.now(), dealer1, false, user1);
//		Ticket t3 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user1);
//		travelDAO.save(t1);
//		travelDAO.save(t2);
//		travelDAO.save(t3);
//
//		Route r1 = new Route("Roma", "Milano", 8);
//		rDAO.saveRoute(r1);
//		Route r2 = new Route("Terni", "Firenze", 4);
//		rDAO.saveRoute(r2);
//
//		Bus b1 = new Bus(10);
//		vDAO.saveVehicle(b1);
//		Bus b2 = new Bus(100);
//		vDAO.saveVehicle(b2);
//		Tram tram1 = new Tram(200);
//		vDAO.saveVehicle(tram1);
//		Duration time = Duration.ofHours(12);
//		Duration time2 = Duration.ofHours(24);
//		Duration time3 = Duration.ofHours(6);
//
//		Travel travel1 = new Travel(b1, r1);
//		travel1.setTravelTime(time);
//		travel1.setNumberOfTimes(3);
//		tDAO.saveTravel(travel1);
//
//		Travel travel2 = new Travel(b2, r1);
//		travel2.setTravelTime(time2);
//		travel2.setNumberOfTimes(1);
//		tDAO.saveTravel(travel2);
//
//		Travel travel3 = new Travel(tram1, r2);
//		travel3.setTravelTime(time3);
//		travel3.setNumberOfTimes(2);
//		tDAO.saveTravel(travel3);
//
//		pass1.setVehicle(tram1);
//		t1.setVehicle(b1);
//		t2.setVehicle(b1);
//		t3.setVehicle(b2);
//
//		vDAO.validateTicket(t1.getId().toString(), b1.getId().toString());
//		vDAO.validateTicket(t2.getId().toString(), b1.getId().toString());
//		vDAO.validateTicket(t3.getId().toString(), b1.getId().toString());
//		vDAO.validateTicket(pass1.getId().toString(), tram1.getId().toString());

		// vDAO.docPerVehicleAndDate(vDAO.getVehicleById(UUID.fromString("e4ea7b87-b7df-4376-81a5-d8a95dca1704")),
		// LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
		// travelDAO.checkValidity(pass1.getId().toString(), null);

		vDAO.endMaintenance(UUID.fromString("32a217c5-a9a4-46f7-bc81-45fcfbdffc8a"));

		em.close();
		emf.close();
	}

}
