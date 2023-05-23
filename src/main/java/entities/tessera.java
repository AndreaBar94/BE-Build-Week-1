package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tessera")
@Getter
@Setter
public class tessera {
	@Id
	@GeneratedValue
	protected UUID Id;
	protected LocalDate dataAttivazione;
	protected LocalDate dataScadenza;
	protected boolean validità;
	
	public tessera() {
		
	};
	
	public tessera(LocalDate dataAttivazione, boolean validità) {
		this.dataAttivazione = dataAttivazione;
		this.dataScadenza = dataAttivazione.plusYears(1);
		this.validità = validità;
	};
	
	@Override
	public String toString() {
		return "Tessera n°= " + Id + " data di attivazione: " + dataAttivazione + " data di scadenza: " + dataScadenza + " validità: " + validità;
	};
}
