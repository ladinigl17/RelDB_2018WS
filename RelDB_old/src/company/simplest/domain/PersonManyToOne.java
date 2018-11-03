package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(schema = "ue5_manytoone", name = "person")
public class PersonManyToOne {
	public enum Color
	{
		red,
		blue,
		green
	}
	@SequenceGenerator (name = "PersonIdGenerator",
			sequenceName = "person_sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator="PersonIdGenerator")
	int id;
	int height, age;
	String eyecolor, name;

	public AddressManyToOne getAddress() {
		return address;
	}

	public void setAddress(AddressManyToOne address) {
		this.address = address;
	}

	@ManyToOne
	private AddressManyToOne address;

	protected PersonManyToOne(){}

	public PersonManyToOne
	(int id, String name, String eyecolor, int height, int age)
	{
		this.id = id;
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;

	}

	public PersonManyToOne
	(String name, String eyecolor, int height, int age)
	{
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;
		
	}
	
	public void raiseHeight(int more)
	{
		this.height += more;
	};
	
	public int getHeight(){ return this.height; }
}
