package entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public abstract class Travel_Document {
	
	@Id
	@GeneratedValue
	protected UUID id;
	protected LocalDate dataEmissione;
	//@ManyToOne(mappedBy = Rivenditore autorizzato)
	protected UUID puntoEmissione;
	
	public static int totalDocumentsIssued = 0;
	private static Map<UUID, Integer> documentsIssuedBySeller = new HashMap<>();
	
	public Travel_Document(UUID id, LocalDate dataEmissione, UUID puntoEmissione) {
		super();
		this.id = id;
		this.dataEmissione = dataEmissione;
		this.puntoEmissione = puntoEmissione;
		incrementDocumentsIssued(puntoEmissione);
	}
	
	public static void incrementDocumentsIssued(UUID puntoEmissione) {
		totalDocumentsIssued++;
		documentsIssuedBySeller.put(puntoEmissione, documentsIssuedBySeller.getOrDefault(puntoEmissione, 0) + 1);
	}
	
	public static int getTotalDocumentsIssued() {
		return totalDocumentsIssued;
	}

	public static Map<UUID, Integer> getDocumentsIssuedBySeller() {
		return documentsIssuedBySeller;
	}
	
}
