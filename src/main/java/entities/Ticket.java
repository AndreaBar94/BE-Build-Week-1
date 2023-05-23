package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	public Ticket(UUID id, LocalDate dataEmissione, UUID puntoEmissione, UUID id2, boolean endorsed) {
		super(id, dataEmissione, puntoEmissione);
		id = id2;
		this.endorsed = endorsed;
	}
		
	
}
