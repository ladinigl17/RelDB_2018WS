package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(schema = "ue3", name = "Person")
public class PersonSequence {
	public enum Color
	{
		red,
		blue,
		green
	}

	@SequenceGenerator (name = "PersonIdGenerator",
			sequenceName = "person_sequence", allocationSize = 1)
	@Id
	@GeneratedValue (generator="PersonIdGenerator")
	int id;
	int height, age;
	String eyecolor, name;
	@Enumerated(EnumType.STRING)
	@Column(name = "haircolor")
	Color hair_color;

	protected PersonSequence(){}

	public PersonSequence
	(int id, String name, String eyecolor, int height, int age, Color haircolor)
	{
		this.id = id;
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;
		this.hair_color = haircolor;

	}

	public PersonSequence
	(String name, String eyecolor, int height, int age, Color haircolor)
	{
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;
		this.hair_color = haircolor;
		
	}
	
	public void raiseHeight(int more)
	{
		this.height += more;
	};
	
	public int getHeight(){ return this.height; }
}
