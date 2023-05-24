package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private boolean endorsed;
	
	public Ticket(LocalDate dataEmissione,  AuthorizedDealer puntoEmissione, boolean endorsed, User u) {
		super(dataEmissione, puntoEmissione);
		this.endorsed = endorsed;
		this.user = u;
	}
		
	
}
