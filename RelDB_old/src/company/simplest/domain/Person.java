package company.simplest.domain;

import javax.persistence.*;

@Entity
@Table(schema = "ue2")
public class Person {
	public enum Color
	{
		red,
		blue,
		green
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int height, age;
	String eyecolor, name;
	@Enumerated(EnumType.STRING)
	@Column(name = "haircolor")
	Color hair_color;
	
	protected Person(){}
	
	public Person
	(int id, String name, String eyecolor, int height, int age, Color haircolor)
	{
		this.id = id;
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;
		this.hair_color = haircolor;
		
	}
	
	public Person
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
