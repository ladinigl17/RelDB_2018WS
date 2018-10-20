package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String aname;

	protected Address(){}

	public Address
	(int id, String aname)
	{
		this.id = id;
		this.aname = aname;

	}

}
