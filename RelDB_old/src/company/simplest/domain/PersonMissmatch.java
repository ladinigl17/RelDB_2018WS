package company.simplest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class PersonMissmatch {
	@Id
	int id;
	@Column(name="height")
	int heights;
	@Column(name="age")
	int alter;
	String eyecolor, name;

	protected PersonMissmatch(){}

	public PersonMissmatch
	(int id, String name, String eyecolor, int heights, int alter)
	{
		this.id = id;
		this.name = name;
		this.eyecolor = eyecolor;
		this.heights = heights;
		this.alter = alter;
	}
	
	public void raiseHeight(int more)
	{
		this.heights += more;
	};
	
	public int getHeight(){ return this.heights; }
}
