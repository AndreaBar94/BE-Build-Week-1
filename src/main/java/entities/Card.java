package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public class Card {
	@Id
	@GeneratedValue
	protected UUID Id;
	protected LocalDate dataAttivazione;
	protected LocalDate dataScadenza;
	protected boolean validità;
	
	@Column(name = "user_id")
    private UUID userId;
	
	@Column(name = "pass_id")
    private UUID passId;
	
	
	@OneToOne(mappedBy = "card")
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy = "card")
    @JoinColumn(name = "pass_id")
    private Public_Transport_Pass pass;
	
	public Card(LocalDate dataAttivazione, UUID userAssociato) {
		this.dataAttivazione = dataAttivazione;
		this.dataScadenza = dataAttivazione.plusYears(1);
		this.userId = userAssociato;
		this.validità = true;
		this.passId = null;
	};
	
	
	@Override
	public String toString() {
		return "Tessera n°= " + Id + " data di attivazione: " + dataAttivazione + " data di scadenza: " + dataScadenza + " validità: " + validità;
	};
}
