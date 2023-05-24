package entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.InheritanceType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQuery(name = "AuthorizedDealer.findAll", query = "SELECT a FROM AuthorizedDealer a")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class AuthorizedDealer {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "issued_documents")
	private int totalDocumentsIssued = 0;
	

	public int incrementDocCounter() {
		return totalDocumentsIssued++;
	}
	
	public int getTotalDocumentsIssued() {
		return totalDocumentsIssued;
	}

	
	@Override
	public String toString() {
		return "AuthorizedDealer [id=" + id + "]";
	}

}