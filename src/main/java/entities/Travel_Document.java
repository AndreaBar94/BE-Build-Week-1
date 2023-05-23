package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Travel_Document {
	
	@Id
	@GeneratedValue
	protected UUID id;
	protected LocalDate dataEmissione;
	//@ManyToOne(mappedBy Rivenditore autorizzato)
	protected UUID puntoEmissione;
	
	public Travel_Document(UUID id, LocalDate dataEmissione, UUID puntoEmissione) {
		super();
		this.id = id;
		this.dataEmissione = dataEmissione;
		this.puntoEmissione = puntoEmissione;
	}
	
}
