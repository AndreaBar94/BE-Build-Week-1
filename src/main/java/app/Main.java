package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.VehicleDAO;
import util.JpaUtil;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

//		UserDAO userDAO = new UserDAO(em);
//		CardDAO cardDAO = new CardDAO(em);
//		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
//		DealersDAO dealersDAO = new DealersDAO(em);
		VehicleDAO vDAO = new VehicleDAO(em);
//		RouteDAO rDAO = new RouteDAO(em);
//
//		User user1 = new User("John", "Doe");
//		userDAO.saveUser(user1);
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
//		Vehicle bus1 = new Vehicle(50, Vehicle.Type.BUS);
//		vDAO.saveVehicle(bus1);
//		Vehicle bus2 = new Vehicle(50, Vehicle.Type.BUS);
//		vDAO.saveVehicle(bus2);
//		Vehicle bus3 = new Vehicle(60, Vehicle.Type.BUS);
//		vDAO.saveVehicle(bus3);
//		Vehicle bus4 = new Vehicle(70, Vehicle.Type.BUS);
//		vDAO.saveVehicle(bus4);
//		Vehicle tram1 = new Vehicle(150, Vehicle.Type.TRAM);
//		vDAO.saveVehicle(tram1);
//		Vehicle tram2 = new Vehicle(120, Vehicle.Type.TRAM);
//		vDAO.saveVehicle(tram2);
//		Vehicle tram3 = new Vehicle(180, Vehicle.Type.TRAM);
//		vDAO.saveVehicle(tram3);
//
//		Route r1 = new Route("Roma", "Milano", 4, 4, Duration.ofHours(1));
//		r1.setVehicle(bus1);
//		rDAO.saveRoute(r1);
//		Route r2 = new Route("Roma", "Milano", 4, 1, Duration.ofHours(4));
//		r2.setVehicle(bus2);
//		rDAO.saveRoute(r2);
//		Route r3 = new Route("Roma", "Milano", 4, 2, Duration.ofHours(2));
//		r3.setVehicle(tram1);
//		rDAO.saveRoute(r3);
//		Route r4 = new Route("Bari", "Como", 10, 2, Duration.ofHours(5));
//		r4.setVehicle(tram2);
//		rDAO.saveRoute(r4);
//		Route r5 = new Route("Bari", "Como", 10, 1, Duration.ofHours(10));
//		r5.setVehicle(bus3);
//		rDAO.saveRoute(r5);
//		Route r6 = new Route("Firenze", "Catania", 8, 2, Duration.ofHours(4));
//		r6.setVehicle(bus4);
//		rDAO.saveRoute(r6);
//		Route r7 = new Route("Firenze", "Catania", 8, 4, Duration.ofHours(2));
//		r7.setVehicle(tram3);
//		rDAO.saveRoute(r7);
//
//		pass1.setVehicle(tram1);
//		t1.setVehicle(bus1);
//		t2.setVehicle(bus1);
//		t3.setVehicle(bus2);
//
//		vDAO.validateTicket(t1.getId().toString(), bus1.getId().toString());
//		vDAO.validateTicket(t2.getId().toString(), bus1.getId().toString());
//		vDAO.validateTicket(t3.getId().toString(), bus1.getId().toString());
//		vDAO.validateTicket(pass1.getId().toString(), tram1.getId().toString());

//		vDAO.docPerVehicleAndDate(vDAO.getVehicleById(UUID.fromString("e4ea7b87-b7df-4376-81a5-d8a95dca1704")),
//				LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
//		travelDAO.checkValidity(pass1.getId().toString(), null);
//
//		vDAO.endMaintenance(UUID.fromString("32a217c5-a9a4-46f7-bc81-45fcfbdffc8a"));
		// apertura scanner per interazione con utente
//		Scanner scanner = new Scanner(System.in);
//
//		int input = -1;
//
//		while (input != 0) {
//			System.out.println("Premi 1 per visualizzare tutti gli utenti");
//			System.out.println("Premi 2 per visualizzare tutte le tessere");
//			System.out.println("Premi 3 per visualizzare tutti i titoli di viaggio");
//			System.out.println("Premi 4 per visualizzare tutti i mezzi in servizio");
//			System.out.println("Premi 5 per visualizzare tutti i mezzi in manutenzione");
//			System.out.println("Premi 6 per visualizzare tutti i rivenditori");
//			System.out.println("Premi 7 per visualizzare tutte le tratte");
//			System.out.println("Premi 8 per visualizzare tutte le corse");
//			System.out.println("Premi 9 per ---to be continued---");
//
//			input = scanner.nextInt();
//			switch (input) {
//			case 1:
//				userDAO.getAllUser();
//				break;
//
//			case 2:
//				cardDAO.getAllCard();
//				break;
//
//			case 3:
//				travelDAO.getAllTd();
//				break;
//
//			case 4:
//				// vDAO.getAllBuses(); aspettiamo simone per la modifica del DAO
//				break;
//
//			case 5:
//				// vDAO.getAllBuses(); aspettiamo simone per la modifica del DAO
//				break;
//
//			case 6:
//				dealersDAO.getAllAuthorizedDealers();
//				break;
//
//			case 7:
//				rDAO.getAllRoutes();
//				break;
//
//			case 8:
//				// tDAO.getAllTravels();
//				break;
//
//			case 9:
//
//				break;
//
//			case 0:
//
//				break;
//
//			default:
//				System.out.println("Devi inserire un numero da 0 a 9.");
//			}
//			;
//		}
//
//		// chiusura scanner
//		scanner.close();
		vDAO.findVehicleWithHighestTicketsValidated();
		em.close();
		emf.close();
	}

}
