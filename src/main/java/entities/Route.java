package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
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
	
	@OneToOne(mappedBy = "route")
    private Travel travels;

	public Route(String startPoint, String terminus, int avgTime) {
		super();
		this.startPoint = startPoint;
		this.terminus = terminus;
		this.avgTime = avgTime;
	}
	
	
}
