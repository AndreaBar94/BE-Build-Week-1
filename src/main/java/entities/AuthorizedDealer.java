package entities;


import javax.persistence.Entity;
import javax.persistence.InheritanceType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "AuthorizedDealer.findAll", query = "SELECT a FROM AuthorizedDealer a")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AuthorizedDealer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public AuthorizedDealer() {
		super();

	}

	@Override
	public String toString() {
		return "AuthorizedDealer [id=" + id + "]";
	}


}