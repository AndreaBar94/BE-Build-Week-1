package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import util.JpaUtil;



public class main {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(main.class);
	
	private static EntityManagerFactory  emf= JpaUtil.getEntityManagerFactory(); 
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		
	}

}
