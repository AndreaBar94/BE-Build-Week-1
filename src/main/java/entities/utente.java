package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utente")
@Getter
@Setter
public class utente {
	@Id
	@GeneratedValue
	protected UUID Id;
	protected String name;
	protected String lastName;
	
	
	public utente() {
		
	};
	
	public utente(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	};
	
	@Override
	public String toString() {
		return "Utente = " + Id + " nome: " + name + " cognome: " + lastName;
	};
}
