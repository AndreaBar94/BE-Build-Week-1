package dao;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ch.qos.logback.classic.Level;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import entities.AuthorizedDealer;
import entities.Public_Transport_Pass;
import entities.Travel_Document;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Travel_DocumentDAO {
	
	private final EntityManager em;
	
	public Travel_DocumentDAO (EntityManager em) {
		this.em = em;
	}
	
	public void save(Travel_Document a) {
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(a);
			t.commit();
			log.info("Travel Document saved!");
		} catch (Exception e) {
			if (t.isActive()) {
                t.rollback();
            }
            log.error("Errore durante il salvataggio del veicolo.", e);
            throw e;
		}
		
	}
	
	public Travel_Document findByUUID(String id) {
		Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
		return found;
	}
	
	public void delete(String id) {
		try {
			Travel_Document found = em.find(Travel_Document.class, UUID.fromString(id));
			if (found != null) {
			em.remove(found);
			System.out.println("Travel Document with id " + id + " deleted!");
		}
		} catch (Exception e) {
			log.info("Delete error: " + e);
		}
		
	}
	
	public void checkValidity(String id) {
	    Public_Transport_Pass pass = em.find(Public_Transport_Pass.class, UUID.fromString(id));
	    
	    if (pass.getSubType() == Public_Transport_Pass.SubType.SETTIMANALE) {
	       
	        LocalDate dataEmissione = pass.getDataEmissione();
	        LocalDate now = LocalDate.now();
	        LocalDate expirationDate = dataEmissione.plusWeeks(1);
	        
	        if (now.isBefore(expirationDate)) {
	            System.out.println("L'abbonamento settimanale è ancora valido.");
	        } else {
	            System.out.println("L'abbonamento settimanale non è più valido.");
	        }
	    } else if (pass.getSubType() == Public_Transport_Pass.SubType.MENSILE) {
	    
	        LocalDate dataEmissione = pass.getDataEmissione();
	        LocalDate now = LocalDate.now();
	        LocalDate expirationDate = dataEmissione.plusMonths(1); 
	        
	        if (now.isBefore(expirationDate)) {
	            System.out.println("L'abbonamento mensile è ancora valido.");
	        } else {
	            System.out.println("L'abbonamento mensile non è più valido.");
	        }
	    } else {
	        System.out.println("Tipo di abbonamento non supportato.");
	    }
	}
	
	public int docPerDealersAndDate(AuthorizedDealer authorizedDealer, LocalDate startDate, LocalDate endDate) {
		try {
            List<Travel_Document> documents = getDocumentsByAuthorizedDealer(authorizedDealer);
            int count = (int) documents.stream()
                    .filter(document -> isWithinDateRange(document.getDataEmissione(), startDate, endDate))
                    .count();
            log.info("Numero di documenti emessi per il punto di emissione: " + count);
            return count;
        } catch (Exception e) {
            log.error("Si è verificato un errore durante il conteggio dei documenti.", e);
            return 0;
        }
	}
	
	private List<Travel_Document> getDocumentsByAuthorizedDealer(AuthorizedDealer authorizedDealer) {
        try {
			List<Travel_Document> found = authorizedDealer.getIssuedDocuments();
			return found;
		} catch (Exception e) {
			 log.error("Si è verificato un errore durante il conteggio dei documenti.", e);
	            return null;
		}
  
    }
    

    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
