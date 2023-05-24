package app;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DealersDAO;
import dao.Travel_DocumentDAO;
import dao.cardDao;
import dao.userDao;
import entities.AuthorizedDealer;
import entities.Card;
import entities.Public_Transport_Pass;
import entities.Ticket;
import entities.User;
import util.JpaUtil;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        userDao userDao = new userDao(em);
        Travel_DocumentDAO td = new Travel_DocumentDAO(em);
        DealersDAO dDAO = new DealersDAO();
        cardDao cd = new cardDao(em);

        // Creazione degli oggetti User
        User user1 = new User("John", "Doe");
        AuthorizedDealer d1 = new AuthorizedDealer();
        
        
        userDao.saveUser(user1);
        dDAO.createAuthorizedDealer(d1);
        
        
        Card c1 = new Card(LocalDate.now(), user1.getId());
        
        
       
       Public_Transport_Pass p1 = new Public_Transport_Pass(LocalDate.now(), d1, Public_Transport_Pass.SubType.SETTIMANALE, true, c1 );
       
       c1.setPassId(p1.getId());
        
       cd.saveCard(c1);

       td.save(p1);
        em.close();
        emf.close();
    }
}
