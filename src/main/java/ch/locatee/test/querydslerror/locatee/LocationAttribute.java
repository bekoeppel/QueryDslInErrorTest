package ch.locatee.test.querydslerror.locatee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LocationAttribute {

	@Id
	@GeneratedValue
	@Column(name="LOCATION_ATTRIBUTE_ID")
	protected int id;

}
