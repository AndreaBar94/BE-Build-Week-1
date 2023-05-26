package app;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.CardDAO;
import dao.DealersDAO;
import dao.RouteDAO;
import dao.Travel_DocumentDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import entities.AuthorizedDealer;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.Route;
import entities.Ticket;
import entities.User;
import entities.Vehicle;
import entities.VendingMachine;
import lombok.extern.slf4j.Slf4j;
import util.JpaUtil;

@Slf4j
public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		UserDAO userDAO = new UserDAO(em);
		CardDAO cardDAO = new CardDAO(em);
		Travel_DocumentDAO travelDAO = new Travel_DocumentDAO(em);
		DealersDAO dealersDAO = new DealersDAO(em);
		VehicleDAO vDAO = new VehicleDAO(em);
		RouteDAO rDAO = new RouteDAO(em);
		
		//date pronte per controlli metodi
		LocalDate data1 = LocalDate.now().minusDays(2);
		LocalDate data2 = LocalDate.now();
		
		//CREAZIONE UTENTI
		User user1 = new User("John", "Smith");
		userDAO.saveUser(user1);

		User user2 = new User("Emma", "Johnson");
		userDAO.saveUser(user2);

		User user3 = new User("Michael", "Williams");
		userDAO.saveUser(user3);

		User user4 = new User("Olivia", "Jones");
		userDAO.saveUser(user4);

		User user5 = new User("William", "Brown");
		userDAO.saveUser(user5);

		User user6 = new User("Sophia", "Davis");
		userDAO.saveUser(user6);

		User user7 = new User("James", "Miller");
		userDAO.saveUser(user7);

		User user8 = new User("Isabella", "Wilson");
		userDAO.saveUser(user8);

		User user9 = new User("Benjamin", "Taylor");
		userDAO.saveUser(user9);

		User user10 = new User("Charlotte", "Anderson");
		userDAO.saveUser(user10);
		
		//CREAZIONE TESSERE UTENTE
		Card card1 = new Card(LocalDate.of(2023, 3, 10));
		card1.setUser(user1);
		card1.toUpCredit(210);
		cardDAO.saveCard(card1);

		Card card2 = new Card(LocalDate.of(2023, 3, 11));
		card2.setUser(user1);
		card2.toUpCredit(210);
		cardDAO.saveCard(card2);

		Card card3 = new Card(LocalDate.of(2023, 3, 12));
		card3.setUser(user2);
		card3.toUpCredit(210);
		cardDAO.saveCard(card3);

		Card card4 = new Card(LocalDate.of(2023, 3, 13));
		card4.setUser(user2);
		card4.toUpCredit(210);
		cardDAO.saveCard(card4);

		Card card5 = new Card(LocalDate.of(2023, 3, 14));
		card5.setUser(user3);
		card5.toUpCredit(210);
		cardDAO.saveCard(card5);
		
		//CREAZIONE RIVENDITORI
		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.saveAuthorizedDealer(dealer1);

		AuthorizedDealer dealer2 = new AuthorizedDealer();
		dealersDAO.saveAuthorizedDealer(dealer2);
		
		VendingMachine dealer3 = new VendingMachine(false);
		dealersDAO.saveAuthorizedDealer(dealer3);
		
		VendingMachine dealer4 = new VendingMachine(true);
		dealersDAO.saveAuthorizedDealer(dealer4);
		
		//CREAZIONE BIGLIETTI
		Ticket ticket1 = new Ticket(LocalDate.now(), dealer1, false, user1);
		travelDAO.save(ticket1);

		Ticket ticket2 = new Ticket(LocalDate.now(), dealer1, false, user2);
		travelDAO.save(ticket2);

		Ticket ticket3 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user3);
		travelDAO.save(ticket3);

		Ticket ticket4 = new Ticket(LocalDate.now(), dealer2, false, user4);
		travelDAO.save(ticket4);

		Ticket ticket5 = new Ticket(LocalDate.now(), dealer2, false, user5);
		travelDAO.save(ticket5);

		Ticket ticket6 = new Ticket(LocalDate.now(), dealer3, false, user6);
		travelDAO.save(ticket6);

		Ticket ticket7 = new Ticket(LocalDate.now(), dealer2, false, user7);
		travelDAO.save(ticket7);

		Ticket ticket8 = new Ticket(LocalDate.now(), dealer1, false, user8);
		travelDAO.save(ticket8);

		Ticket ticket9 = new Ticket(LocalDate.now().minusYears(1), dealer2, false, user9);
		travelDAO.save(ticket9);

		Ticket ticket10 = new Ticket(LocalDate.now(), dealer3, false, user10);
		travelDAO.save(ticket10);
		
		Ticket ticket11 = new Ticket(LocalDate.now(), dealer2, false, user1);
		travelDAO.save(ticket11);

		Ticket ticket12 = new Ticket(LocalDate.now(), dealer3, false, user2);
		travelDAO.save(ticket12);

		Ticket ticket13 = new Ticket(LocalDate.now(), dealer2, false, user3);
		travelDAO.save(ticket13);

		Ticket ticket14 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user4);
		travelDAO.save(ticket14);

		Ticket ticket15 = new Ticket(LocalDate.now(), dealer1, false, user5);
		travelDAO.save(ticket15);

		Ticket ticket16 = new Ticket(LocalDate.now(), dealer2, false, user6);
		travelDAO.save(ticket16);

		Ticket ticket17 = new Ticket(LocalDate.now(), dealer2, false, user7);
		travelDAO.save(ticket17);

		Ticket ticket18 = new Ticket(LocalDate.now(), dealer1, false, user8);
		travelDAO.save(ticket18);

		Ticket ticket19 = new Ticket(LocalDate.now(), dealer2, false, user9);
		travelDAO.save(ticket19);

		Ticket ticket20 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user10);
		travelDAO.save(ticket20);
		
		//CREAZIONE VEICOLI
		Vehicle tram1 = new Vehicle(150, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram1);

		Vehicle tram2 = new Vehicle(120, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram2);

		Vehicle tram3 = new Vehicle(180, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram3);

		Vehicle tram4 = new Vehicle(160, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram4);

		Vehicle tram5 = new Vehicle(140, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram5);
		
		Vehicle bus1 = new Vehicle(50, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus1);

		Vehicle bus2 = new Vehicle(50, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus2);

		Vehicle bus3 = new Vehicle(60, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus3);

		Vehicle bus4 = new Vehicle(70, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus4);

		Vehicle bus5 = new Vehicle(55, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus5);

		Vehicle bus6 = new Vehicle(60, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus6);

		Vehicle bus7 = new Vehicle(65, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus7);

		Vehicle bus8 = new Vehicle(70, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus8);

		Vehicle bus9 = new Vehicle(55, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus9);

		Vehicle bus10 = new Vehicle(60, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus10);
		
		//CREAZIONE ABBONAMENTI
		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1, Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
		travelDAO.save(pass1);

		Public_Transport_Pass pass2 = new Public_Transport_Pass(LocalDate.now(), dealer2, Public_Transport_Pass.SubType.SETTIMANALE, true, card2);
		travelDAO.save(pass2);

		Public_Transport_Pass pass3 = new Public_Transport_Pass(LocalDate.now().minusWeeks(2), dealer1, Public_Transport_Pass.SubType.SETTIMANALE, false, card3);
		travelDAO.save(pass3);

		Public_Transport_Pass pass4 = new Public_Transport_Pass(LocalDate.now(), dealer2, Public_Transport_Pass.SubType.MENSILE, true, card4);
		travelDAO.save(pass4);

		Public_Transport_Pass pass5 = new Public_Transport_Pass(LocalDate.now().minusWeeks(5), dealer1, Public_Transport_Pass.SubType.MENSILE, false, card5);
		travelDAO.save(pass5);

		//CREAZIONE TRATTE
		Route route1 = new Route("Roma", "Milano", 4, 4, Duration.ofHours(1));
		route1.setVehicle(bus1);
		rDAO.saveRoute(route1);

		Route route2 = new Route("Roma", "Milano", 4, 1, Duration.ofHours(4));
		route2.setVehicle(bus2);
		rDAO.saveRoute(route2);

		Route route3 = new Route("Roma", "Milano", 4, 2, Duration.ofHours(2));
		route3.setVehicle(tram1);
		rDAO.saveRoute(route3);

		Route route4 = new Route("Bari", "Como", 10, 2, Duration.ofHours(5));
		route4.setVehicle(tram2);
		rDAO.saveRoute(route4);

		Route route5 = new Route("Bari", "Como", 10, 1, Duration.ofHours(10));
		route5.setVehicle(bus3);
		rDAO.saveRoute(route5);

		Route route6 = new Route("Firenze", "Catania", 8, 2, Duration.ofHours(4));
		route6.setVehicle(bus4);
		rDAO.saveRoute(route6);

		Route route7 = new Route("Firenze", "Catania", 8, 4, Duration.ofHours(2));
		route7.setVehicle(tram3);
		rDAO.saveRoute(route7);

		Route route8 = new Route("Napoli", "Palermo", 6, 3, Duration.ofHours(3));
		route8.setVehicle(bus5);
		rDAO.saveRoute(route8);

		Route route9 = new Route("Napoli", "Palermo", 6, 1, Duration.ofHours(5));
		route9.setVehicle(bus6);
		rDAO.saveRoute(route9);

		Route route10 = new Route("Torino", "Venezia", 7, 2, Duration.ofHours(3));
		route10.setVehicle(bus7);
		rDAO.saveRoute(route10);

		Route route11 = new Route("Torino", "Venezia", 7, 3, Duration.ofHours(2));
		route11.setVehicle(tram4);
		rDAO.saveRoute(route11);

		Route route12 = new Route("Bologna", "Genova", 5, 2, Duration.ofHours(2));
		route12.setVehicle(tram5);
		rDAO.saveRoute(route12);
		
		Route route13 = new Route("Milano", "Bologna", 3, 2, Duration.ofHours(1));
		route13.setVehicle(bus8);
		rDAO.saveRoute(route13);

		Route route14 = new Route("Milano", "Bologna", 3, 1, Duration.ofHours(2));
		route14.setVehicle(bus10);
		rDAO.saveRoute(route14);

		Route route15 = new Route("Roma", "Napoli", 5, 3, Duration.ofHours(1));
		route15.setVehicle(bus9);
		rDAO.saveRoute(route15);

		//SETTO AMBIENTE DI LAVORO CON QUALCHE INTERAZIONE TRA ENTITY
		ticket1.setVehicle(bus1);
		travelDAO.update(ticket1);

		ticket2.setVehicle(bus1);
		travelDAO.update(ticket2);

		ticket3.setVehicle(bus2);
		travelDAO.update(ticket3);

		ticket4.setVehicle(bus3);
		travelDAO.update(ticket4);

		ticket5.setVehicle(bus4);
		travelDAO.update(ticket5);

		ticket6.setVehicle(bus5);
		travelDAO.update(ticket6);

		ticket7.setVehicle(bus6);
		travelDAO.update(ticket7);

		ticket8.setVehicle(bus7);
		travelDAO.update(ticket8);

		ticket9.setVehicle(bus8);
		travelDAO.update(ticket9);

		ticket10.setVehicle(bus9);
		travelDAO.update(ticket10);

		ticket11.setVehicle(bus10);
		travelDAO.update(ticket11);

		ticket12.setVehicle(bus1);
		travelDAO.update(ticket12);

		ticket13.setVehicle(bus2);
		travelDAO.update(ticket13);

		ticket14.setVehicle(bus3);
		travelDAO.update(ticket14);

		ticket15.setVehicle(bus4);
		travelDAO.update(ticket15);

		ticket16.setVehicle(bus5);
		travelDAO.update(ticket16);

		ticket17.setVehicle(bus6);
		travelDAO.update(ticket17);

		ticket18.setVehicle(bus7);
		travelDAO.update(ticket18);

		ticket19.setVehicle(bus8);
		travelDAO.update(ticket19);

		ticket20.setVehicle(bus9);
		travelDAO.update(ticket20);

		pass1.setVehicle(tram1);
		travelDAO.update(pass1);

		pass2.setVehicle(tram2);
		travelDAO.update(pass2);

		pass3.setVehicle(tram3);
		travelDAO.update(pass3);

		pass4.setVehicle(tram4);
		travelDAO.update(pass4);

		pass5.setVehicle(tram5);
		travelDAO.update(pass5);

		// TIMBRO QUALCHE BIGLIETTO
		vDAO.validateTicket(ticket1.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(ticket2.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(ticket3.getId().toString(), bus2.getId().toString());
		vDAO.validateTicket(ticket4.getId().toString(), bus3.getId().toString());
		vDAO.validateTicket(ticket5.getId().toString(), bus4.getId().toString());
		vDAO.validateTicket(ticket6.getId().toString(), bus5.getId().toString());
		vDAO.validateTicket(ticket7.getId().toString(), bus6.getId().toString());
		vDAO.validateTicket(ticket8.getId().toString(), bus7.getId().toString());
		vDAO.validateTicket(ticket9.getId().toString(), bus8.getId().toString());
		vDAO.validateTicket(ticket10.getId().toString(), bus9.getId().toString());
		vDAO.validateTicket(ticket11.getId().toString(), bus10.getId().toString());
		vDAO.validateTicket(ticket12.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(ticket13.getId().toString(), bus2.getId().toString());
		vDAO.validateTicket(ticket14.getId().toString(), bus3.getId().toString());
		vDAO.validateTicket(ticket15.getId().toString(), bus4.getId().toString());
		vDAO.validateTicket(ticket16.getId().toString(), bus5.getId().toString());
		vDAO.validateTicket(ticket17.getId().toString(), bus6.getId().toString());
		vDAO.validateTicket(ticket18.getId().toString(), bus7.getId().toString());
		vDAO.validateTicket(ticket19.getId().toString(), bus8.getId().toString());
		vDAO.validateTicket(ticket20.getId().toString(), bus9.getId().toString());
		vDAO.validateTicket(pass1.getId().toString(), tram1.getId().toString());
		vDAO.validateTicket(pass2.getId().toString(), tram2.getId().toString());
		vDAO.validateTicket(pass3.getId().toString(), tram3.getId().toString());
		vDAO.validateTicket(pass4.getId().toString(), tram4.getId().toString());
		vDAO.validateTicket(pass5.getId().toString(), tram5.getId().toString());
		
		//MANDO IN MANTENIMENTO QUALCHE MEZZO
		

		
		// SCANNER PER INTERAZIONE CON UTENTE
		Scanner scanner = new Scanner(System.in);

		int input = -1;

		while (input != 0) {
			System.out.println("Premi 1 per visualizzare tutti gli utenti");
			System.out.println("Premi 2 per visualizzare tutte le tessere");
			System.out.println("Premi 3 per visualizzare tutti i titoli di viaggio");
			System.out.println("Premi 4 per visualizzare tutti i mezzi in servizio");
			System.out.println("Premi 5 per visualizzare tutti i mezzi in manutenzione");
			System.out.println("Premi 6 per visualizzare tutti i rivenditori");
			System.out.println("Premi 7 per visualizzare tutte le tratte");
			System.out.println("Premi 8 per visualizzare altre opzioni");
			System.out.println("Premi 9 per una sorpresa!");
			System.out.println("Premi 0 per chiudere!");

			input = scanner.nextInt();
			switch (input) {
			case 1:
				userDAO.getAllUser();
				break;

			case 2:
				cardDAO.getAllCard();
				break;

			case 3:
				travelDAO.getAllTd();
				break;

			case 4:
				 vDAO.getAllVehiclesInService();
				break;

			case 5:
				vDAO.getAllVehiclesUnderMaintenance();
				break;

			case 6:
				dealersDAO.getAllAuthorizedDealers();
				break;

			case 7:
				rDAO.getAllRoutes();
				break;

			case 8:
			    int subInput = -1;
			    while (subInput != 0) {
			        System.out.println("Premi 1 per vedere biglietti e abbonamenti emessi in un dato periodo di tempo in totale e per punto di emissione");
			        System.out.println("Premi 2 per check validità abbonamento in base a numero di tessera utente");
			        System.out.println("Premi 3 per check numero biglietti vidimati su un mezzo per un dato periodo di tempo");
			        System.out.println("Premi 4 per associare in automatico il veicolo più veloce ad ogni tratta");
			        System.out.println("Premi 5 per vedere quale veicolo ha timbrato più biglietti in assoluto");
			        System.out.println("Premi 0 per tornare al menu principale");

			        subInput = scanner.nextInt();
			        switch (subInput) {
			            case 1:
			                travelDAO.docPerDealersAndDate(dealer1, data1, data2);
			                break;

			            case 2:
			                travelDAO.checkValidity(pass1.getId().toString(), card1.getId().toString());
			                break;

			            case 3:
			                vDAO.docPerVehicleAndDate(bus1, data1, data2.plusDays(2));
			                break;

			            case 4:
			                rDAO.findVehicleIdsWithShortestTravelTime();
			                break;

			            case 5:
			                vDAO.findVehicleWithHighestTicketsValidated();
			                break;

			            case 0:
			                break;

			            default:
			                System.out.println("Opzione non valida. Riprova.");
			                break;
			        }
			    }
			    break;

			case 9:
				
				//creazione utente
				System.out.println("Crea un nuovo utente, inserisci i dati come richiesto: ");
				User user = new User();

				System.out.println("Inserisci il nome dell'utente: ");
				String firstName = scanner.next();
				user.setName(firstName);

				System.out.println("Inserisci il cognome dell'utente: ");
				String lastName = scanner.next();
				user.setLastName(lastName);

				userDAO.saveUser(user);
				
				// Acquisto del biglietto
				System.out.println("Acquista un biglietto inserendo la data: ");
				System.out.println("Inserisci la data del biglietto (AAAA-MM-GG): ");
				String ticketDateStr = scanner.next();
				LocalDate ticketDate = LocalDate.parse(ticketDateStr);
				Ticket ticket = new Ticket(ticketDate, dealer1, false, user);
				travelDAO.save(ticket);
				
				// Scelta dell'autobus
				List<Vehicle> buses = vDAO.getAllVehiclesInService();
				System.out.println("Seleziona il mezzo disponibile:");

				int busIndex = 1;
				for (Vehicle bus : buses) {
				    System.out.println(busIndex + ". " + bus.toString());
				    busIndex++;
				}
				
				System.out.print("Inserisci il numero corrispondente all'autobus scelto: ");
				int selectedBusIndex = scanner.nextInt();
				scanner.nextLine();
				
				Vehicle selectedBus = buses.get(selectedBusIndex - 1);
				System.out.println("Sei salito sul " + selectedBus.getType() + ", quando il controllore ti chiede di mostrare il tuo titolo di viaggio...");
				
				if(user.getName().equals("Ajeje") && user.getLastName().equals("Brazorf")) {
					
					ticket.setEndorsed(true);//scherzetto...
				
					vDAO.validateTicket(ticket.getId().toString(), selectedBus.getId().toString());
				}else {
					vDAO.validateTicket(ticket.getId().toString(), selectedBus.getId().toString());
				}
				
				
				break;

			case 0:
				System.out.println("Grazie per averci scelto!");
				break;

			default:
				System.out.println("Devi inserire un numero da 0 a 9.");
			}
			;
		}

		// CHIUSURA SCANNER ED ENTITY MANAGER
		scanner.close();
		em.close();
		emf.close();
	}

}
