package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pass")
@Getter
@Setter
@NoArgsConstructor
public class Public_Transport_Pass extends Travel_Document {
	
	
	public enum SubType {SETTIMANALE, MENSILE};
	private SubType subType;
	
	private boolean isValid;

	public Public_Transport_Pass(LocalDate dataEmissione, AuthorizedDealer puntoEmissione, SubType subType, boolean isValid) {
		super(dataEmissione, puntoEmissione);
		this.subType = subType;
		this.isValid = isValid;
	}
	
	
}