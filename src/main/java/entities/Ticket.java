package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket extends Travel_Document{
	
	
	private boolean endorsed;
	
	public Ticket(LocalDate dataEmissione,  AuthorizedDealer puntoEmissione, boolean endorsed) {
		super(dataEmissione, puntoEmissione);
		this.endorsed = endorsed;
	}
		
	
}
