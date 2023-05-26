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
		
		User user1 = new User("John", "Doe");
		userDAO.saveUser(user1);

		AuthorizedDealer dealer1 = new AuthorizedDealer();
		dealersDAO.saveAuthorizedDealer(dealer1);

		Card card1 = new Card(LocalDate.of(2023, 3, 10));
		card1.setUser(user1);
		card1.toUpCredit(210);
		cardDAO.saveCard(card1);

		Public_Transport_Pass pass1 = new Public_Transport_Pass(LocalDate.now(), dealer1,
				Public_Transport_Pass.SubType.SETTIMANALE, true, card1);
		travelDAO.save(pass1);

		Ticket t1 = new Ticket(LocalDate.now().minusDays(1), dealer1, false, user1);
		Ticket t2 = new Ticket(LocalDate.now().minusDays(1), dealer1, false, user1);
		Ticket t3 = new Ticket(LocalDate.now().minusYears(1), dealer1, false, user1);
		travelDAO.save(t1);
		travelDAO.save(t2);
		travelDAO.save(t3);

		Vehicle bus1 = new Vehicle(50, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus1);
		Vehicle bus2 = new Vehicle(50, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus2);
		Vehicle bus3 = new Vehicle(50, Vehicle.Type.BUS);
		vDAO.saveVehicle(bus3);
		Vehicle tram1 = new Vehicle(50, Vehicle.Type.TRAM);
		vDAO.saveVehicle(tram1);

		Route r1 = new Route("Roma", "Milano", 8, 2, Duration.ofHours(4));
		r1.setVehicle(bus1);
		rDAO.saveRoute(r1);
		Route r2 = new Route("Terndfgdi", "Firfenze", 4, 4, Duration.ofHours(1));
		r2.setVehicle(bus2);
		rDAO.saveRoute(r2);
		Route r3 = new Route("rrni", "Firegadgagagnze", 6, 3, Duration.ofHours(2));
		r3.setVehicle(bus3);
		rDAO.saveRoute(r3);
		Route r4 = new Route("Terndfturti", "11Firenze", 10, 2, Duration.ofHours(5));
		r4.setVehicle(tram1);
		rDAO.saveRoute(r4);

		pass1.setVehicle(tram1);
		t1.setVehicle(bus1);
		t2.setVehicle(bus1);
		t3.setVehicle(bus2);

		vDAO.validateTicket(t1.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(t2.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(t3.getId().toString(), bus1.getId().toString());
		vDAO.validateTicket(pass1.getId().toString(), tram1.getId().toString());
//
//		vDAO.docPerVehicleAndDate(vDAO.getVehicleById(UUID.fromString("e4ea7b87-b7df-4376-81a5-d8a95dca1704")),
//				LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
//		travelDAO.checkValidity(pass1.getId().toString(), null);
//
//		vDAO.endMaintenance(UUID.fromString("32a217c5-a9a4-46f7-bc81-45fcfbdffc8a"));
		// apertura scanner per interazione con utente
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
				System.out.println("Sei salito sul mezzo numero " + selectedBus.getType() + ", quando il controllore ti chiede di mostrare il tuo titolo di viaggio...");
				
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

		// chiusura scanner
		scanner.close();
		em.close();
		emf.close();
	}

}
