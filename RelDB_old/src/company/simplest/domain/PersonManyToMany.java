package company.simplest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(schema = "ue6_manytomany", name = "person")
public class PersonManyToMany {

	@SequenceGenerator (name = "PersonIdGenerator",
			sequenceName = "person_sequence", allocationSize = 1)
	@Id
	@GeneratedValue(generator="PersonIdGenerator")
	int id;
	int height, age;
	String eyecolor, name;

	public Collection<GroupManyToMany> getGroups() {
		return groups;
	}



    @ManyToMany
    @JoinTable(schema="ue6_manytomany", name="person_group", joinColumns = @JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="groups_id") )
    private Collection<GroupManyToMany> groups = new ArrayList<GroupManyToMany>();

    public void addGroup(GroupManyToMany group) {
		groups.add(group);
		group.addPerson(this);
	}


	protected PersonManyToMany(){}

	public PersonManyToMany
	(int id, String name, String eyecolor, int height, int age)
	{
		this.id = id;
		this.name = name;
		this.eyecolor = eyecolor;
		this.height = height;
		this.age = age;

	}

	public PersonManyToMany
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
