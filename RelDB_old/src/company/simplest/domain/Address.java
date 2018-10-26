package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(name = "address", schema="ue_4_onetoone")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String aname;

	@OneToOne(mappedBy = "address")
	private Building building;

	public void setBuilding (Building building) {
		this.building = building;
	}
	public Building getBuilding() {
		return building;
	}

	protected Address(){}

	public Address
	(int id, String aname)
	{
		this.id = id;
		this.aname = aname;

	}

}
