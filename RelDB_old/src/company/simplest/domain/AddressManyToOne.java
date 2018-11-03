package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(name = "address", schema="ue5_manytoone")
public class AddressManyToOne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String aname;

	protected AddressManyToOne(){}

	public AddressManyToOne
	(int id, String aname)
	{
		this.id = id;
		this.aname = aname;

	}

}
