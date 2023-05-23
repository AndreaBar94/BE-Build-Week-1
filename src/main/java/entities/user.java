package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class user {
	@Id
	@GeneratedValue
	protected UUID Id;
	protected String name;
	protected String lastName;
	

	public user(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	};
	
	@Override
	public String toString() {
		return "Utente = " + Id + " nome: " + name + " cognome: " + lastName;
	};
}
