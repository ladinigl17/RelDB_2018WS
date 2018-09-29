package company.simplest.domain;

import javax.persistence.Entity; 
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	int id;
	int height, age;
	String eyecolor, name;
	
	protected Person(){}
	
	public Person
	(int id, String name, String eyecolor, int height, int age)
	{
		this.id = id;
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
