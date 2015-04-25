package ch.locatee.test.querydslerror.locatee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Location {

	@Id
	@GeneratedValue
	@Column(name="LOCATION_ID")
	protected int id;

	@ManyToMany(targetEntity = LocationAttribute.class)
	@JoinTable(name="LOCATION_TO_LOCATION_ATTRIBUTE",
			joinColumns = @JoinColumn(name="LOCATION_ID", referencedColumnName = "LOCATION_ID"),
			inverseJoinColumns = @JoinColumn(name="LOCATION_ATTRIBUTE_ID", referencedColumnName = "LOCATION_ATTRIBUTE_ID")
	)
	private List<LocationAttribute> locationAttributes;
}
