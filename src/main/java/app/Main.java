package app;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.VehicleDAO;
import util.JpaUtil;

public class Main {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		VehicleDAO vehicleDAO = new VehicleDAO(em);

//		Bus bus1 = new Bus(50);
//		Bus bus2 = new Bus(40);
//
//		Tram tram1 = new Tram(200);
//		Tram tram2 = new Tram(150);
//
//		vehicleDAO.saveVehicle(bus1);
//		vehicleDAO.saveVehicle(bus2);
//		vehicleDAO.saveVehicle(tram1);
//		vehicleDAO.saveVehicle(tram2);

		vehicleDAO.endService(UUID.fromString("0f6a10a9-5595-4222-beac-1fbe1e567acd"));

		em.close();
		emf.close();
	}
}
