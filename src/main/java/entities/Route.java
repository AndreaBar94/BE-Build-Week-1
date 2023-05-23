package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import antlr.collections.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Getter
@Setter 
@NoArgsConstructor
public class Route {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String startPoint;
	private String terminus;
	
	private int avgTime;
	
//	@OneToMany(mappedBy = "routes")
//	private List<Travel> travels;
	
	
}
